package pageobjects;

import org.testng.Assert;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import utilities.PlaywrightHelpers;

public class ProductsPO {

    private final Page page;

    private final PlaywrightHelpers pwHelper;

    public ProductsPO(Page page){
        this.page = page;
        this.pwHelper = new PlaywrightHelpers(page); // Inisialisasi PlaywrightHelpers

        // Inisialisasi locator
        initializeLocators();
    }

    private Locator inventoryContainer;
    private Locator inventoryList;
    private Locator invetoryItem;
    private String addToCartButton;
    private Locator cartButton;
    private Locator countCart;
    private Locator removeButton;
    private Locator checkoutButton;
    private Locator firstNameTextField;
    private Locator lastNameTextField;
    private Locator postalCodeTextField;
    private Locator continueButton;
    private Locator finishButton;

    private void initializeLocators(){
        this.inventoryContainer = page.locator("//div[@data-test='inventory-container']");
        this.inventoryList = page.locator("//div[@data-test='inventory-list']");
        this.invetoryItem = page.locator("//div[@data-test='inventory-item']");
        this.addToCartButton = "//div[@data-test='inventory-item']";
        this.cartButton = page.locator("//a[@data-test='shopping-cart-link']");
        this.countCart = page.locator("//span[@data-test='shopping-cart-badge']");
        this.removeButton = page.locator("//button[@data-test='remove-sauce-labs-bike-light']");
        this.checkoutButton = page.locator("//button[@data-test='checkout']");
        this.firstNameTextField = page.locator("//input[@data-test='firstName']");
        this.lastNameTextField = page.locator("//input[@data-test='lastName']");
        this.postalCodeTextField = page.locator("//input[@data-test='postalCode']");
        this.continueButton = page.locator("//input[@data-test='continue']");
        this.finishButton = page.locator("//button[@data-test='finish']");
    }

    public void validateItemIsDisplayed(int num){
        assertThat(inventoryContainer).isVisible();
        assertThat(inventoryList).isVisible();
        assertThat(invetoryItem).hasCount(num);
    }

    public void memberAddToChart2Product(){
        int count = 0;
        for (int i = 1; i <= 2; i++){
            count = 2;
            String newButtonAddToCart = String.format(addToCartButton+"[%d]//button", i);
            Locator newButton = page.locator(newButtonAddToCart);
            pwHelper.clickOn(newButton);
        }
        pwHelper.waitUntilVisible(countCart, 2);
        String getCount = countCart.innerText();
        int actualCount = Integer.parseInt(getCount);

        Assert.assertEquals(actualCount, count);
    }

    public void memberAddToChart3Product(){
        int count = 0;
        for (int i = 1; i <= 3; i++){
            count = 3;
            String newButtonAddToCart = String.format(addToCartButton+"[%d]//button", i);
            Locator newButton = page.locator(newButtonAddToCart);
            pwHelper.clickOn(newButton);
        }
        pwHelper.waitUntilVisible(countCart, 2);
        String getCount = countCart.innerText();
        int actualCount = Integer.parseInt(getCount);

        Assert.assertEquals(actualCount, count);
    }

    public void memberAddToChart4Product(){
        int count = 0;
        for (int i = 1; i <= 4; i++){
            count = 4;
            String newButtonAddToCart = String.format(addToCartButton+"[%d]//button", i);
            Locator newButton = page.locator(newButtonAddToCart);
            pwHelper.clickOn(newButton);
        }
        pwHelper.waitUntilVisible(countCart, 2);
        String getCount = countCart.innerText();
        int actualCount = Integer.parseInt(getCount);

        Assert.assertEquals(actualCount, count);
    }

    public void memberClickAddToCart(int num){
        String newButtonAddToCart = String.format(addToCartButton+"["+num+"]"+"//button");
        Locator newButton = page.locator(newButtonAddToCart);

        pwHelper.clickOn(newButton);
    }

    public void memberClickCartButton(){
        pwHelper.clickOn(cartButton);
    }

    public void memberRemoveProduct(){
        pwHelper.clickOn(removeButton);
    }

    public void memberClickCheckout(){
        pwHelper.clickOn(checkoutButton);
    }

    public void memberInputFirstNameInformation(String fName){
        pwHelper.enterText(firstNameTextField, fName, true);
    }

    public void memberInputLastNameInformation(String lName){
        pwHelper.enterText(lastNameTextField, lName, true);
    }

    public void memberInputPostalCodeInformation(String postalCode){
        pwHelper.enterText(postalCodeTextField, postalCode, true);
    }

    public void memberClickContinueButton(){
        pwHelper.clickOn(continueButton);
    }

    public void memberClickFinishButton(){
        pwHelper.clickOn(finishButton);
    }
}
