package page;

import org.openqa.selenium.*;

import java.util.List;
public class OrderForRentPage {

    private WebDriver driver;

    public OrderForRentPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локатор для поля "Когда привезти самокат"
    private By locatorWhen = By.xpath("//input[@placeholder = '* Когда привезти самокат']");

    //Локатор для поля "Срок аренды"
    private By locatorPeriod = By.xpath("//div[contains(@class,'Dropdown-control')]");

    //Локатор для выпадающего поля в "Срок аренды"
    private By locatorPeriodMenu= By.xpath("//div[contains(@class,'Dropdown-menu')]");

    //Локатор для поля "Цвет самоката"
    private By locatorColour = By.xpath("//div[contains(@class,'Order_Checkboxes')]");

    //Локатор для поля "Комментарий для курьера"
    private By locatorComment = By.xpath("//input[@placeholder = 'Комментарий для курьера']");

    //Локатор для кнопки "Заказать"
    private By locatorOrder = By.xpath("//div[contains(@class,'Order_Buttons')]//button[text()='Заказать']");

    //Локатор для кнопки "Да"
    private By locatorYes = By.xpath("//div[contains(@class,'Order_Buttons')]//button[text()='Да']");

    // Локатор окна "Заказ оформлен" с данными заказа
    private By locatorDataOrder = By.xpath("//div[text()='Заказ оформлен']");


    //Метод для заполнения полей
    public void forRent(String date, String period, String colour, String comment){
        inputDate(date);
        driver.findElement(locatorPeriod).click();
        int indexRent = getIndexForRent(period);
        driver.findElements(locatorPeriodMenu).get(indexRent).click();
        clickColour(colour);
        driver.findElement(locatorComment).sendKeys(comment);
    }

    // Метод заполнения поля "Когда привезти"
    public void inputDate(String date) {
        driver.findElement(locatorWhen).sendKeys(date, Keys.ENTER);
    }

    //Метод для определения id указанного срока аренды
    private int getIndexForRent(String period) {
        int indexRent = 0;
        List<WebElement> listElementRent = driver.findElements(locatorPeriodMenu);
        for (int i = 0; i < listElementRent.size(); i++) {
            if (listElementRent.get(i).getText().equals(period)) {
                return i;
            }
        }
        return 0;
    }

    //Метод для нажатия выбранного цвета самоката
    public void clickColour(String colour) {
        if (colour == "чёрный жемчуг") {
            driver.findElement(By.xpath("//input[@id = 'black']")).click();
        } else {
            driver.findElement(By.xpath("//input[@id = 'grey']")).click();
        }
    }

    // Метод знажатия кнопки "Заказать"
    public void clickRent() {
        driver.findElement(locatorOrder).click();
    }

    // Метод знажатия кнопки "Да"
    public void clickYes() {
        driver.findElement(locatorYes).click();
    }

    // Метод ожидания появления окна c данными заказа
    public void waitForDataOrder() {
        driver.findElement(locatorDataOrder).isDisplayed();
    }
}
