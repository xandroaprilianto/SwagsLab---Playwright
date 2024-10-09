import com.microsoft.playwright.Page;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.LoginPO;
import pageobjects.ProductsPO;
import utilities.PlaywrightManager;

public class SelectItems {

    private PlaywrightManager manager;
    Page page;
    LoginPO loginPO;
    private ProductsPO productsPO;

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
    public void Select2Items(){
        productsPO.validateItemIsDisplayed(6);
        productsPO.memberAddToChart2Product();
    }

    @Test
    public void Select3Items(){
        productsPO.validateItemIsDisplayed(6);
        productsPO.memberAddToChart3Product();
    }

    @Test
    public void Select4Items(){
        productsPO.validateItemIsDisplayed(6);
        productsPO.memberAddToChart4Product();
    }

    @AfterClass
    public void tearDown() {
        manager.closeBrowser(); // Menutup browser setelah semua pengujian selesai
    }
}
