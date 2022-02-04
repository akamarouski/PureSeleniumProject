package com.solvd.askomar.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HomePage {

    public static final String URL = "https://www.kufar.by";

    protected WebDriver driver;

    @FindBy(css = "title")
    private WebElement title;
    @FindBy(xpath = "//input[contains(@id, 'searchbar')]")
    private WebElement searchField;
    @FindBy(css = "section")
    private List<WebElement> products;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.get(URLSaver.HOME_PAGE);
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return title.getText();
    }

    public Optional<List<String>> searchProductsAsText(String name) {
        searchField.sendKeys(name);
        searchField.click();
        searchField.sendKeys(Keys.ENTER);
        sleep(1);
        if (products == null) return Optional.empty();
        List<String> productsAsText = products.stream()
                .map(product -> product.getText())
                .collect(Collectors.toList());
        return Optional.of(productsAsText);
    }

    private void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
