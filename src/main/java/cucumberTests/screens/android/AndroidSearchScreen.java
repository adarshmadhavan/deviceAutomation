package cucumberTests.screens.android;

import UITestFramework.GenericMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class AndroidSearchScreen extends GenericMethods {

	public AndroidSearchScreen(WebDriver driver) {
		super(driver);
	}
	

	// Locators on the search screen
	public By searchButton = By.xpath("//android.widget.ImageView[@content-desc=\"Search\"]");
	public By searchButtonTxt = By.id("com.sensynehealth.hospitals:id/search_src_text");
	public By hospitalList = By.id("com.sensynehealth.hospitals:id/list");
	public By closeButton = By.id("com.sensynehealth.hospitals:id/search_close_btn");
	
	public By navigateBack = By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]");
	public By hospitalName = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TextView[1]");
	public By subType = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TextView[2]");
	public By sector = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TextView[3]");
	public By address = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TextView[4]");
	public By phoneNumber = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TextView[7]");
	
	
}

