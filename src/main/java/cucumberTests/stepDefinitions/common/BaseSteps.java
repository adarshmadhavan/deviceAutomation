package cucumberTests.stepDefinitions.common;

import cucumber.api.java.en.Given;
import cucumberTests.CreateSessionCucumber;
import cucumberTests.CucumberRunnerUtil;
import cucumberTests.screens.android.AndroidSearchScreen;
import cucumberTests.screens.iOS.IOSSearchScreen;

import org.openqa.selenium.WebDriver;

public class BaseSteps {
    CreateSessionCucumber createSessionCucumber;
    WebDriver driver;
    String platform;
    AndroidSearchScreen androidSearchScreen;
    IOSSearchScreen iosSearchScreen;


    @Given("^User has sensyne \"([^\"]*)\" app$")
    public void userHasSensyneApp(String invokeDriver) throws Exception {

        platform = invokeDriver;
        createSessionCucumber = new CreateSessionCucumber();
        createSessionCucumber.createDriver(invokeDriver, BaseSteps.class.getDeclaredMethod("userHasSensyneApp",String.class));
        driver = createSessionCucumber.getWebDriver();

        if (invokeDriver.equalsIgnoreCase("android")) {
        	androidSearchScreen = new AndroidSearchScreen(driver);
        } else if (invokeDriver.equalsIgnoreCase("iOS")) {
        	iosSearchScreen = new IOSSearchScreen(driver);
        }
    }

}
