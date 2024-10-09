package pageobjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import utilities.PlaywrightHelpers;

public class LoginPO {

    private final Page page;

    private final PlaywrightHelpers pwHelper;

    public LoginPO(Page page) {
        this.page = page;
        this.pwHelper = new PlaywrightHelpers(page); // Inisialisasi PlaywrightHelpers

        // Inisialisasi locator
        initializeLocators();
    }

    // Locator untuk elemen di halaman login
    private Locator loginTitleText;
    private Locator userNameTextField;
    private Locator passwordTextField;
    private Locator loginButton;
    private Locator loggedTitleText;

    //Error Element
    private Locator userNameErrorIcon;
    private Locator passwordErrorIcon;
    private Locator errorMessageLogin;

    private void initializeLocators() {
        this.loginTitleText = page.locator("//div[@class='login_logo']");
        this.userNameTextField = page.locator("//input[@id='user-name']");
        this.passwordTextField = page.locator("//input[@id='password']");
        this.loginButton = page.locator("//input[@id='login-button']");
        this.loggedTitleText = page.locator("//div[@class='app_logo']");
        this.userNameErrorIcon = page.locator("//input[@id='user-name']/following-sibling::*[name()='svg']");
        this.passwordErrorIcon = page.locator("//input[@id='password']/following-sibling::*[name()='svg']");
        this.errorMessageLogin = page.locator("//h3[@data-test='error']");
    }

    public void validatePageLogin(String txt){
        assertThat(loginTitleText).hasText(txt);
    }

    public void fillUsername(String username, boolean clear) {
        pwHelper.enterText(userNameTextField, username, clear);
    }

    public void fillPassword(String password, boolean clear) {
        pwHelper.enterText(passwordTextField, password,clear);
    }

    public void clickLoginButton() {
        pwHelper.clickOn(loginButton);
    }

    public void validateSuccesLogin(){
        assertThat(loggedTitleText).isVisible();
    }

    public void validateUsernameIsMandatory(String txt){
        assertThat(userNameErrorIcon).isVisible();
        assertThat(errorMessageLogin).hasText(txt);
    }

    public void validatePasswordIsMandatory(String txt){
        assertThat(passwordErrorIcon).isVisible();
        assertThat(errorMessageLogin).hasText(txt);
    }

    public void validateLockedAccount(String txt){
        assertThat(userNameErrorIcon).isVisible();
        assertThat(passwordErrorIcon).isVisible();
        assertThat(errorMessageLogin).isVisible();
        assertThat(errorMessageLogin).hasText(txt);
    }

}
