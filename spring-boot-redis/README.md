# Spring Boot with Redis Example
본 프로젝트에서는 아래의 명세서를 참고하여 똑같은 결과를 도출하기 위해 Spring Boot와 Redis 서버를 연결하기 위한 방법인 CrudRepository, RedisTemplate을 모두 구현한 뒤 이 둘을 비교합니다.

## 명세서
* 서비스 내에서는 여러 개의 미션을 생성하고 관리합니다.
* 각각의 미션은 생성된 뒤 10초 뒤에 자동으로 삭제됩니다.

| Request | Payload | Return | Desc |
|:--|:--|:--|:--|
|`POST /mission`|-|랜덤 미션 ID|미션을 생성합니다.|
|`PUT /mission-timer`|미션 ID|-|미션의 타이머를 갱신합니다.<br>이때 존재하지 않거나 만료된 미션 ID인 경우 작업을 진행하지 않습니다.|