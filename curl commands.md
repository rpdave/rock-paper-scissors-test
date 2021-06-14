# Collection of curl commands to play game
curl http://localhost:8080/welcome

## Play
curl -i --user user:pass1 http://localhost:8080/api/game/submit/rock
curl -i --user user:pass1 http://localhost:8080/api/game/submit/paper
curl -i --user user:pass1 http://localhost:8080/api/game/submit/scissors

## User List