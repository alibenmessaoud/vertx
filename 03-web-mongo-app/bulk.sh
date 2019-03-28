#!/bin/bash
for i in {1..10000}
do
    curl -X POST http://localhost:9000/api/comment/save \
  	-H 'Content-Type: application/json' \
  	-d '{"content": "comment using post", "user": "ali"}'
done
