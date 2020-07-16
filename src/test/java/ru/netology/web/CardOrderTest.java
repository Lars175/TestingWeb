package ru.netology.web;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardOrderTest {
    private WebDriver driver;
    ChromeOptions options;

    @BeforeEach
    void setUp() {
        options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--ignore-certificate-errors");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
    }


    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;

    }

    @Test
    void shouldSubmitRequest() {
        driver.get("http://localhost:9999");
        List<WebElement> elements = driver.findElements(By.className("[data-test-id=callback-form]"));
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов Олег");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79991992929");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("[role=button]")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }


}



