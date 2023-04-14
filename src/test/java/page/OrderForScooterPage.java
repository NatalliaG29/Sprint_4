package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OrderForScooterPage {

    private WebDriver driver;

    public OrderForScooterPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локатор для поля "Имя"
    private By locatorName = By.xpath("//input[@placeholder = '* Имя']");

    //Локатор для поля "Фамилия"
    private By locatorLastName = By.xpath("//input[@placeholder = '* Фамилия']");

    //Локатор для поля "Адрес"
    private By locatorAddress = By.xpath("//input[@placeholder = '* Адрес: куда привезти заказ']");

    //Локатор для поля "Станция метро"
    private By locatorMetro = By.xpath("//input[@placeholder = '* Станция метро']");

    //Локатор для поля "Телефон"
    private By locatorPhone = By.xpath("//input[@placeholder = '* Телефон: на него позвонит курьер']");

    //Локатор для кнопки "Далее"
    private By locatorThen = By.xpath("//div[contains(@class,'Order_NextButton')]//button[text()='Далее']");


    //Метод для заполнения полей
    public void forScooter(String name, String lastName, String address, String metro, String phone, String date, String period, String colour, String comment){
        driver.findElement(locatorName).sendKeys(name);
        driver.findElement(locatorLastName).sendKeys(lastName);
        driver.findElement(locatorAddress).sendKeys(address);
        driver.findElement(locatorPhone).sendKeys(phone);
        driver.findElement(locatorMetro).click();
        int indexMetro = getIndexForMetro(metro);
        driver.findElements(By.className("select-search__row")).get(indexMetro).click();
        clickThen();
        OrderForRentPage orderForRentPage = new OrderForRentPage(driver);
        orderForRentPage.forRent(date, period, colour, comment);
    }

    //Метод для определения id указанной станции метро
    private int getIndexForMetro(String nameMetro){
        int indexMetro = 0;
        List<WebElement> listElementMetro = driver.findElements(By.className("select-search__row"));
        for (int i = 0; i < listElementMetro.size(); i++) {
            if (listElementMetro.get(i).getText().equals(nameMetro)) {
                return i;
            }
        }
        return 0;
    }

    //Метод для нажатия кнопки "Далее"
    public void clickThen() {
        driver.findElement(locatorThen).click();
    }
}
