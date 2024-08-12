package org.keycloak.test.framework.webdriver;

import org.keycloak.theme.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeHeadlessWebDriverSupplier extends AbstractWebDriverSupplier {

    @Override
    public String getAlias() {
        return "chrome-headless";
    }

    @Override
    public WebDriver getWebDriver() {
        URL gridUrl;
        try {
            gridUrl = new URL("http://127.0.0.1:4444/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        ChromeOptions options = new ChromeOptions();
        setGlobalOptions(options);
        options.addArguments(
                "--headless",
                "--disable-gpu",
                "--window-size=1920,1200",
                "--ignore-certificate-errors",
                "--disable-dev-shm-usage"
        );
        return new RemoteWebDriver(gridUrl, options);
    }
}
