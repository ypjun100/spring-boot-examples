package org.junyoung.springbootredis.crudrepository.controller;

import lombok.RequiredArgsConstructor;
import org.junyoung.springbootredis.crudrepository.entity.MissionTimer;
import org.junyoung.springbootredis.crudrepository.repository.MissionTimerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CrudRepoController {
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
        MissionTimer missionTimer = new MissionTimer(missionId);
        missionTimerRepository.save(missionTimer);
        return missionTimer.getMissionId();
    }

    // 타이머 갱신
    @PutMapping("/mission-timer")
    public void updateMissionTimer(@RequestBody String missionId) {
        if (missionTimerRepository.findById(missionId).isPresent()) { // 존재하는 미션인지 확인
            MissionTimer missionTimer = missionTimerRepository.findById(missionId).get();
            missionTimerRepository.save(new MissionTimer(missionTimer.getMissionId()));
        }
    }
}
