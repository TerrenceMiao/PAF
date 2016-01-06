# How Gradle works?

Take a new greenfield project for example. Assume an old Gradle version 2.4 on PC. New project requires Gradle 2.9.

```
$ mkdir HelloGradle

$ cd HelloGradle

# initiate the skeleton of the Gradle project
$ $GRADLE_HOME/bin/gradle init -–type java-library

$ $GRADLE_HOME/bin/gradle-2.4/bin/gradle wrapper --gradle-version 2.9

$ cat gradle/wrapper/gradle-wrapper.properties
#Wed Jan 06 14:35:15 AEDT 2016
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-2.9-bin.zip

$ ./gradlew.bat clean
Downloading https://services.gradle.org/distributions/gradle-2.9-bin.zip
.......
Unzipping /Users/miaot/.gradle/wrapper/dists/gradle-2.9-bin/1lebsnfoptv8qpa10w6kyy5mp/gradle-2.9-bin.zip to /Users/miaot/.gradle/wrapper/dists/gradle-2.9-bin/1lebsnfoptv8qpa10w6kyy5mp
:clean UP-TO-DATE

BUILD SUCCESSFUL

$ ./gradlew.bat –version
------------------------------------------------------------
Gradle 2.9
------------------------------------------------------------

Build time:   2015-11-17 07:02:17 UTC
Build number: none
Revision:     b463d7980c40d44c4657dc80025275b84a29e31f

Groovy:       2.4.4
Ant:          Apache Ant(TM) version 1.9.3 compiled on December 23 2013
JVM:          1.8.0_66 (Oracle Corporation 25.66-b18)
OS:           Mac OS X 10.1.5 amd64
```

Download Gradle binary into $HOME/.gradle is once-off task. If $HOME/.gradle directory already has matched version Gradle binary release, gradlew is happily pick up and run the tasks straight forward.

