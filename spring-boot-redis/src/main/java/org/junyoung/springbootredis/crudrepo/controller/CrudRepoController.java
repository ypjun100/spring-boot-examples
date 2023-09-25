package org.junyoung.springbootredis.crudrepo.controller;

import lombok.RequiredArgsConstructor;
import org.junyoung.springbootredis.crudrepo.entity.MissionTimer;
import org.junyoung.springbootredis.crudrepo.repository.MissionTimerRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crud-repo")
@RequiredArgsConstructor
public class CrudRepoController {
    private final MissionTimerRepository missionTimerRepository;

    // 총 타이머 수 확인
    @GetMapping("/number-of-timers")
    public long getNumberOfTimers() { return missionTimerRepository.count(); }

    // 새로운 미션 추가
    @PostMapping("/mission")
    public String createMission() {

        ///////////////////
        // ...
        // 미션 추가 관련 코드
        // ...
        ///////////////////

        // 미션 타이머 추가
        MissionTimer missionTimer = new MissionTimer();
        missionTimerRepository.save(missionTimer);
        return missionTimer.getId();
    }

    // 타이머 갱신
    @PutMapping("/mission-timer")
    public void updateMissionTimer(@RequestBody String id) {
        if (missionTimerRepository.findById(id).isPresent()) {
            MissionTimer missionTimer = missionTimerRepository.findById(id).get();
            missionTimerRepository.save(new MissionTimer(missionTimer.getId()));
        }
    }
}
