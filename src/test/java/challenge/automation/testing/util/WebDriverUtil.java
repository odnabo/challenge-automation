package challenge.automation.testing.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.io.ByteArrayInputStream;

public class WebDriverUtil {

	private WebDriver webDriver = null;
	private String browser = "chrome";

	public WebDriverUtil() {
		this.browser = System.getenv("BROWSER") == null ? browser : System.getenv("BROWSER");
	}

	public WebDriver getWebDriver() throws Exception {
		switch (browser){
			case "safari":
				System.setProperty("webdriver.safari.noinstall", "true");
				webDriver = new SafariDriver();
				break;

			case "edge":
				WebDriverManager.edgedriver().setup();
				this.webDriver = new EdgeDriver();
				break;

			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				this.webDriver = new FirefoxDriver();
				break;

			case "chrome":
				WebDriverManager.chromedriver().setup();
				this.webDriver = new ChromeDriver();
				break;

			default:
				throw new Exception(browser + " not supported");
		}

		return this.webDriver;
	}

	public void takeScreenshot() {
		if (this.webDriver != null) {
			Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) this.webDriver).getScreenshotAs(OutputType.BYTES)));
		}
	}

	public void quit() {
		if(this.webDriver != null) {
			this.webDriver.close();
		}
	}
}