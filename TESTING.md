# How to test #

  * Run Test Suite in parallel mode on Gradle command line

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
