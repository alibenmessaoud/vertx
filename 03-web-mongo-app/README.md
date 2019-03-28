# Vert.x Web and MongoDB

Demo:
=============
Solution #1

Start database: `docker-compose up` in `src/docker`

Start the app:

`mvn clean compile vertx:run` Or `java -jar target/app.jar`

Solution #2

Start server using `./start.sh`

Stop server using `./stop.sh`

Dev:
=============
Solution #1

Start database: `docker-compose up` in `src/docker`

Solution #2

Start dev on watch mode: `./start.sh`

Stop dev server using `./stop.sh`

Clean container
=============
Solution #1

`docker-compose down` in `src/docker`

Solution #2

`docker stop vertxwmdb`

`docker rm vertxwmdb`