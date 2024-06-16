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
    private final String scooterColorId;
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
            String scooterColorId,
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
        this.scooterColorId = scooterColorId;
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
                        ClientDataOrderPage.firstMetroStationId,
                        "79812345677",
                        LocalDateTime.now().plusDays(1).format(dateFormatter),
                        RentDataOrderPage.oneDayRentText,
                        RentDataOrderPage.scooterColorBlackId,
                        "Привезите как можно скорее",
                        MainPage.upperOrderButtonName
                },
                {
                        "Александр",
                        "Санин",
                        "пер. Пролетарский 88",
                        ClientDataOrderPage.secondMetroStationId,
                        "897746573322",
                        LocalDateTime.now().plusDays(2).format(dateFormatter),
                        RentDataOrderPage.twoDaysRentText,
                        RentDataOrderPage.scooterColorGrayId,
                        "Жду самик!",
                        MainPage.bottomOrderButtonName
                }
        };
    }

    @Test
    public void testScooterOrder() {
        MainPage objMainPage = new MainPage(driver);
        ClientDataOrderPage objClientDataPage = new ClientDataOrderPage(driver);
        RentDataOrderPage objRentDataPage = new RentDataOrderPage(driver);
        objMainPage.clickRemoveCookieButton();
        if (orderButtonName.equals(MainPage.upperOrderButtonName)) {
            objMainPage.clickUpperOrderButton();
        } else if (orderButtonName.equals(MainPage.bottomOrderButtonName)) {
            objMainPage.clickBottomOrderButton();
        }
        objClientDataPage.setUpClientDataAndClickNext(
                username,
                userLastName,
                userAddress,
                metroStationId,
                userPhoneNumber
        );
        objRentDataPage.orderScooter(date, rentDaysText, scooterColorId, comment);
        String actualText = objRentDataPage.getStatusButtonText();
        Assert.assertEquals("Text in status button doesn't match!", expectedText, actualText);
    }

    private static final String expectedText = "Посмотреть статус";
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
}
