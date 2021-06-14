# Collection of curl commands to play game

## Play

```curl -i --user user:pass1 http://localhost:8080/api/game/submit/rock```

```curl -i --user user:pass1 http://localhost:8080/api/game/submit/paper```

```curl -i --user user:pass1 http://localhost:8080/api/game/submit/scissors```

## User List

```curl -i --admin:admin123 http://localhost:8080/api/player/list```