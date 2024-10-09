package utilities;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

public class PlaywrightHelpers {

    Page page;

    public PlaywrightHelpers(Page page) {
        this.page = page;
    }

    public void enterText(Locator loc, String text, boolean clear) {
        if (clear) {
            loc.fill(""); // Hapus teks jika clear = true
        }
        loc.fill(text); // Masukkan teks
    }

    public void clickOn(Locator loc){
        loc.click();
    }

    // Metode untuk menunggu locator sampai terlihat
    public void waitUntilVisible(Locator locator, int seconds) {
        // Mengonversi detik ke milidetik
        double timeoutInMillis = seconds * 1000;

        locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(timeoutInMillis)
        );
    }

    // Metode untuk menunggu sampai halaman dimuat dengan opsi
    public void waitForPageLoaded(int seconds) {
        // Mengonversi detik ke milidetik
        double timeoutInMillis = seconds * 1000;

        Page.WaitForLoadStateOptions options = new Page.WaitForLoadStateOptions()
                .setTimeout(timeoutInMillis);

        page.waitForLoadState(LoadState.NETWORKIDLE, options); // Menunggu sampai halaman dalam keadaan NETWORKIDLE
    }
}
