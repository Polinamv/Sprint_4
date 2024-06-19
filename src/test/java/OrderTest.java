import pages.ClientDataOrderPage;
import pages.MainPage;
import pages.RentDataOrderPage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderTest extends BaseTest {

    private final String username;
    private final String userLastName;
    private final String userAddress;
    private final int metroStationId;
    private final String userPhoneNumber;
    private final String date;
    private final String rentDaysText;
    private final String scooterColor;
    private final String comment;
    private final String orderButtonName;

    public OrderTest(
            String username,
            String userLastName,
            String userAddress,
            int metroStationId,
            String userPhoneNumber,
            String date,
            String rentDaysText,
            String scooterColor,
            String comment,
            String orderButtonName
    ) {
        this.username = username;
        this.userLastName = userLastName;
        this.userAddress = userAddress;
        this.metroStationId = metroStationId;
        this.userPhoneNumber = userPhoneNumber;
        this.date = date;
        this.rentDaysText = rentDaysText;
        this.scooterColor = scooterColor;
        this.comment = comment;
        this.orderButtonName = orderButtonName;
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
        if (orderButtonName.equals(MainPage.UPPER_ORDER_BUTTON_NAME)) {
            objMainPage.clickUpperOrderButton();
        } else if (orderButtonName.equals(MainPage.BOTTOM_ORDER_BUTTON_NAME)) {
            objMainPage.clickBottomOrderButton();
        }
        objClientDataPage.setUpClientDataAndClickNext(
                username,
                userLastName,
                userAddress,
                metroStationId,
                userPhoneNumber
        );
        objRentDataPage.orderScooter(date, rentDaysText, scooterColor, comment);
        String actualText = objRentDataPage.getStatusButtonText();
        Assert.assertEquals("Text in status button doesn't match!", EXPECTED_TEXT, actualText);
    }

    private static final String EXPECTED_TEXT = "Посмотреть статус";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
}
