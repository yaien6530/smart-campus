package cn.yaien.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.text.SimpleDateFormat;

/**
 * Json 工具类
 *
 * @author yanggl
 * @since 2024/7/17/15:56
 */
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private JsonUtil() {
    }

    static {
        //对象的NULL字段不进行转换
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //取消默认转换timestamps形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //忽略空Bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat(STANDARD_FORMAT));
        //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 将对象序列化为JSON字符串
     *
     * @param object 要序列化的对象
     * @return 包含JSON字符串的Mono
     */
    public static Mono<String> toJson(Object object) {
        return Mono.fromCallable(() -> objectMapper.writeValueAsString(object))
                .subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * 将JSON字符串反序列化为对象
     *
     * @param json  JSON字符串
     * @param clazz 对象的Class类型
     * @param <T>   对象的类型
     * @return 包含对象的Mono
     */
    public static <T> Mono<T> toObj(String json, Class<T> clazz) {
        return Mono.fromCallable(() -> objectMapper.readValue(json, clazz))
                .subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * 将对象转换为JsonNode
     *
     * @param object 要转换的对象
     * @return 包含JsonNode的Mono
     */
    public static Mono<JsonNode> toJsonNode(Object object) {
        return Mono.fromCallable(() -> (JsonNode) objectMapper.valueToTree(object))
                .subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * 将JsonNode转换为对象
     *
     * @param jsonNode JsonNode对象
     * @param clazz    对象的Class类型
     * @param <T>      对象的类型
     * @return 包含对象的Mono
     */
    public static <T> Mono<T> toObj(JsonNode jsonNode, Class<T> clazz) {
        return Mono.fromCallable(() -> objectMapper.treeToValue(jsonNode, clazz))
                .subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * 将JSON字符串转换为JsonNode
     *
     * @param json JSON字符串
     * @return 包含JsonNode的Mono
     */
    public static Mono<JsonNode> toJsonNode(String json) {
        return Mono.fromCallable(() -> objectMapper.readTree(json))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
