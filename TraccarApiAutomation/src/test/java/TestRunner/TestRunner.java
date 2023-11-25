package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features="./src/test/java/cucumberOptions/",
glue={"stepDefinations"})
public class TestRunner {

}

