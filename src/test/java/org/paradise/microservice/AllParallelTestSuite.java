package org.paradise.microservice;

import com.googlecode.junittoolbox.ParallelSuite;
import com.googlecode.junittoolbox.SuiteClasses;
import org.junit.runner.RunWith;

/**
 * Created by terrence on 23/12/2015.
 */
@RunWith(ParallelSuite.class)
@SuiteClasses("**/*Test.class")
public class AllParallelTestSuite {

}
