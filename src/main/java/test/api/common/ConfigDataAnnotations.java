package test.api.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***********************************************************************************************
 * Meta data for method to Results Tagging. 
 * List of data points : testName, component, expectedResutls, environments, scope, testType, releaseAdded, labels, comment
 * @author atulsharma
 * ********************************************************************************************/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ConfigDataAnnotations {

	String testName() default "";
	String component() default "API";
	String expectedResult() default "";
	String [] environments() default {"ITE"};
	String [] scope() default {"Sanity","Regression Test"};
	String testType() default "Automated";
	String releaseAdded() default "";
	String labels() default "API";
	String comment() default "";
}
