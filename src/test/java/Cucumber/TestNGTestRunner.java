package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * features will have, where the feature file present. And glue holds the information about, where the step definition present
 */
@CucumberOptions(features = "src/test/java/Cucumber", glue = "rahulShetty.stepDefinitions", 
monochrome = true, plugin = {"html:target/cucumber.html"}, tags = "@Regression")

public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
