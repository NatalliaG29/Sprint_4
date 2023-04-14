package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локатор кнопки "Заказать" вверху страницы
    private By locatorForOrderInHeader = By.xpath("//div[contains(@class,'Header_Nav')]//button[text()='Заказать']");

    //Локатор списка FAQ
    private By locatorForAllQuestions = By.xpath("//div[contains(@class,'accordion')]");

    //Локатор кнопки "Заказать" внизу страницы
    private By locatorForOrderInFooter = By.xpath("//div[contains(@class,'Home_Finis')]//button[text()='Заказать']");

    //Локатор кнопки "да все привыкли"
    private By locatorCookie = By.xpath("//button[text()='да все привыкли']");


    //Метод для нажатия кнопки "да все привыкли"
    public void closeCookie(){
        driver.findElement(locatorCookie).click();
    }

    //Метод для скролла к списку FAQ
    public void scrollForFaq(){
        WebElement elementForScroll = driver.findElement(locatorForAllQuestions);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", elementForScroll);
    }

    //Метод для проверки нажатия стрелочки и получения соответствующего текста
    public String ckickQuestion(By locatorAccordionHeading, By locatorAccordionPanel) {
        WebElement elementForQuestion = driver.findElement(locatorForAllQuestions).findElement(locatorAccordionHeading);

        if (elementForQuestion.isEnabled()){
            elementForQuestion.click();
            return driver.findElement(locatorForAllQuestions).findElement(locatorAccordionPanel).getText();
        }
        return null;
    }

    //Метод для нажатия кнопки "Заказать"
    public void clickOrderButton(int number){
        if (number == 1) {
            driver.findElement(locatorForOrderInHeader).click();
        }
        else {
            WebElement elementForScroll = driver.findElement(locatorForOrderInFooter);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", elementForScroll);
            driver.findElement(locatorForOrderInFooter).click();
        }
    }
}
