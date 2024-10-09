package utilities;

import com.microsoft.playwright.*;

import java.awt.*;
import java.util.List;

public class PlaywrightManager {

    private Playwright playwright;
    private Browser browser;

    public Page launchBrowser(String browserType) {
        try {
            playwright = Playwright.create();
            browser = switch (browserType.toLowerCase()) {
                case "chromium" -> playwright.chromium().launch(new BrowserType.LaunchOptions()
                                .setArgs(List.of("--start-maximized"))
                                .setHeadless(false));
                case "firefox" -> playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                case "webkit" -> playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                default -> throw new IllegalArgumentException("Browser type " + browserType + " is not supported.");
            };

            BrowserContext context;

            if ("chromium".equalsIgnoreCase(browserType)) {
                // Chromium tanpa viewport size
                context = browser.newContext(new Browser.NewContextOptions()
                        .setViewportSize(null));
            } else {
                // Mengambil ukuran layar untuk Firefox dan WebKit
                int[] dimensions = getDeviceDimension();
                context = browser.newContext(new Browser.NewContextOptions()
                        .setViewportSize(dimensions[1], dimensions[0])); // width, height
            }

            return context.newPage();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void closeBrowser() {
        if (browser != null) {
            browser.close(); // Menutup browser
        }
        if (playwright != null) {
            playwright.close(); // Menutup Playwright
        }
    }

    public int[] getDeviceDimension(){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = dimension.width;
        int height = dimension.height;

        return new int[]{height, width};
    }
}
