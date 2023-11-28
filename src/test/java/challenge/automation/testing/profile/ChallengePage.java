package challenge.automation.testing.profile;

import challenge.automation.testing.util.Utils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;

import java.util.List;

public class ChallengePage {

    @FindBy(how = How.ID, using = "user-name")
    private WebElement txtUserName;
    @FindBy(how = How.ID, using = "password")
    private WebElement txtPassword;
    @FindBy(how = How.ID, using = "login-button")
    private WebElement btnLogin;
    @FindBy(how = How.CLASS_NAME, using = "error-message-container")
    private WebElement containerError;
    @FindBy(how = How.CLASS_NAME, using = "title")
    private WebElement spanTitle;
    @FindBy(how = How.ID, using = "react-burger-menu-btn")
    private WebElement btnBurgerMenu;
    @FindBy(how = How.ID, using = "logout_sidebar_link")
    private WebElement navLogout;
    @FindBys({@FindBy(how = How.CLASS_NAME, using = "inventory_item")})
    private List<WebElement> listShopingItems;
    @FindBy(how = How.CLASS_NAME, using = "shopping_cart_link")
    private WebElement btnShopingCart;
    @FindBys({@FindBy(how = How.CLASS_NAME, using = "cart_item")})
    private List<WebElement> listCheckoutCartItems;
    @FindBy(how = How.ID, using = "checkout")
    private WebElement btnCheckout;
    @FindBy(how = How.ID, using = "first-name")
    private WebElement inputFirstName;
    @FindBy(how = How.ID, using = "last-name")
    private WebElement inputLastName;
    @FindBy(how = How.ID, using = "postal-code")
    private WebElement inputZipCode;
    @FindBy(how = How.ID, using = "continue")
    private WebElement btnContinueCheckout;
    @FindBy(how = How.ID, using = "finish")
    private WebElement btnFinish;


    /**
     * Methods
     */
    @Step("Enter username")
    public void enterUser(String value){
        txtUserName.sendKeys(value);
    }
    @Step("Enter password")
    public void enterPassword(String value){
        txtPassword.sendKeys(value);
    }
    @Step("Click Login")
    public void clickBtnLogin(){
        btnLogin.click();
    }
    public boolean isVisibleError(){
        return Utils.isVisibleElement(containerError);
    }
    public String getVisibleError(){
        return containerError.getText().toString().trim();
    }
    @Step("Successful Login")
    public boolean isVisibleTitle(){
        return Utils.isVisibleElement(spanTitle);
    }
    @Step("Successful Logout")
    public void logout(){
        btnBurgerMenu.click();
        navLogout.click();
    }
    public void randomItemsListClass(List<WebElement> list){
        int random = Utils.randomNumber(0, list.size());
        for (int i = 0; i < list.size(); i++) {
            if (i == random) {
                String howUsing = list.get(i).getAttribute("class");
                WebElement btnListClass = list.get(i).findElement(By.className(howUsing));
                btnListClass.click();
            }
        }
    }
    @Step("Select Items to Cart")
    public void selectRandomItemsToCart(){
        int cantRandom = Utils.randomNumber(0, 5);
        for(int x=0; x<cantRandom; x++) {
            randomItemsListClass(listShopingItems);
        }
    }
    @Step("Remove Items to Cart")
    public void removeRandomItemToCart(){
        int cantRandom = Utils.randomNumber(0, listCheckoutCartItems.size());
        for(int x=0; x<cantRandom; x++) {
            randomItemsListClass(listCheckoutCartItems);
        }
    }
    @Step("Start Checkout")
    public void clickShopingCart(){
        btnShopingCart.click();
    }

    @Step("Enter data for shoping cart")
    public void checkout(String firstName, String lastName, String zipCode){
        btnCheckout.click();
        inputFirstName.sendKeys(firstName);
        inputLastName.sendKeys(lastName);
        inputZipCode.sendKeys(zipCode);
        btnContinueCheckout.click();
    }
    @Step("Finish Checkout")
    public void clickFinishCart(){
        btnFinish.click();
    }
}
