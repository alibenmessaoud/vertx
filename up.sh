#!/bin/bash

echo "Started configuration"
echo "Run under ${PWD}"

trap "exit" INT TERM ERR
trap "kill 0" EXIT

./run-brand.sh
./run-clean.sh
./api/run-api.sh
./app/run-app.sh

docker-compose up --build -d & wait
