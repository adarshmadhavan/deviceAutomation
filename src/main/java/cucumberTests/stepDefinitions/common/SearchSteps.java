package cucumberTests.stepDefinitions.common;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberTests.CreateSessionCucumber;
import cucumberTests.screens.android.AndroidSearchScreen;
import cucumberTests.screens.iOS.IOSSearchScreen;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

public class SearchSteps {
    AndroidSearchScreen androidLoginScreen;
    IOSSearchScreen iosLoginScreen;
    WebDriver driver;
    String searchString;
    String hospitalDetail;
    Properties configFileObject;
    BaseSteps baseStepsContext;



    public SearchSteps(BaseSteps baseSteps) {
        baseStepsContext = baseSteps;
        driver = baseStepsContext.driver;
        androidLoginScreen = baseStepsContext.androidSearchScreen;
        iosLoginScreen = baseStepsContext.iosSearchScreen;
        configFileObject = CreateSessionCucumber.localeConfigProp;
    }



    @And("user has \"([^\"]*)\" search string")
    public void usernameAndPasswordIs(String credentialsValidations) {
        if(credentialsValidations.equalsIgnoreCase("valid")){
        	searchString = configFileObject.getProperty("searchString");
        }
        else if(credentialsValidations.equalsIgnoreCase("inValid")){
        	searchString = configFileObject.getProperty("invalidSearch");
        }
        else{
        	searchString = configFileObject.getProperty("Ab");
        }
    }

    @When("user enters hospital name")
    public void userEntersData() {
        androidLoginScreen.waitForVisibility(androidLoginScreen.searchButton);
        androidLoginScreen.findElement(androidLoginScreen.searchButton).click();
        androidLoginScreen.findElement(androidLoginScreen.searchButtonTxt).sendKeys(searchString);
    }

    @And("taps on \"([^\"]*)\" list")
    public void tapsOnButton(String pageId) {
    	if (pageId.equalsIgnoreCase("dropdown"))
    	{
    		androidLoginScreen.waitForVisibility(androidLoginScreen.hospitalList);
    		androidLoginScreen.findElement(androidLoginScreen.hospitalList).click();
    	}
    	else if (pageId.equalsIgnoreCase("backButton"))
    	{
    		androidLoginScreen.waitForVisibility(androidLoginScreen.navigateBack);
    		androidLoginScreen.findElement(androidLoginScreen.navigateBack).click();
    	}
    	
    }
    
    
    @And("user lands on \"([^\"]*)\" screen")
    public void landingPageVerification(String elementVer) {
    	if (elementVer.equalsIgnoreCase("search"))
    	{
    		androidLoginScreen.waitForVisibility(androidLoginScreen.searchButton);
    	}
    	else if (elementVer.equalsIgnoreCase("backToSearch"))
    	{
    		androidLoginScreen.waitForVisibility(androidLoginScreen.searchButtonTxt);
    	}

    }
    

    @Then("\"([^\"]*)\" elements should be visible")
    public void elementsShouldBeVisible(String value) {
        
        if(value.equalsIgnoreCase("Data")) {
            androidLoginScreen.waitForVisibility(androidLoginScreen.hospitalName);
            String HospitalName = androidLoginScreen.findElement(androidLoginScreen.hospitalName).getText();
            String subType = androidLoginScreen.findElement(androidLoginScreen.subType).getText();
            String sector = androidLoginScreen.findElement(androidLoginScreen.sector).getText();
            String address = androidLoginScreen.findElement(androidLoginScreen.address).getText();
            String phone = androidLoginScreen.findElement(androidLoginScreen.phoneNumber).getText();
            assertEquals(configFileObject.getProperty("Name"), HospitalName);
            assertEquals(configFileObject.getProperty("subtype"), subType);
            assertEquals(configFileObject.getProperty("sector"), sector);
            assertEquals(configFileObject.getProperty("address"), address);
            assertEquals(configFileObject.getProperty("phone"), phone);
        }
        else if(value.equalsIgnoreCase("dropdown")) {
        	try {
				assertTrue(androidLoginScreen.isElementPresent(androidLoginScreen.hospitalList));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	;
        }
    }


}
