package start;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Queue;


public class UiTest {
    private WebDriver driver;

    @BeforeEach
    public void setUP() {
        //путь до драйвера
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        //настройка браузера
        //ChromeOptions options = new ChromeOptions();
        //запуск невидемого браузера(может быть долгая загрузка)
        //options.addArguments("--headless");
        //создаем обьект браузера
        driver = new ChromeDriver();
        driver.get("https://www.google.ru/");
        //размер окна браузера при открытии
        driver.manage().window().setSize(new Dimension(1920, 1080));
        //тетс падает если страница не загрузилась
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        //ожидание для всех элементов
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        }

    @AfterEach
    public void endTest(){
        driver.close();

    }

    @Test
    public void titleTest () {
        String actualTitle = driver.getTitle();
        String expectTitle = "Google";
        Assertions.assertEquals(actualTitle, expectTitle);

    }

    @Test
    public void simpleTest(){
        driver.get("http://85.192.34.140:8081/");
        WebElement textName = driver.findElement(By.xpath("//h5[text()='Elements']"));
        textName.click();
        WebElement textBox = driver.findElement(By.xpath("//span[text()='Text Box']"));
        textBox.click();
        WebElement fullName = driver.findElement(By.id("userName"));
        WebElement email = driver.findElement(By.id("userEmail"));
        WebElement firstAddress = driver.findElement(By.id("currentAddress"));
        WebElement secondAddress = driver.findElement(By.id("permanentAddress"));
        WebElement button = driver.findElement(By.id("submit"));

        String expectedName = "Ivan";
        String expectedEmail = "ivan@mail.ru";
        String expectedFirstAddress = "Gerasimovo";
        String expectedSecondAddress = "Varancovo";

        fullName.sendKeys(expectedName);
        email.sendKeys(expectedEmail);
        firstAddress.sendKeys(expectedFirstAddress);
        secondAddress.sendKeys(expectedSecondAddress);
        button.click();

        WebElement newName = driver.findElement(By.id("name"));
        WebElement newEmail = driver.findElement(By.id("email"));
        WebElement newFirstAddress = driver.findElement(By.xpath("//div[@id='output']//p[@id='currentAddress']"));
        WebElement newSecondAddress = driver.findElement(By.xpath("//div[@id='output']//p[@id='currentAddress']"));

        String actualName = newName.getText();
        String actualEmail = newEmail.getText();
        String actualFirstAddress = newFirstAddress.getText();
        String actualSecondAddress = newSecondAddress.getText();

        Assertions.assertTrue(actualEmail.contains(expectedEmail));
        Assertions.assertTrue(actualName.contains(expectedName));
        Assertions.assertTrue(actualFirstAddress.contains(expectedFirstAddress));
        Assertions.assertTrue(actualSecondAddress.contains(expectedSecondAddress));


    }

}
