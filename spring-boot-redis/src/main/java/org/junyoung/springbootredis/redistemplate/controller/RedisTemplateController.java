package org.junyoung.springbootredis.redistemplate.controller;

import lombok.RequiredArgsConstructor;
import org.junyoung.springbootredis.redistemplate.repository.MissionTimerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class RedisTemplateController {
    private final MissionTimerRepository missionTimerRepository;

    // 새로운 미션 추가
    @PostMapping("/mission")
    public String createMission() {

        ///////////////////
        String missionId = UUID.randomUUID().toString();
        // 미션 추가 관련 코드
        // ...
        ///////////////////

        // 미션 타이머 추가
        missionTimerRepository.save(missionId);
        return missionId;
    }

    // 타이머 갱신
    @PutMapping("/mission-timer")
    public void updateMissionTimer(@RequestBody String missionId) {
        missionTimerRepository.update(missionId);
    }
}