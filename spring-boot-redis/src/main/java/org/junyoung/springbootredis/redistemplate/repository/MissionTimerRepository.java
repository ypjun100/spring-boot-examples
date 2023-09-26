package org.junyoung.springbootredis.redistemplate.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Repository
public class MissionTimerRepository {
    final private RedisTemplate<String, Object> redisTemplate;
    final private ValueOperations<String, Object> valueOperations;

    final private String prefix = "redistemplate:mission_timer:"; // Redis에 저장될 key prefix

    public MissionTimerRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
    }

    // 데이터 저장
    public void save(String missionId) {
        // Key로는 'prefix + 미션 id', Value로는 해당 데이터가 만료되는 시점을 저장
        valueOperations.set(prefix + missionId, LocalDateTime.now().plusSeconds(10).toString());
        redisTemplate.expire(prefix + missionId, 10, TimeUnit.SECONDS); // 10초 후 만료
    }

    // 데이터 갱신 (ttl을 갱신시키는 역할을 함)
    public void update(String missionId) {
        if (valueOperations.get(prefix + missionId) != null) { // 해당 미션이 존재하는지 확인
            valueOperations.set(prefix + missionId, LocalDateTime.now().plusSeconds(10).toString());
            redisTemplate.expire(prefix + missionId, 10, TimeUnit.SECONDS); // 10초 후 만료
        }
    }
}
