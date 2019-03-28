#!/usr/bin/env bash

# Launch the Vert.x backend server with hot reloading in both sides

export LAUNCHER="io.vertx.core.Launcher"
export VERTICLE="ali.vertx.wt.AppDeployer"
export CMD="mvn compile"
export VERTX_CMD="run"

# Start containers and the app
mvn compile dependency:copy-dependencies && java \
  -cp  $(echo target/dependency/*.jar | tr ' ' ':'):"target/classes" \
  $LAUNCHER $VERTX_CMD $VERTICLE \
  --redeploy="src/main/**/*" --on-redeploy="$CMD" \
  --launcher-class=$LAUNCHER \
  --conf src/main/resources/conf.json
  $@