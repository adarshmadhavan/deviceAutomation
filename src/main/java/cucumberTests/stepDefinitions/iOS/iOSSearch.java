package cucumberTests.stepDefinitions.iOS;

import UITestFramework.CreateSession;
import cucumberTests.screens.android.AndroidSearchScreen;
import cucumberTests.screens.iOS.IOSSearchScreen;

public class iOSSearch {
    IOSSearchScreen iosSearchScreen;

    public iOSSearch(CreateSession createSession) {
    	iosSearchScreen = new IOSSearchScreen(createSession.driver);
    }
}
