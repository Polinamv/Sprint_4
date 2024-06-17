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
    private static final String ORDER_TITLE_CLASS_NAME = "Order_Header__BZXOb";

    // Локаторы для формы заказа
    // Локатор для поля Имя
    private static final String USERNAME_INPUT_XPATH = ".//input[@placeholder='* Имя']";
    // Локатор для поля Фамилия
    private static final String USER_LAST_NAME_INPUT_XPATH = ".//input[@placeholder='* Фамилия']";
    // Локатор для поля Адрес
    private static final String USER_ADDRESS_INPUT_XPATH = ".//input[@placeholder='* Адрес: куда привезти заказ']";
    // Локатор для поля станции метро
    private static final String USER_METRO_STATION_SELECTOR_XPATH = ".//input[@placeholder='* Станция метро']";
    // Локатор для выбора станции
    private static final String METRO_STATION_XPATH_MASK = ".//div[@class='select-search__select']/ul[@class='select-search__options']/li[@class='select-search__row']/button[@value='%d']";
    // Локатор для поля номера телефона
    private static final String USER_PHONE_NUMBER_XPATH = ".//input[@placeholder='* Телефон: на него позвонит курьер']";
    // Локатор для кнопки "Далее"
    private static final String NEXT_BUTTON_XPATH = ".//button[text()='Далее']";


    public void waitTitleToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.className(ORDER_TITLE_CLASS_NAME)));
    }

    public void setUsername(String username) {
        driver.findElement(By.xpath(USERNAME_INPUT_XPATH)).sendKeys(username);
    }

    public void setUserLastName(String userLastName) {
        driver.findElement(By.xpath(USER_LAST_NAME_INPUT_XPATH)).sendKeys(userLastName);
    }

    public void setUserAddress(String userAddress) {
        driver.findElement(By.xpath(USER_ADDRESS_INPUT_XPATH)).sendKeys(userAddress);
    }

    public void selectMetroStation(int metroStationId) {
        driver.findElement(By.xpath(USER_METRO_STATION_SELECTOR_XPATH)).click();
        String metroStationXpath = String.format(METRO_STATION_XPATH_MASK, metroStationId);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath(metroStationXpath)));
        driver.findElement(By.xpath(metroStationXpath)).click();
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        driver.findElement(By.xpath(USER_PHONE_NUMBER_XPATH)).sendKeys(userPhoneNumber);
    }

    public void clickNextButton() {
        driver.findElement(By.xpath(NEXT_BUTTON_XPATH)).click();
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

    public static final int FIRST_METRO_STATION_ID = 1;
    public static final int SECOND_METRO_STATION_ID = 2;
}
