import com.microsoft.playwright.Page;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.LoginPO;
import utilities.PlaywrightManager;

public class LoginScenario {

    private PlaywrightManager manager;
    Page page;
    private LoginPO loginPO;

    @BeforeClass
    public void setUp() {
        manager = new PlaywrightManager();
        page = manager.launchBrowser("chromium"); // Mendapatkan objek Page
        page.navigate("https://www.saucedemo.com/");

        loginPO = new LoginPO(page);
    }

    @Test
    public void SuccessLogin(){
        loginPO.validatePageLogin("Swag Labs");
        loginPO.fillUsername("standard_user", true); // Mengisi username dan menghapus sebelumnya
        loginPO.fillPassword("secret_sauce", true); // Mengisi password dan menghapus sebelumnya
        loginPO.clickLoginButton();
        loginPO.validateSuccesLogin();
    }

    @Test
    public void ValidateUserNameIsMandatory(){
        loginPO.validatePageLogin("Swag Labs");
        loginPO.fillUsername("", true); // Mengisi username dan menghapus sebelumnya
        loginPO.fillPassword("secret_sauce", true); // Mengisi password dan menghapus sebelumnya
        loginPO.clickLoginButton();
        loginPO.validateUsernameIsMandatory("Epic sadface: Username is required");
    }

    @Test
    public void ValidatePasswordIsMandatory(){
        loginPO.validatePageLogin("Swag Labs");
        loginPO.fillUsername("standard_user", true); // Mengisi username dan menghapus sebelumnya
        loginPO.fillPassword("", true); // Mengisi password dan menghapus sebelumnya
        loginPO.clickLoginButton();
        loginPO.validatePasswordIsMandatory("Epic sadface: Password is required");
    }

    @Test
    public void FailedLoginUsingLockedAccount(){
        loginPO.validatePageLogin("Swag Labs");
        loginPO.fillUsername("locked_out_user", true); // Mengisi username dan menghapus sebelumnya
        loginPO.fillPassword("secret_sauce", true); // Mengisi password dan menghapus sebelumnya
        loginPO.clickLoginButton();
        loginPO.validateLockedAccount("Epic sadface: Sorry, this user has been locked out.");
    }

    @AfterClass
    public void tearDown() {
        manager.closeBrowser(); // Menutup browser setelah semua pengujian selesai
    }
}
