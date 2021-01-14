package cucumberTests.stepDefinitions.android;

import UITestFramework.CreateSession;
import cucumberTests.screens.android.AndroidSearchScreen;

public class AndroidSearch {
    AndroidSearchScreen androidSearchScreen;


    public AndroidSearch(CreateSession createSession) {
    	androidSearchScreen = new AndroidSearchScreen(createSession.driver);
    }

}
