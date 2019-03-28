#!/usr/bin/env bash

docker-compose -f ./src/docker/docker-compose.yml down
mvn vertx:stop
