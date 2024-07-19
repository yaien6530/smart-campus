package cn.yaien.common.utils;

import lombok.Getter;

/**
 * 主键生成工具类
 *
 * @author yanggl
 * @since 2024/7/18/20:18
 */
public class IdGeneratorUtil {

    // 毫秒内序列(0~4095)
    private long sequence = 0L;

    // 上次生成ID的时间截
    private long lastTimestamp = -1L;

    // 获取单例实例
    @Getter
    private static IdGeneratorUtil instance = new IdGeneratorUtil();

    // 私有构造函数防止实例化
    private IdGeneratorUtil() {
    }

    /**
     * 生成唯一的主键
     *
     * @return 唯一主键
     */
    public synchronized long generateKey() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        // 如果是同一时间生成的，则进行毫秒内序列
        // 序列在id中占的位数
        long sequenceBits = 12L;
        if (lastTimestamp == timestamp) {
            // 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
            long sequenceMask = ~(-1L << sequenceBits);
            sequence = (sequence + 1) & sequenceMask;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 时间戳改变，毫秒内序列重置
            sequence = 0L;
        }

        // 上次生成ID的时间截
        lastTimestamp = timestamp;

        // 开始时间截 (2024-01-01)
        long twepoch = 1704067200000L;
        return ((timestamp - twepoch) << sequenceBits) | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        // 测试主键生成
        IdGeneratorUtil generator = IdGeneratorUtil.getInstance();
        for (int i = 0; i < 20; i++) {
            long primaryKey = generator.generateKey();
            System.out.println("生成的主键: " + primaryKey);
        }
    }
}
