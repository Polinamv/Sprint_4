import pages.ClientDataOrderPage;
import pages.MainPage;
import pages.RentDataOrderPage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderTest extends BaseTest {

    private final String USERNAME;
    private final String USER_LAST_NAME;
    private final String USER_ADDRESS;
    private final int METRO_STATION_ID;
    private final String USER_PHONE_NUMBER;
    private final String DATE;
    private final String RENT_DAYS_TEXT;
    private final String SCOOTER_COLOR;
    private final String COMMENT;
    private final String ORDER_BUTTON_NAME;

    public OrderTest(
            String USERNAME,
            String USER_LAST_NAME,
            String USER_ADDRESS,
            int METRO_STATION_ID,
            String USER_PHONE_NUMBER,
            String DATE,
            String RENT_DAYS_TEXT,
            String SCOOTER_COLOR,
            String COMMENT,
            String ORDER_BUTTON_NAME
    ) {
        this.USERNAME = USERNAME;
        this.USER_LAST_NAME = USER_LAST_NAME;
        this.USER_ADDRESS = USER_ADDRESS;
        this.METRO_STATION_ID = METRO_STATION_ID;
        this.USER_PHONE_NUMBER = USER_PHONE_NUMBER;
        this.DATE = DATE;
        this.RENT_DAYS_TEXT = RENT_DAYS_TEXT;
        this.SCOOTER_COLOR = SCOOTER_COLOR;
        this.COMMENT = COMMENT;
        this.ORDER_BUTTON_NAME = ORDER_BUTTON_NAME;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {
                        "Геннадий",
                        "Горин",
                        "Садовая 113а",
                        ClientDataOrderPage.FIRST_METRO_STATION_ID,
                        "79812345677",
                        LocalDateTime.now().plusDays(1).format(DATE_FORMATTER),
                        RentDataOrderPage.ONE_DAY_RENT_TEXT,
                        RentDataOrderPage.SCOOTER_COLOR_BLACK_ID,
                        "Привезите как можно скорее",
                        MainPage.UPPER_ORDER_BUTTON_NAME
                },
                {
                        "Александр",
                        "Санин",
                        "пер. Пролетарский 88",
                        ClientDataOrderPage.SECOND_METRO_STATION_ID,
                        "897746573322",
                        LocalDateTime.now().plusDays(2).format(DATE_FORMATTER),
                        RentDataOrderPage.TWO_DAYS_RENT_TEXT,
                        RentDataOrderPage.SCOOTER_COLOR_GRAY_ID,
                        "Жду самик!",
                        MainPage.BOTTOM_ORDER_BUTTON_NAME
                }
        };
    }

    @Test
    public void testScooterOrder() {
        MainPage objMainPage = new MainPage(driver);
        ClientDataOrderPage objClientDataPage = new ClientDataOrderPage(driver);
        RentDataOrderPage objRentDataPage = new RentDataOrderPage(driver);
        objMainPage.clickRemoveCookieButton();
        if (ORDER_BUTTON_NAME.equals(MainPage.UPPER_ORDER_BUTTON_NAME)) {
            objMainPage.clickUpperOrderButton();
        } else if (ORDER_BUTTON_NAME.equals(MainPage.BOTTOM_ORDER_BUTTON_NAME)) {
            objMainPage.clickBottomOrderButton();
        }
        objClientDataPage.setUpClientDataAndClickNext(
                USERNAME,
                USER_LAST_NAME,
                USER_ADDRESS,
                METRO_STATION_ID,
                USER_PHONE_NUMBER
        );
        objRentDataPage.orderScooter(DATE, RENT_DAYS_TEXT, SCOOTER_COLOR, COMMENT);
        String actualText = objRentDataPage.getStatusButtonText();
        Assert.assertEquals("Text in status button doesn't match!", EXPECTED_TEXT, actualText);
    }

    private static final String EXPECTED_TEXT = "Посмотреть статус";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
}
