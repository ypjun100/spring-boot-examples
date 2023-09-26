package org.junyoung.springbootredis.crudrepository.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value="crudrepository:mission_timer", timeToLive = 10) // 10초 이후 만료
// RedisHash 어노테이션은 Redis에 데이터를 저장하기 전 Primary Key를 관리하기 위한 Keyspace라는 SET을 따로 저장함.
// 따라서 Redis 내에서 `keys *`를 입력하면 아래와 같이 출력됨
// 1) mission_timer       <- Keyspace Set
// 2) mission_timer:id    <- 실제 저장된 데이터
public class MissionTimer {
    @Id
    private String missionId;

    @PersistenceCreator // Redis에서 역질렬화(데이터를 가져올 때)를 할 때 사용되는 생성자 정의 어노테이션
    public MissionTimer(String missionId) {
        this.missionId = missionId;
    }
}
