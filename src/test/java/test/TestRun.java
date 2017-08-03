package test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="C:\\Users\\Prave\\workspace2\\google_calendar\\src\\test\\java\\test\\TimeFormat.feature"
//		glue={"gui.guru99.test.stepDefn"}
		)
public class TestRun {

}
