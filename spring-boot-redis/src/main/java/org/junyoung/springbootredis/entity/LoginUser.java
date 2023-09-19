package org.junyoung.springbootredis.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@RedisHash(value="login_user", timeToLive=10L) // 60초 이후 만료
// Redis에 값을 저장하기 전 primary key를 관리하기 위한 keyspace라는 SET을 따로 저장함. 따라서 redis 안에서 'keys *'를 입력하면 아래와 같이 나옴
// 1) login_user             <- keyspace set
// 2) login_user:세션ID       <- 실제 데이터
public class LoginUser {
    @Id
    private String id;

    private final String name;
    private final LocalDateTime loginAt;

    @PersistenceCreator // Redis에서 역직렬화를 할 때 사용하는 생성자
    public LoginUser(String id, String name, LocalDateTime loginAt) {
        this.id = id;
        this.name = name;
        this.loginAt = loginAt;
    }

    public LoginUser(String name) {
        this.name = name;
        this.loginAt = LocalDateTime.now();
    }

    public LoginUser(LoginUser loginUser) {
        this.id = loginUser.getId();
        this.name = loginUser.getName();
        this.loginAt = loginUser.getLoginAt();
    }

}
