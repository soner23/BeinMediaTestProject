package SubscriptionTest;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import SubscriptionTest.MavenTest.BeinSportConnect;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.logging.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;


public class BeinSportTest {

    private static WebDriver driver = null;
    private static Logger log = Logger.getLogger(String.valueOf(BeinSportTest.class));


    public static void main (String[] args) throws InterruptedException {

        beinSport();

    } @Test

    public static void beinSport () throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/users/soner/Downloads/chrome/chromedriver" );
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //Navigate to https://connect-th.beinsports.com/en
        driver.get("https://connect-th.beinsports.com/en");
        log.info("Sayfa açıldı");

        // Click Subscribe and Register Monthly Pass with One Week Free Trial Package
        BeinSportConnect.subscribe(driver).click();
        BeinSportConnect.paket(driver).click();
        BeinSportConnect.validate(driver).click();
        log.info("Paket Seçilip,Validate edildi");
        Thread.sleep(5000);

        // Fill Account Form
        BeinSportConnect.accountformfirstname(driver).sendKeys("atactkgd");
        BeinSportConnect.accountformlastname(driver).sendKeys("btcitgkbd");
        BeinSportConnect.accountformEmail(driver).sendKeys("digitrk@trk.com");
        BeinSportConnect.accountformPassword(driver).sendKeys("123456.Sa");
        BeinSportConnect.accountformSubmit(driver).click();
        log.info("Üye bilgileri Form'a girildi");
        Thread.sleep(10000);

        //Ignore email verify
        BeinSportConnect.emailVerify(driver).click();
        log.info("Email Ignore edildi");
        Thread.sleep(5000);

        //Make Payment with Credit Card and Expect for a total charge of 1.00
        BeinSportConnect.basketCheck(driver).click();
        BeinSportConnect.basketPayNow(driver).click();
        Thread.sleep(10000);
        String title = BeinSportConnect.totalCharge(driver).getText();
        assertThat(title, is("1.00 THB"));
        log.info("Ödeme şekli seçildi ve Toplam ücret doğrulandı");
        Thread.sleep(5000);

        //Provide a test card data
        BeinSportConnect.cardHoldersName(driver).sendKeys("AA");
        BeinSportConnect.cardsNumber(driver).sendKeys("4022774022774026");
        BeinSportConnect.expiryDateMonth(driver).sendKeys("12");
        BeinSportConnect.expiryDateYear(driver).sendKeys("2020");
        BeinSportConnect.cardCode(driver).sendKeys("000");
        BeinSportConnect.confirmPayment(driver).click();
        log.info("Kart bilgileri girildi");
        Thread.sleep(10000);

        //Expect for a error text and finish test with success
        String actualMsg = BeinSportConnect.errorText(driver).getText();
        String errMsg = "The transaction has been denied";

        if(actualMsg.contains(errMsg))
        {
            System.out.println("Test Case Passed");
            driver.close();
            driver.quit();
        }

        else
        {
            System.out.println("Test Case Failed");
        }
        log.info("Test Basarılı " + errMsg);

    }


}
