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
    private static final String cookieButtonId = "rcc-confirm-button";

    // Блок "Вопросы о важном"
    // Локатор для блока дроп-даунов "Вопросы о важном"
    private static final String accordionItemXpathMask = ".//div[@id='accordion__heading-%d']";
    // Локатор для ответов на вопросы о важном
    private static final String accordionItemTextXpathMask = ".//div[@id='accordion__panel-%d']";
    // Локатор для тайтла блока "Вопросы о важном"
    private static final String questionsBlockXpath = ".//div[@class='Home_SubHeader__zwi_E' and text()='Вопросы о важном']";

    // Кнопки "Заказать"
    // Локатор для верхней кнопки
    private static final String upperOrderButtonXpath = ".//button[@class='Button_Button__ra12g' and text()='Заказать']";
    // Локатор для нижней кнопки
    private static final String bottomOrderButtonXpath = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']";

    public void clickRemoveCookieButton() {
        driver.findElement(By.id(cookieButtonId)).click();
    }

    public void scrollTillQuestionsBlock() {
        WebElement element = driver.findElement(By.xpath(questionsBlockXpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickAccordionItem(int accordionItemId) {
        String accordionItemXpath = String.format(accordionItemXpathMask, accordionItemId);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(accordionItemXpath)));
        driver.findElement(By.xpath(accordionItemXpath)).click();
    }

    public String getAccordionItemText(int accordionItemId) {
        return driver.findElement(By.xpath(String.format(accordionItemTextXpathMask, accordionItemId))).getText();
    }

    public void clickUpperOrderButton() {
        driver.findElement(By.xpath(upperOrderButtonXpath)).click();
    }

    public void clickBottomOrderButton() {
        driver.findElement(By.xpath(bottomOrderButtonXpath)).click();
    }

    public void openAnswer(int accordionItemId) {
        scrollTillQuestionsBlock();
        clickAccordionItem(accordionItemId);
    }

    public static final String upperOrderButtonName = "upperOrderButton";
    public static final String bottomOrderButtonName = "bottomOrderButton";
}
