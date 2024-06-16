import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

@RunWith(Parameterized.class)
public abstract class BaseTest {

    BrowserDrivers browserDrivers = new BrowserDrivers();
    // Можем выбрать driver для браузера Firefox, используя browserDrivers.firefoxDriver();
    WebDriver driver = browserDrivers.chromeDriver();

    @Before
    public void setup() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void clearUp() {
        driver.quit();
    }
}
