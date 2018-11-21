package runners;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;
import com.utils.managers.FileReaderManager;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(
		features = "src/test/resources/features",
		glue = {"stepdefinitions"},
		monochrome = true,
		plugin = {"pretty",
//				"html:target/cucumber-reports",
				"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"
		},
//		tags = {"@flightSearch, @flightBooking, @signup, @reservation"}
		tags = {"@reservation"}
		)

public class TestRunner {
	@AfterClass
	public static void writeExtentReport() {
		Reporter.loadXMLConfig(new File(FileReaderManager.getInstance().getConfigReader().getReportConfigPath()));
		
	}
}

