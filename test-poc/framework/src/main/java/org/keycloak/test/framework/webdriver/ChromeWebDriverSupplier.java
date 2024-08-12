package org.keycloak.test.framework.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeWebDriverSupplier extends AbstractWebDriverSupplier {

    @Override
    public String getAlias() {
        return "chrome";
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
        return new RemoteWebDriver(gridUrl, options);
    }
}
