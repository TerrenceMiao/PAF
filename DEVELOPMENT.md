# How to build and run application as Java application
```
terrence@Silencer ~/Projects/PAF
00:20:42 1 $ $GRADLE/bin/gradle clean build

terrence@Silencer ~/Projects/PAF
00:21:42 2 $ $JAVA_HOME/bin/java -Djava.security.egd=file:/dev/./urandom -jar build/libs/paf-1.0.0-SNAPSHOT.jar
```
Then go to: [http://localhost:8080/](http://localhost:8080/)


# How to build and run application in Docker #

On Mac OS X, start docker Quickstart Terminal, then run:

```
terrence@Silencer ~/Projects/PAF
00:27:42 6 $ docker-machine env default
export DOCKER_TLS_VERIFY="1"
export DOCKER_HOST="tcp://192.168.99.100:2376"
export DOCKER_CERT_PATH="/Users/terrence/.docker/machine/machines/default"
export DOCKER_MACHINE_NAME="default"

terrence@Silencer ~/Projects/PAF
00:39:05 9 $ ifconfig
... ...
vboxnet0: flags=8943<UP,BROADCAST,RUNNING,PROMISC,SIMPLEX,MULTICAST> mtu 1500
	ether 0a:00:27:00:00:00
	inet 192.168.99.1 netmask 0xffffff00 broadcast 192.168.99.255
```
    * The Guest OS in run on IP 192.168.99.100
    * Docker client or Host is on gateway IP 192.168.99.1

```
terrence@Silencer ~/Projects/PAF
00:53:01 8 $ /usr/local/gradle-2.9/bin/gradle clean build buildDocker -x test
...
:build
:buildDocker
Sending build context to Docker daemon 46.06 MB
Step 1 : FROM java:8
 ---> d4849089125b
Step 2 : VOLUME /tmp
 ---> Using cache
 ---> 748468154f06
Step 3 : ADD paf-1.0.0-SNAPSHOT.jar app.jar
 ---> 9ce15ff3e952
Removing intermediate container 4e6c0f315943
Step 4 : RUN bash -c 'touch /app.jar'
 ---> Running in 3843a22ef374
 ---> beca43c5cbb5
Removing intermediate container 3843a22ef374
Step 5 : ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar /app.jar
 ---> Running in d0e03dc9df6f
 ---> 75345cb98bdc
Removing intermediate container d0e03dc9df6f
Successfully built 75345cb98bdc

terrence@Silencer ~/Projects/PAF
00:55:01 9 $ docker run -e "SPRING_PROFILES_ACTIVE=docker" -p 8080:8080 -t org.paradise/paf:1.0.0-SNAPSHOT /Users/terrence/Projects/PAF/build/docker

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.2.7.RELEASE)

2016-02-04 12:22:56.550  INFO 1 --- [           main] org.paradise.microservice.App            : Starting App on f2b654cc8075 with PID 1 (/app.jar started by root in /)
...

terrence@Silencer ~
23:27:02 153 𝜆 docker-machine ssh default
                        ##         .
                  ## ## ##        ==
               ## ## ## ## ##    ===
           /"""""""""""""""""\___/ ===
      ~~~ {~~ ~~~~ ~~~ ~~~~ ~~~ ~ /  ===- ~~~
           \______ o           __/
             \    \         __/
              \____\_______/
 _                 _   ____     _            _
| |__   ___   ___ | |_|___ \ __| | ___   ___| | _____ _ __
| '_ \ / _ \ / _ \| __| __) / _` |/ _ \ / __| |/ / _ \ '__|
| |_) | (_) | (_) | |_ / __/ (_| | (_) | (__|   <  __/ |
|_.__/ \___/ \___/ \__|_____\__,_|\___/ \___|_|\_\___|_|
Boot2Docker version 1.9.1, build master : cef800b - Fri Nov 20 19:33:59 UTC 2015
Docker version 1.9.1, build a34a1d5

docker@default:~$ ps axuw | grep java
root      1155 26.9 14.6 2533744 299672 pts/1  Ssl+ 12:26   0:31 java -Djava.security.egd=file:/dev/./urandom -jar /app.jar /Users/terrence/Projects/PAF/build/docker
```
Then go to: [http://192.168.99.100:8080/](http://192.168.99.100:8080/)

# Swagger interface URLs #

API docs - [http://localhost:8080/v2/api-docs](http://localhost:8080/v2/api-docs)

API UI - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Swagger Resources - [http://localhost:8080/swagger-resources](http://localhost:8080/swagger-resources)
