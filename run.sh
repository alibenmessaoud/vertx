#!/bin/bash

trap "exit" INT TERM ERR

trap "kill 0" EXIT

if test ! -z "$(docker ps -aq)"; then
  docker stop $(docker ps -aq)
  docker rm $(docker ps -aq)
fi

if test ! -z "$(docker images -q go-agile-web/app.goagile.web)"; then
  docker rmi go-agile-web/app.goagile.web
fi

mvn clean install

docker-compose up -d & wait
