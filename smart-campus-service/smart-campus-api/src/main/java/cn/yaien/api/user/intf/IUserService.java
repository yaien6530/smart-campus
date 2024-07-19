package cn.yaien.api.user.intf;

import cn.yaien.api.user.param.user.UserSaveDTO;
import cn.yaien.common.web.Session;
import reactor.core.publisher.Mono;

/**
 * 用户服务接口
 *
 * @author yanggl
 * @since 2024/7/17/23:22
 */
public interface IUserService {

    /**
     * 用户登陆
     *
     * @param username 用户名
     * @param password 密码
     * @param captcha  验证码
     * @return 带登陆Session的Mono
     */
    Mono<Session> login(String username, String password, String captcha);

    /**
     * 新增用户
     *
     * @param dto 请求模版
     * @return 执行结果
     */
    Mono<Void> save(UserSaveDTO dto);

}
