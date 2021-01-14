package UITestFramework;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import logger.Log;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;



public class CreateSession  {

	public WebDriver driver = null;
	Properties configFile ;
	protected static Properties lobConfigProp = new Properties();
	protected static Properties localeConfigProp = new Properties();
	protected FileInputStream configFis, lobConfigFis, localeConfigFis;	
	public Properties testDataFile;
	private final String CONFIG_FILE_PATH="//src//main//java//config//config.properties";
	protected File file = new File("");
	Properties configProp = new Properties();
	String OS;



	public void invokeAppium() throws Exception
	{
		String OS = System.getProperty("os.name").toLowerCase();
		try{
			startAppiumServer(OS);
			Log.info("Appium server started successfully");
		}
		catch (Exception e) {
			Log.logError(getClass().getName(), "startAppium", "Unable to start appium server");
			throw new Exception(e.getMessage());
		}
	}


	public void stopAppium() throws Exception {
		try{
			stopAppiumServer(OS);
			Log.info("Appium server stopped successfully");

		}
		catch (Exception e) {
			Log.logError(getClass().getName(), "stopAppium", "Unable to stop appium server");
			throw new Exception(e.getMessage());
		}
	}


	@Parameters({"os"})
	@BeforeMethod
	public  void createDriver(String os, Method methodName) throws Exception{

		propertiesFileLoad(os);

		File propertiesFile = new File(file.getAbsoluteFile() + "//src//main//java//log4j.properties");
		PropertyConfigurator.configure(propertiesFile.toString());
		Log.info("--------------------------------------");



		if (os.equalsIgnoreCase("android")){
			String buildPath = choosebuild(os);
			androidDriver(buildPath, methodName);
			Log.info("Android driver created");

		}																		         
		else if (os.equalsIgnoreCase("iOS")){
			String buildPath = choosebuild(os);
			iOSDriver(buildPath, methodName);
			Log.info("iOS driver created");
		}
	}


	@AfterMethod
	public void teardown(){
		Log.info("Shutting down driver");
		driver.quit();
	}




	public synchronized void androidDriver(String buildPath, Method methodName) throws MalformedURLException{
		File app = new File(buildPath);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "emulator-5554");
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
		capabilities.setCapability("automationName", "UiAutomator2");
		driver = new AndroidDriver( new URL("http://localhost:4723/wd/hub"), capabilities);

	}


	public void iOSDriver(String buildPath, Method methodName) throws MalformedURLException {
		File app = new File(buildPath);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName","iOS");
		capabilities.setCapability("platformVersion", "13.1");
		capabilities.setCapability("appiumVersion", "1.18.2");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"iPhone 11"); 
		capabilities.setCapability("app", app.getAbsolutePath());
		driver  = new IOSDriver( new URL("http://localhost:4723/wd/hub"), capabilities);

	}



	public void startAppiumServer(String os) throws ExecuteException, IOException, InterruptedException{
		if (os.contains("windows")){
			CommandLine command = new CommandLine("cmd");  
			command.addArgument("/c");  
			command.addArgument("C:/Program Files/nodejs/node.exe");  
			command.addArgument("C:/Appium/node_modules/appium/bin/appium.js");  
			command.addArgument("--address", false);  
			command.addArgument("127.0.0.1");  
			command.addArgument("--port", false);  
			command.addArgument("4723");  
			command.addArgument("--full-reset", false);  

			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();  
			DefaultExecutor executor = new DefaultExecutor();  
			executor.setExitValue(1);  
			executor.execute(command, resultHandler);  
			Thread.sleep(5000);  
		}
		else if (os.contains("mac os x")){
			CommandLine command = new CommandLine("/Applications/Appium.app/Contents/Resources/node/bin/node");  
			command.addArgument("/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js", false);  
			command.addArgument("--address", false);  
			command.addArgument("127.0.0.1");  
			command.addArgument("--port", false);  
			command.addArgument("4723");  
			command.addArgument("--full-reset", false);  
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();  
			DefaultExecutor executor = new DefaultExecutor();  
			executor.setExitValue(1);  
			executor.execute(command, resultHandler);  
			Thread.sleep(5000);  
		}
		else{
			Log.info(os + "is not supported yet");
		}
	}

	public void stopAppiumServer(String os) throws ExecuteException, IOException {
		if (os.contains("windows")){
			CommandLine command = new CommandLine("cmd");  
			command.addArgument("/c");  
			command.addArgument("Taskkill /F /IM node.exe");  

			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();  
			DefaultExecutor executor = new DefaultExecutor();  
			executor.setExitValue(1);  
			executor.execute(command, resultHandler);  
		}
		else if (os.contains("mac os x")){
			String[] command ={"/usr/bin/killall","-KILL","node"};  
			Runtime.getRuntime().exec(command);  
			Log.info("Appium server stopped");  
		}
	}


	public void propertiesFileLoad(String platform) throws Exception{
		configFis = new FileInputStream(file.getAbsoluteFile()
				+ CONFIG_FILE_PATH);
		configProp .load(configFis);

		File f = new File(file.getAbsoluteFile() + "//src//main//java//config//" + platform
				+ "_config.properties");

		if (f.exists() && !f.isDirectory()) {
			lobConfigFis = new FileInputStream(file.getAbsoluteFile()
					+ "/src//main//java//config//" + platform + "_config.properties");
			lobConfigProp.load(lobConfigFis);

			String locale = lobConfigProp.getProperty("LOCALE");

			localeConfigFis = new FileInputStream(file.getAbsoluteFile()
					+ "//src//main//java//testData//" + locale + "_" + platform  + ".properties");
			localeConfigProp.load(localeConfigFis);
		} 
		else {
			throw new Exception("Properties files loading failed ");
		}}

	public String choosebuild(String invokeDriver){
		String appPath = null;
		if(invokeDriver.equals("android")){
			appPath = configProp.getProperty("AndroidAppPath");
			return appPath;
		}
		else if(invokeDriver.equals("iOS")){
			appPath = configProp.getProperty("iOSAppPath");
			return appPath;
		}

		return appPath;
	}



}

