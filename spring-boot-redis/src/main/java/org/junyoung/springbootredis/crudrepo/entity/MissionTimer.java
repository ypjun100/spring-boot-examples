package org.junyoung.springbootredis.crudrepo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor // 빈 파라미터를 가진 생성자 생성 (id에는 자동으로 랜덤 문자열이 들어감)
@RedisHash(value="mission_timer", timeToLive = 30) // 30초 이후 만료
// RedisHash 어노테이션은 Redis에 데이터를 저장하기 전 Primary Key를 관리하기 위한 Keyspace라는 SET을 따로 저장함.
// 따라서 Redis 내에서 `keys *`를 입력하면 아래와 같이 출력됨
// 1) mission_timer       <- Keyspace Set
// 2) mission_timer:id    <- 실제 저장된 데이터
public class MissionTimer {
    @Id
    private String id;

    @PersistenceCreator // Redis에서 역질렬화(데이터를 가져올 때)를 할 때 사용되는 생성자 정의 어노테이션
    public MissionTimer(String id) {
        this.id = id;
    }
}
