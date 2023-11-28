package challenge.automation.testing;

import java.time.Duration;

import challenge.automation.testing.functional.Challenge;
import challenge.automation.testing.profile.ChallengePage;
import challenge.automation.testing.util.WebDriverUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


@RunWith(Suite.class)
@Suite.SuiteClasses({
		Challenge.class
})
public class MainTest {

	/*
	 * WebDriver
	 */
	public static WebDriver webDriver; 
	
	/*
	 * Pages Profiles
	 */
	public static ChallengePage challengePage;
 
	/*
	 * Automation Config
	 */
	public static WebDriverUtil webDriverManager = new WebDriverUtil();

	private static void initProfiles() {
		challengePage = PageFactory.initElements(webDriver, ChallengePage.class);
	}


	@BeforeClass
	public static void setUp() throws Exception {
		System.out.println("*** Starting Automation *** ");
		webDriver = webDriverManager.getWebDriver();
		webDriver.manage().deleteAllCookies();
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		//webDriver.manage().window().maximize();
		initProfiles();
	}

	@AfterClass
	public static void finish() {
		//webDriverManager.quit();
		System.out.println("*** Automation completed *** ");
	}
}

 
