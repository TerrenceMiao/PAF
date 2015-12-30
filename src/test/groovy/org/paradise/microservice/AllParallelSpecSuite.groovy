package org.paradise.microservice

import com.googlecode.junittoolbox.ParallelSuite
import com.googlecode.junittoolbox.SuiteClasses
import org.junit.runner.RunWith
import spock.lang.Specification

/**
 * Created by terrence on 26/12/2015.
 */
@RunWith(ParallelSuite.class)
@SuiteClasses("**/*Spec.class")
class AllParallelSpecSuite extends Specification {
}
