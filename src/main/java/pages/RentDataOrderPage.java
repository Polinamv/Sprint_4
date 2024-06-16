package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RentDataOrderPage extends BasePage {

    public RentDataOrderPage(WebDriver driver) {
        super(driver);
    }

    // Локатор для ожидаемого результата: появление текста "Про аренду"
    private static final String orderTitleClassName = "Order_Header__BZXOb";

    // Локатор для поля календаря
    private static final String dateFieldInputXpath = ".//input[@placeholder='* Когда привезти самокат']";
    // Локатор для поля срока аренды
    private static final String rentDaysFieldClassName = "Dropdown-arrow";
    // Локатор для выбора дней аренды
    private static final String rentDaysXpathMask = ".//div[@class='Dropdown-menu']/div[text()='%s']";
    // Локатор для комментария для курьера
    private static final String commentForCourierXpath = ".//input[@placeholder='Комментарий для курьера']";
    // Локатор для кнопки "Заказать"
    private static final String orderButtonXpath = ".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']";

    // Локатор для ожидаемого результата: появление модального окна "Хотите оформить заказ?"
    private static final String orderModalWindowClassName = "Order_ModalHeader__3FDaJ";
    // Локатор для кнопки "Да" в модальном окне
    private static final String yesButtonXpath = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']";

    // Локатор для ожидаемого результата: появилось модальное окно об успешном создании заказа
    private static final String orderIsProcessedClassName = "Order_ModalHeader__3FDaJ";
    // Локатор для кнопки "Посмотреть статус"
    private static final String statusButtonXpath = ".//button[text()='Посмотреть статус']";

    public void waitTitleToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.className(orderTitleClassName)));
    }

    public void setDate(String date) {
        driver.findElement(By.xpath(dateFieldInputXpath)).sendKeys(date);
    }

    public void selectRentDays(String rentDaysText) {
        driver.findElement(By.className(rentDaysFieldClassName)).click();
        driver.findElement(By.xpath(String.format(rentDaysXpathMask, rentDaysText))).click();
    }

    public void selectScooterColor(String scooterColorId) {
        driver.findElement(By.id(scooterColorId)).click();
    }

    public void setCommentForCourier(String comment) {
        driver.findElement(By.xpath(commentForCourierXpath)).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(By.xpath(orderButtonXpath)).click();
    }

    public void waitModalWindowToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.className(orderModalWindowClassName)));
    }

    public void clickYesButton() {
        driver.findElement(By.xpath(yesButtonXpath)).click();
    }

    public void waitSuccessfullyCreatedOrderWindow() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.className(orderIsProcessedClassName)));
    }

    public String getStatusButtonText() {
        return driver.findElement(By.xpath(statusButtonXpath)).getText();
    }

    public void orderScooter(String date, String rentDaysText, String scooterColorId, String comment) {
        waitTitleToLoad();
        setDate(date);
        selectRentDays(rentDaysText);
        selectScooterColor(scooterColorId);
        setCommentForCourier(comment);
        clickOrderButton();
        waitModalWindowToLoad();
        clickYesButton();
        waitSuccessfullyCreatedOrderWindow();
    }

    public static final String oneDayRentText = "сутки";
    public static final String twoDaysRentText = "двое суток";
    public static final String scooterColorBlackId = "black";
    public static final String scooterColorGrayId = "grey";
}
