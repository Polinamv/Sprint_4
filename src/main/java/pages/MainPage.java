package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    // Локатор для кнопки "Да все привыкли"
    private static final String COOKIE_BUTTON_ID = "rcc-confirm-button";

    // Блок "Вопросы о важном"
    // Локатор для блока дроп-даунов "Вопросы о важном"
    private static final String ACCORDION_ITEM_XPATH_MASK = ".//div[@id='accordion__heading-%d']";
    // Локатор для ответов на вопросы о важном
    private static final String ACCORDION_ITEM_TEXT_XPATH_MASK = ".//div[@id='accordion__panel-%d']";
    // Локатор для тайтла блока "Вопросы о важном"
    private static final String QUESTIONS_BLOCK_XPATH = ".//div[@class='Home_SubHeader__zwi_E' and text()='Вопросы о важном']";

    // Кнопки "Заказать"
    // Локатор для верхней кнопки
    private static final String UPPER_ORDER_BUTTON_XPATH = ".//button[@class='Button_Button__ra12g' and text()='Заказать']";
    // Локатор для нижней кнопки
    private static final String BOTTOM_ORDER_BUTTON_XPATH = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']";

    public void clickRemoveCookieButton() {
        driver.findElement(By.id(COOKIE_BUTTON_ID)).click();
    }

    public void scrollTillQuestionsBlock() {
        WebElement element = driver.findElement(By.xpath(QUESTIONS_BLOCK_XPATH));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickAccordionItem(int accordionItemId) {
        String accordionItemXpath = String.format(ACCORDION_ITEM_XPATH_MASK, accordionItemId);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(accordionItemXpath)));
        driver.findElement(By.xpath(accordionItemXpath)).click();
    }

    public String getAccordionItemText(int accordionItemId) {
        return driver.findElement(By.xpath(String.format(ACCORDION_ITEM_TEXT_XPATH_MASK, accordionItemId))).getText();
    }

    public void clickUpperOrderButton() {
        driver.findElement(By.xpath(UPPER_ORDER_BUTTON_XPATH)).click();
    }

    public void clickBottomOrderButton() {
        driver.findElement(By.xpath(BOTTOM_ORDER_BUTTON_XPATH)).click();
    }

    public void openAnswer(int accordionItemId) {
        scrollTillQuestionsBlock();
        clickAccordionItem(accordionItemId);
    }

    public static final String UPPER_ORDER_BUTTON_NAME = "upperOrderButton";
    public static final String BOTTOM_ORDER_BUTTON_NAME = "bottomOrderButton";
}
