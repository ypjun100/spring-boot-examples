package org.junyoung.springbootredis.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@RedisHash(value="login_user", timeToLive=60) // 60초 이후 만료
public class LoginUser {
    @Id
    private String id;

    private final String name;
    private final LocalDateTime loginAt;

    public LoginUser(String name) {
        this.name = name;
        this.loginAt = LocalDateTime.now();
    }
}
