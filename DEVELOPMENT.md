# How to test #

  * Run Test Suite in parallel mode

```
$GRADLE_HOME/bin/gradle clean -Dtest.single=AllParallelTestSuite test
```

  * Run Test Suite in sequential mode

```
$GRADLE_HOME/bin/gradle clean -Dtest.single=AllTestSuite test
```

  * Test Suite run in parallel, sequential mode in IntelliJ comparison

[alt text][parallelTestSuite]

[parallelTestSuite]: https://raw.githubusercontent.com/TerrenceMiao/PAF/master/doc/All%20tests%20run%20in%20parallel.png "Test Suite run in parallel"

[alt text][sequentialTestSuite]

[sequentialTestSuite]: https://raw.githubusercontent.com/TerrenceMiao/PAF/master/doc/All%20tests%20run%20in%20sequential.png "Test Suite run in sequential"
