# How to test #

  * Run Test Suite in parallel mode on Gradle command line

```
$GRADLE_HOME/bin/gradle clean -Dtest.single=AllParallelTestSuite test
```

  * Run Test Suite in sequential mode on Gradle command line

![alt text][parallelTestSuite]

[parallelTestSuite]: https://raw.githubusercontent.com/TerrenceMiao/PAF/master/doc/All%20tests%20run%20in%20parallel.png "Test Suite run in parallel"

```
$GRADLE_HOME/bin/gradle clean -Dtest.single=AllTestSuite test
```

  * Test Suite run in parallel, sequential mode in IntelliJ comparison

![alt text][sequentialTestSuite]

[sequentialTestSuite]: https://raw.githubusercontent.com/TerrenceMiao/PAF/master/doc/All%20tests%20run%20in%20sequential.png "Test Suite run in sequential"
