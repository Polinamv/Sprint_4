package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ClientDataOrderPage extends BasePage {

    public ClientDataOrderPage(WebDriver driver) {
        super(driver);
    }

    // Локатор для ожидаемого результата: появление текста "Для кого самокат"
    private static final String orderTitleClassName = "Order_Header__BZXOb";

    // Локаторы для формы заказа
    // Локатор для поля Имя
    private static final String usernameInputXpath = ".//input[@placeholder='* Имя']";
    // Локатор для поля Фамилия
    private static final String userLastNameInputXpath = ".//input[@placeholder='* Фамилия']";
    // Локатор для поля Адрес
    private static final String userAddressInputXpath = ".//input[@placeholder='* Адрес: куда привезти заказ']";
    // Локатор для поля станции метро
    private static final String userMetroStationSelectorXpath = ".//input[@placeholder='* Станция метро']";
    // Локатор для выбора станции
    private static final String metroStationXpathMask = ".//div[@class='select-search__select']/ul[@class='select-search__options']/li[@class='select-search__row']/button[@value='%d']";
    // Локатор для поля номера телефона
    private static final String userPhoneNumberXpath = ".//input[@placeholder='* Телефон: на него позвонит курьер']";
    // Локатор для кнопки "Далее"
    private static final String nextButtonXpath = ".//button[text()='Далее']";


    public void waitTitleToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.className(orderTitleClassName)));
    }

    public void setUsername(String username) {
        driver.findElement(By.xpath(usernameInputXpath)).sendKeys(username);
    }

    public void setUserLastName(String userLastName) {
        driver.findElement(By.xpath(userLastNameInputXpath)).sendKeys(userLastName);
    }

    public void setUserAddress(String userAddress) {
        driver.findElement(By.xpath(userAddressInputXpath)).sendKeys(userAddress);
    }

    public void selectMetroStation(int metroStationId) {
        driver.findElement(By.xpath(userMetroStationSelectorXpath)).click();
        String metroStationXpath = String.format(metroStationXpathMask, metroStationId);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath(metroStationXpath)));
        driver.findElement(By.xpath(metroStationXpath)).click();
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        driver.findElement(By.xpath(userPhoneNumberXpath)).sendKeys(userPhoneNumber);
    }

    public void clickNextButton() {
        driver.findElement(By.xpath(nextButtonXpath)).click();
    }

    public void setUpClientDataAndClickNext(
            String username,
            String userLastName,
            String userAddress,
            int metroStationId,
            String userPhoneNumber
    ) {
        waitTitleToLoad();
        setUsername(username);
        setUserLastName(userLastName);
        setUserAddress(userAddress);
        selectMetroStation(metroStationId);
        setUserPhoneNumber(userPhoneNumber);
        clickNextButton();
    }

    public static final int firstMetroStationId = 1;
    public static final int secondMetroStationId = 2;
}
