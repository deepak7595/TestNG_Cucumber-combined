package rahulShetty.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulShetty.TestComponents.BaseTest;
import rahulShettyacademy.pageobjects.CartPage;
import rahulShettyacademy.pageobjects.LoginPage;
import rahulShettyacademy.pageobjects.OrderConfirmationPage;
import rahulShettyacademy.pageobjects.PaymentPage;
import rahulShettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImplementation extends BaseTest {

	public LoginPage loginPage;
	public ProductCatalogue productCatalogue;
	public PaymentPage paymentPage;
	OrderConfirmationPage confirmationPage;
	
	
	String confirmationMsg = "THANKYOU FOR THE ORDER.";
	String productName = "ZARA COAT 3";

	/**
	 * we are using the reusable codes from baseTest.
	 * 
	 */

	@Given("i landed on Ecommerce Page")
	public void i_landed_on_ecommerce_page() throws IOException {

		loginPage = launchApplication();
	}

	/**
	 * (.+) this means, some value will be replaced during the execution. This will work, only when we drive the data from EXAMPLES.
	 * 
	 */

	@Given("^Logged in with (.+) and  (.+)$")
	public void logged_in_with_username_and_password_deepak(String username, String password) {
		productCatalogue = LoginPage.loginApplication(username, password);
	}

	@When("^i add the product ZARA COAT (.+) to cart$")
	public void i_add_the_product_to_cart(String productName) {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}

	@And("^Checkout ZARA COAT (.+) and sumbit the order$")
	public void checkout_zara_coat_and_sumbit_the_order(String productName) {
		CartPage cartPage = productCatalogue.ClkToCart();
//		boolean productPresent = cartPage.productPresent(productName);
//		Assert.assertTrue(productPresent);
		paymentPage = cartPage.Checkout_btn();
		paymentPage.selectCountry("se");
		paymentPage.selectCountry_DropdownOption();
		confirmationPage = paymentPage.placeOrder_btn();
	}

	
	/**
	 * if we drive the data on the step itself. we can use "{string}". Example below
	 * 
	 */
	@Then("verify the success message as {string} displayed on confirmation Page.")
	public void verify_the_success_message_as_displayed_on_confirmation_page(String confirmationMsg) {
		String orderConfirmationMsg = confirmationPage.getOrderConfirmationMsg();
		Assert.assertEquals(orderConfirmationMsg, confirmationMsg, "Content Matching");
		System.out.println(orderConfirmationMsg);
		driver.close();
	}
	
	
	
	@Then("Verify the Error message {string}")
	public void verify_the_error_message(String string) {
		String IncorrectMsg = "Incorrect email or password.";
		Assert.assertEquals(LoginPage.getErrorMessage(), IncorrectMsg, "Matching");
		System.out.println("Done");
		driver.close();
	}

}
