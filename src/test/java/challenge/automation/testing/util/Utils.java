package challenge.automation.testing.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import challenge.automation.testing.MainTest;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

	public static void execJS(String js) {
		((JavascriptExecutor) MainTest.webDriver).executeScript(js); 
	}

	public static void execJS(String js, Object... args) {
		((JavascriptExecutor) MainTest.webDriver).executeScript(js, args);
	}

	public static void setValueInputJS(WebElement input, String dato) {
		execJS("arguments[0].value = '" + dato + "';", input);
	}

	public static void clickJS(WebElement elemento) {
		execJS("arguments[0].click();", elemento);
	}

	public static void sleep(int segundos, Class<?> clazz) {
		sleepms(segundos * 1000, clazz);
	}
	
	public static int randomNumber(int min, int max) {
		return (int)Math.floor(Math.random()*(max-min+1)+min);
	}
	
	public static void sleepms(int milisegundos, Class<?> clazz) {
		synchronized (clazz) {
			try {
				clazz.wait(milisegundos);
			} catch (InterruptedException e) {

			}
		}
	}

	public static boolean isVisibleElement(WebElement elemento) {
		try {
			return elemento.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isEnabledElement(WebElement elemento) {
		try {
			return elemento.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}
	
	public static List<String> getOpcionesSelect(WebElement selector){
		return new Select(selector).getOptions().stream().map(el -> el.getText().trim()).collect(Collectors.toList());
	}

	public static String getRutaActual() {
		return System.getProperty("user.dir");
	}

	public static void clickIzquierdo(WebElement el) {
		try {
			el.click();
		} catch (Exception e) {
			System.out.println("Falló click selenium");
			try {
			clickJS(el);
			}catch(Exception ee) {}
		}
	}
	
	public static String formatearMoneda(String monto, String sepMiles, String sepDecimal) {
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator(sepDecimal.charAt(0)); 
        otherSymbols.setGroupingSeparator(sepMiles.charAt(0)); 
        DecimalFormat df = new DecimalFormat("###,###", otherSymbols);
        return df.format(Double.parseDouble(monto.replaceAll(Constants.REGEX_ONLY_NUMBERS,"")));
	}
	
	public static void hoverMouse(WebElement elemento) {
		Utils.sleep(2, Utils.class);
		Actions accionHover = new Actions(MainTest.webDriver);
		accionHover.moveToElement(elemento).build().perform();
		Utils.sleep(2, Utils.class);
	}
	
	public static void borrarBackSpace(WebElement el) {
		int largoTexto = el.getAttribute("value").length();
		for(int x = 0; x < largoTexto; x++) {
			el.sendKeys(Keys.BACK_SPACE);
		}
		el.sendKeys(Keys.BACK_SPACE);
	}
	 	
	public static WebElement webElementByXpath(String xpath) {
		return MainTest.webDriver.findElement(By.xpath(xpath));
	}

	public static void waitForElement(WebDriver webDriver, WebElement element, long seconds){
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
}
