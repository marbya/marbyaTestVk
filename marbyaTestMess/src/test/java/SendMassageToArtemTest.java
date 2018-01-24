import org.aeonbits.owner.ConfigFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;


/**
 * Created by marbya on 18.01.2018.
 */

@RunWith(Parameterized.class)

public class SendMassageToArtemTest {

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[10][0]);
    }


    @Test
    public void loginVk() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://vk.com/id229191949");

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().startsWith("Армен Арушанян");
            }
        });
        ServisConfig cfg = ConfigFactory.create(ServisConfig.class);
        WebElement login = driver.findElement(By.name("email"));
        login.sendKeys(cfg.loginVk());
        WebElement pass = driver.findElement(By.name("pass"));
        pass.sendKeys(cfg.passVk());
        WebElement loginButton = driver.findElement(By.className("quick_login_button"));
        loginButton.click();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.className("profile_btn_cut_left")).isDisplayed();
            }
        });


        WebElement sendButton = driver.findElement(By.className("profile_btn_cut_left"));
        sendButton.click();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("mail_box_editable")).isDisplayed();
            }
        });

        WebElement mailBoxEditable = driver.findElement(By.id("mail_box_editable"));
        mailBoxEditable.sendKeys("привет");
        WebElement sendButtonSend = driver.findElement(By.className("mail_box_send_btn"));
        sendButtonSend.click();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.className("top_profile_name")).isDisplayed();
            }
        });

        WebElement logOut1 = driver.findElement(By.className("top_profile_name"));
        logOut1.click();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("top_logout_link")).isDisplayed();
            }
        });
        WebElement logOut2 = driver.findElement(By.id("top_logout_link"));
        logOut2.click();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().startsWith("ВКонтакте");
            }
        });

        driver.quit();


    }

}



