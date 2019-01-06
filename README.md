# kaplab.io/cards Guide

This guide walks you through the process of building a [Docker](https://docker.com/) image for running a Spring Boot application with mongo database and an Angular app.

## App

```javascript
@angular/core: 7.0.0
```

## Api

```xml 
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-parent</artifactId>
<version>2.1.1.RELEASE</version>
```

## Mongo-init

```xml
<groupId>org.mongodb</groupId>
<artifactId>mongodb-driver</artifactId>
<version>3.8.2</version>
```

## Build and run

Run:

```
./up.sh
```

```
./up.sh                                                  
Started configuration
Run under /home/ali/kaplab/01

 _    _       _       _      _       
| | _(_)_ __ | | __ _| |__  (_) ___  
| |/ / | '_ \| |/ _' | '_ \ | |/ _ \ 
|   <| | |_) | | (_| | |_) || | (_) |
|_|\_\_| .__/|_|\__,_|_.__(_)_|\___/ 
       |_|                           
Moving satellites into position

Creating network "01_default" with the default driver
Creating io.kaplab.cards.webapp ... done
Creating io.kaplab.cards.mongo  ... done
Creating io.kaplab.cards.mongo-init   ... done
Creating io.kaplab.cards.api          ... done
Creating io.kaplab.cards.mongo-client ... done
```

Stop:

```
./down.sh
```

```
./down.sh 
Stopping io.kaplab.cards.api    ... done
Stopping io.kaplab.cards.webapp ... done
Stopping io.kaplab.cards.mongo  ... done
Removing io.kaplab.cards.api          ... done
Removing io.kaplab.cards.mongo-client ... done
Removing io.kaplab.cards.mongo-init   ... done
Removing io.kaplab.cards.webapp       ... done
Removing io.kaplab.cards.mongo        ... done
Removing network 01_default

```

## Test



Api test:

```bash
http://localhost:8080/card/ 
```

App test:

```bash
http://localhost:3000 
```

## Check

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

## Utils

Useful docker commands:

```bash
docker images -a
docker ps -a
docker stop | kill $(docker ps -aq)
docker rm $(docker ps -aq)
docker rm (docker stop (docker ps -a -q --filter ancestor=name_of_container --format="{{.ID}}"))
docker rmi $(docker images -aq)
docker rmi $(docker images --filter=reference='name_of_image' --format "{{.ID}}")
docker rmi -f $(docker images -a | grep "<none>" | awk "{print \$3}")
```

