# app

This guide walks you through the process of building a [Docker](https://docker.com/) image for running a Spring Boot application with mongo database.

### Dependencies

Spring:

```xml 
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-parent</artifactId>
<version>2.1.1.RELEASE</version>
```

Mongo:

```xml
<groupId>org.mongodb</groupId>
<artifactId>mongodb-driver</artifactId>
<version>3.8.2</version>
```

### Build and run

Run:

```
./run.sh
```

Test:

```bash
http://localhost:8080/card/ 
```

### Check

Connect to mongo container and check mongo collections:

```bash
docker exec -i -t mongo_container_id /bin/bash 
```

```bash
mongo
use your_db_name;
db.your_table.find();
db.
db.card.insertOne(... { "name": "slef", "author": "pp"})
```

### WIP

angular

### Utils

Useful docker commands:

```bash
docker images -a
docker ps -a
docker stop | kill $(docker ps -aq)
docker rm $(docker ps -aq)
docker rm (docker stop (docker ps -a -q --filter ancestor=name_of_container --format="{{.ID}}"))
docker rmi $(docker images -aq)
docker rmi $(docker images --filter=reference='name_of_image' --format "{{.ID}}")
```

