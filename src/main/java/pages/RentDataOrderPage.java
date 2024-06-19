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
    private static final String ORDER_TITLE_CLASS_NAME = "Order_Header__BZXOb";

    // Локатор для поля календаря
    private static final String DATE_FIELD_INPUT_XPATH = ".//input[@placeholder='* Когда привезти самокат']";
    // Локатор для поля срока аренды
    private static final String RENT_DAYS_FIELD_CLASS_NAME = "Dropdown-arrow";
    // Локатор для выбора дней аренды
    private static final String RENT_DAYS_XPATH_MASK = ".//div[@class='Dropdown-menu']/div[text()='%s']";
    // Локатор для комментария для курьера
    private static final String COMMENT_FOR_COURIER_XPATH = ".//input[@placeholder='Комментарий для курьера']";
    // Локатор для кнопки "Заказать"
    private static final String ORDER_BUTTON_XPATH = ".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']";

    // Локатор для ожидаемого результата: появление модального окна "Хотите оформить заказ?"
    private static final String ORDER_MODAL_WINDOW_CLASS_NAME = "Order_ModalHeader__3FDaJ";
    // Локатор для кнопки "Да" в модальном окне
    private static final String YES_BUTTON_XPATH = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']";

    // Локатор для ожидаемого результата: появилось модальное окно об успешном создании заказа
    private static final String ORDER_IS_PROCESSED_CLASS_NAME = "Order_ModalHeader__3FDaJ";
    // Локатор для кнопки "Посмотреть статус"
    private static final String STATUS_BUTTON_XPATH = ".//button[text()='Посмотреть статус']";

    public void waitTitleToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.className(ORDER_TITLE_CLASS_NAME)));
    }

    public void setDate(String date) {
        driver.findElement(By.xpath(DATE_FIELD_INPUT_XPATH)).sendKeys(date);
    }

    public void selectRentDays(String rentDaysText) {
        driver.findElement(By.className(RENT_DAYS_FIELD_CLASS_NAME)).click();
        driver.findElement(By.xpath(String.format(RENT_DAYS_XPATH_MASK, rentDaysText))).click();
    }

    public void selectScooterColor(String scooterColorId) {
        driver.findElement(By.id(scooterColorId)).click();
    }

    public void setCommentForCourier(String comment) {
        driver.findElement(By.xpath(COMMENT_FOR_COURIER_XPATH)).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(By.xpath(ORDER_BUTTON_XPATH)).click();
    }

    public void waitModalWindowToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.className(ORDER_MODAL_WINDOW_CLASS_NAME)));
    }

    public void clickYesButton() {
        driver.findElement(By.xpath(YES_BUTTON_XPATH)).click();
    }

    public void waitSuccessfullyCreatedOrderWindow() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.className(ORDER_IS_PROCESSED_CLASS_NAME)));
    }

    public String getStatusButtonText() {
        return driver.findElement(By.xpath(STATUS_BUTTON_XPATH)).getText();
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

    public static final String ONE_DAY_RENT_TEXT = "сутки";
    public static final String TWO_DAYS_RENT_TEXT = "двое суток";
    public static final String SCOOTER_COLOR_BLACK_ID = "black";
    public static final String SCOOTER_COLOR_GRAY_ID = "grey";
}
