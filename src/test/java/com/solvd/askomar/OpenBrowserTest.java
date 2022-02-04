package com.solvd.askomar;

import com.solvd.askomar.page.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

public class OpenBrowserTest {

    private static final Logger LOGGER = LogManager.getLogger(OpenBrowserTest.class);
    private WebDriver driver;
    private HomePage homePage;

    @BeforeClass
    public void initialize() {
        driver = new SafariDriver();
        homePage = new HomePage(driver);
    }

    @DataProvider(name = "productTitles")
    public Object[][] productTitles() {
        return new Object[][]{{"брюки"}, {"кофты"}};
    }

    @Test
    public void chromeOpenSession() {
        String pageTitle = homePage.getTitle();
        Assert.assertEquals("Куфар — крупнейшая площадка объявлений в Беларуси", pageTitle,
                "Requested title not as expected");
    }

    @Test(dataProvider = "productTitles")
    public void searchProductsTest(String name) {
        Optional<List<String>> products = homePage.searchProductsAsText(name);
        Assert.assertFalse(products.isEmpty(), " products with title ${name} should exists");
        products.get().forEach(LOGGER::info);
    }

    @AfterClass
    public void closeWebDriver() {
        driver.quit();
    }
}
