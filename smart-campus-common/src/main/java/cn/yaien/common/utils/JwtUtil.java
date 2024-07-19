package cn.yaien.common.utils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * JWT工具类，用于生成和验证JWT Token
 *
 * @author yanggl
 * @since 2024/7/17/21:26
 */
public class JwtUtil {

    /**
     * 1d
     */
    public static final long EXPIRATION_TIME = 86400000;

    /**
     * 生成JWT Token
     *
     * @param username  用户名
     * @param secretKey 加密密钥
     * @return 生成的Token
     */
    public static Mono<String> generateToken(String username, String secretKey) {
        return Mono.fromCallable(() -> Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)), Jwts.SIG.HS256)
                .compact());
    }

    /**
     * 从Token中获取用户名
     *
     * @param token     JWT Token
     * @param secretKey 密钥
     * @return 用户名
     */
    public static Mono<String> getUserName(String token, String secretKey) {
        return Mono.fromCallable(() -> Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject());
    }

    /**
     * 验证Token是否有效
     *
     * @param token     JWT Token
     * @param secretKey 密钥
     * @return 是否有效
     */
    public static Mono<Boolean> validateToken(String token, String secretKey) {
        return Mono.fromCallable(() -> {
            try {
                Jwts.parser()
                        .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)))
                        .build()
                        .parseSignedClaims(token);
                return true;
            } catch (JwtException | IllegalArgumentException e) {
                return false;
            }
        });
    }

    /**
     * 从请求头中获取Token
     *
     * @param exchange
     * @return
     */
    public static String getTokenFromRequest(ServerWebExchange exchange) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        return headers.getFirst(HttpHeaders.AUTHORIZATION);
    }

}
