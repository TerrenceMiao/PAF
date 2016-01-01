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

terrence@Silencer ~/Projects/PAF
00:55:01 9 $ docker run -e "SPRING_PROFILES_ACTIVE=docker" -p 8080:8080 -t org.paradise/paf:1.0.0-SNAPSHOT /Users/terrence/Projects/PAF/build/docker
```
Then go to: http://192.168.99.100:8080/