package SubscriptionTest;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import SubscriptionTest.MavenTest.BeinSportConnect;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;


public class BeinSportTest {

    private static WebDriver driver = null;


    public static void main (String[] args) throws InterruptedException {

        beinSport();

    } @Test

    public static void beinSport () throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/users/soner/Downloads/chrome/chromedriver" );
        WebDriver driver = new ChromeDriver();


        driver.get("https://connect-th.beinsports.com/en");

        BeinSportConnect.subscribe(driver).click();
        BeinSportConnect.paket(driver).click();
        BeinSportConnect.validate(driver).click();
        Thread.sleep(5000);
        BeinSportConnect.accountformfirstname(driver).sendKeys("atactkgd");
        BeinSportConnect.accountformlastname(driver).sendKeys("btcitgkbd");
        BeinSportConnect.accountformEmail(driver).sendKeys("aigfuılılllkjtgka@ata.com");
        BeinSportConnect.accountformPassword(driver).sendKeys("123456.Sa");
        BeinSportConnect.accountformSubmit(driver).click();
        Thread.sleep(10000);
        BeinSportConnect.emailVerify(driver).click();
        Thread.sleep(5000);
        BeinSportConnect.basketCheck(driver).click();
        BeinSportConnect.basketPayNow(driver).click();
        Thread.sleep(10000);
        String title = BeinSportConnect.totalCharge(driver).getText();
        assertThat(title, is("1.00 THB"));
        Thread.sleep(10000);
        BeinSportConnect.cardHoldersName(driver).sendKeys("AA");
        BeinSportConnect.cardsNumber(driver).sendKeys("4022774022774026");
        BeinSportConnect.expiryDateMonth(driver).sendKeys("12");
        BeinSportConnect.expiryDateYear(driver).sendKeys("2020");
        BeinSportConnect.cardCode(driver).sendKeys("000");
        BeinSportConnect.confirmPayment(driver).click();
        Thread.sleep(10000);
        String actualMsg = BeinSportConnect.errorText(driver).getText();
        String errMsg = "The transaction has been denied";

        if(actualMsg.contains(errMsg))
        {
            System.out.println("Test Case Passed");
        }else
        {
            System.out.println("Test Case Failed");
        }


    }


}
