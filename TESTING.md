# How to test #

  * Run Test Suite in parallel mode on Gradle command line

Comment line:
```
exclude '**/*TestSuite.class'
```
in "test" task.

```
$GRADLE_HOME/bin/gradle clean -Dtest.single=AllParallelTestSuite test
```

![alt text][parallelTestSuite]

[parallelTestSuite]: https://raw.githubusercontent.com/TerrenceMiao/PAF/master/doc/All%20tests%20run%20in%20parallel.png "Test Suite run in parallel"

  * Run Test Suite in sequential mode on Gradle command line

```
$GRADLE_HOME/bin/gradle clean -Dtest.single=AllTestSuite test
```

![alt text][sequentialTestSuite]

[sequentialTestSuite]: https://raw.githubusercontent.com/TerrenceMiao/PAF/master/doc/All%20tests%20run%20in%20sequential.png "Test Suite run in sequential"

  * Only run BDD specification test

```
$GRADLE_HOME/bin/gradle clean -Dtest.single=*Spec test
```

  * Run BDD test Suite in parallel mode on Gradle command line

Comment line:
```
exclude '**/*SpecSuite.class'
```
in "test" task.
```
$GRADLE_HOME/bin/gradle clean -Dtest.single=AllParallelSpecSuite test
```

![alt text][parallelSpecSuite]

[parallelSpecSuite]: https://raw.githubusercontent.com/TerrenceMiao/PAF/master/doc/All%20BDD%20tests%20run%20in%20parallel.png "BDD Spec Suite run in parallel"

  * Run BDD test Suite in sequential mode on Gradle command line

```
$GRADLE_HOME/bin/gradle clean -Dtest.single=AllSpecSuite test
```

![alt text][sequentialSpecSuite]

[sequentialSpecSuite]: https://raw.githubusercontent.com/TerrenceMiao/PAF/master/doc/All%20BDD%20tests%20run%20in%20sequential.png "BDD Spec Suite run in sequential"

  * Run cross browsers Geb BDD test

```
$GRADLE_HOME/bin/gradle clean chromeTest

$GRADLE_HOME/bin/gradle clean firefoxTest

$GRADLE_HOME/bin/gradle clean phantomJsTest
```

Testing reports can be found under:

```
$PROJECT_HOME/build/reports/tests/index.html

$PROJECT_HOME/build/reports/chromeTest/
$PROJECT_HOME/build/reports/firefoxTest/
$PROJECT_HOME/build/reports/phantomJsTest/
```

Or BDD style testing reports under:

```
$PROJECT_HOME/build/spock-reports/index.html
```

  * Run test in using Gradle parallel

```
$GRADLE_HOME/bin/gradle --version

------------------------------------------------------------
Gradle 2.9
------------------------------------------------------------

Build time:   2015-11-17 07:02:17 UTC
Build number: none
Revision:     b463d7980c40d44c4657dc80025275b84a29e31f

Groovy:       2.4.4
Ant:          Apache Ant(TM) version 1.9.3 compiled on December 23 2013
JVM:          1.8.0_25 (Oracle Corporation 25.25-b02)
OS:           Mac OS X 10.10.5 x86_64


$GRADLE_HOME/bin/gradle --parallel --max-workers=2 clean test
```
