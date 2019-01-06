#!/bin/bash

if test ! -z "$(docker ps -aq)"; then
  docker stop $(docker ps -aq)
  docker rm $(docker ps -aq)
fi

docker rmi -f $(docker images -a | grep "<none>" | awk "{print \$3}")

echo "==DOCKER:OK"