package challenge.automation.testing.functional;

import challenge.automation.testing.MainTest;
import challenge.automation.testing.util.Constants;
import challenge.automation.testing.util.UnirestUtils;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONArray;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestWatcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import io.qameta.allure.Description;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Challenge {

    @Test
    @Description("Login - Empty User")
    public void test1_login_emptyUser(){
        MainTest.webDriver.get(Constants.URL_CHALLENGE);
        MainTest.challengePage.enterUser("");
        MainTest.challengePage.enterPassword("");
        MainTest.challengePage.clickBtnLogin();
        assertTrue(MainTest.challengePage.isVisibleError());
        assertEquals("Epic sadface: Username is required", MainTest.challengePage.getVisibleError());
    }
    @Test
    @Description("Login - Locked User")
    public void test2_login_lockedUser(){
        MainTest.webDriver.get(Constants.URL_CHALLENGE);
        MainTest.challengePage.enterUser("locked_out_user");
        MainTest.challengePage.enterPassword("secret_sauce");
        MainTest.challengePage.clickBtnLogin();
        assertTrue(MainTest.challengePage.isVisibleError());
        assertEquals("Epic sadface: Sorry, this user has been locked out.", MainTest.challengePage.getVisibleError());
    }
    @Test
    @Description("Login - Correct User")
    public void test3_login_correctUser(){
        MainTest.webDriver.get(Constants.URL_CHALLENGE);
        MainTest.challengePage.enterUser("standard_user");
        MainTest.challengePage.enterPassword("secret_sauce");
        MainTest.challengePage.clickBtnLogin();
        assertTrue(MainTest.challengePage.isVisibleTitle());
    }
    @Test
    @Description("Logout - Correct User")
    public void test4_login_logoutCorrectUser(){
        MainTest.webDriver.get(Constants.URL_CHALLENGE);
        MainTest.challengePage.enterUser("standard_user");
        MainTest.challengePage.enterPassword("secret_sauce");
        MainTest.challengePage.clickBtnLogin();
        assertTrue(MainTest.challengePage.isVisibleTitle());
        MainTest.challengePage.logout();
    }
    @Test
    @Description("Shoping Cart - Add Products To Cart")
    public void test5_shopingCart_correctUserAddProductsToCart(){
        MainTest.webDriver.get(Constants.URL_CHALLENGE);
        MainTest.challengePage.enterUser("standard_user");
        MainTest.challengePage.enterPassword("secret_sauce");
        MainTest.challengePage.clickBtnLogin();
        assertTrue(MainTest.challengePage.isVisibleTitle());
        MainTest.challengePage.selectRandomItemsToCart();
    }
    @Test
    @Description("Shoping Cart - Remove Products To Cart")
    public void test6_shopingCart_correctUserRemoveProductsToCart(){
        MainTest.webDriver.get(Constants.URL_CHALLENGE);
        MainTest.challengePage.enterUser("standard_user");
        MainTest.challengePage.enterPassword("secret_sauce");
        MainTest.challengePage.clickBtnLogin();
        assertTrue(MainTest.challengePage.isVisibleTitle());
        MainTest.challengePage.selectRandomItemsToCart();
        MainTest.challengePage.clickShopingCart();
        MainTest.challengePage.removeRandomItemToCart();
        MainTest.challengePage.checkout("Bárbara", "Obando", "7910489");
        MainTest.challengePage.clickFinishCart();
    }
    @Test
    @Description("Service - Verify Array Departments")
    public void test7_service_getDepartments(){
        HttpResponse<JsonNode> getDep = UnirestUtils.getDepartments();
        JSONArray departments = getDep.getBody().getObject().optJSONArray("departments");
        assertTrue(departments != null);
    }
    @Test
    @Description("Service - Verify length of Departments is more than zero")
    public void test8_service_getDepartments(){
        HttpResponse<JsonNode> getDep = UnirestUtils.getDepartments();
        JSONArray departments = getDep.getBody().getObject().optJSONArray("departments");
        assertTrue(departments.length() > 0);
    }

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, org.junit.runner.Description description) {
            MainTest.webDriverManager.takeScreenshot();
        }
        @Override
        protected void succeeded(org.junit.runner.Description description) {
        }
    };
}
