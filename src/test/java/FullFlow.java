import com.microsoft.playwright.Page;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.LoginPO;
import pageobjects.ProductsPO;
import utilities.PlaywrightManager;

public class FullFlow {

    private PlaywrightManager manager;
    Page page;
    LoginPO loginPO;
    ProductsPO productsPO;

    @BeforeClass
    public void setUp() {
        manager = new PlaywrightManager();
        page = manager.launchBrowser("chromium"); // Mendapatkan objek Page
        page.navigate("https://www.saucedemo.com/");

        loginPO = new LoginPO(page);
        productsPO = new ProductsPO(page);

        loginPO.validatePageLogin("Swag Labs");
        loginPO.fillUsername("standard_user", true); // Mengisi username dan menghapus sebelumnya
        loginPO.fillPassword("secret_sauce", true); // Mengisi password dan menghapus sebelumnya
        loginPO.clickLoginButton();
        loginPO.validateSuccesLogin();
    }

    @Test
    public void fullFlow(){
        productsPO.memberClickAddToCart(2);
        productsPO.memberClickCartButton();
        productsPO.memberRemoveProduct();
        productsPO.memberClickCheckout();
        productsPO.memberInputFirstNameInformation("Automation");
        productsPO.memberInputLastNameInformation("Test");
        productsPO.memberInputPostalCodeInformation("99100");
        productsPO.memberClickContinueButton();
        productsPO.memberClickFinishButton();
    }

    @AfterClass
    public void tearDown() {
        manager.closeBrowser(); // Menutup browser setelah semua pengujian selesai
    }
}
