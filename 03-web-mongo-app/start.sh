#!/usr/bin/env bash

# Launch the Vert.x backend server with hot reloading in both sides

export LAUNCHER="io.vertx.core.Launcher"
export VERTICLE="ali.vertx.wm.AppDeployer"
export CMD="mvn compile"
export VERTX_CMD="run"

# Delete existing container
docker ps -q --filter "name=vertxwmdb" | grep -q . && docker stop vertxwmdb && docker rm vertxwmdb

# Start containers and the app
docker-compose -f ./src/docker/docker-compose.yml up -d && mvn compile dependency:copy-dependencies && java \
  -cp  $(echo target/dependency/*.jar | tr ' ' ':'):"target/classes" \
  $LAUNCHER $VERTX_CMD $VERTICLE \
  --redeploy="src/main/**/*" --on-redeploy="$CMD" \
  --launcher-class=$LAUNCHER \
  --conf src/main/resources/conf.json
  $@