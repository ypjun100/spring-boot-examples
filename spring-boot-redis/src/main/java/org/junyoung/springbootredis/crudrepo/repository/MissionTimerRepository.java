package org.junyoung.springbootredis.crudrepo.repository;

import org.junyoung.springbootredis.crudrepo.entity.MissionTimer;
import org.springframework.data.repository.CrudRepository; // Spring Data에서 기본적으로 제공

public interface MissionTimerRepository extends CrudRepository<MissionTimer, String> {
}
