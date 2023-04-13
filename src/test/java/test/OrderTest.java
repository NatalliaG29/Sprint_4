package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.MainPage;
import page.OrderForRentPage;
import page.OrderForScooterPage;

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;

    private int number;
    private final String name;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String period;
    private final String colour;
    private final String comment;

    public OrderTest(int number, String name, String lastName, String address, String metro, String phone, String date, String period, String colour, String comment) {
        this.number = number;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getTextData() {
        return new Object[][] {
                {2,"Наталья","Горнякова","ул. Жуковского д.9", "Каховская","+71234567890", "12.05.2023", "четверо суток", "чёрный жемчуг", "Хорошего дня!"},
                {2,"Адреано","Челентано","Флоренция","Зорге","+79998887766", "13.06.2023", "двое суток", "чёрный жемчуг", "Жду с нетерпением"},
                {1, "Юрий","Гагарин","5-й кратер Луны","Измайлово","+70000000000", "20.04.2023", "семеро суток", "серая безысходность", "Отличного вам настроения!"},
        };
    }

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);

        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage mainPage = new MainPage(driver);
        mainPage.closeCookie();
    }

    @Test
    public void testOrder(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderButton(number);
        OrderForScooterPage orderForScooterPage = new OrderForScooterPage(driver);
        orderForScooterPage.forScooter(name, lastName, address, metro, phone, date, period, colour, comment);
        OrderForRentPage orderForRentPage = new OrderForRentPage(driver);
        orderForRentPage.clickRent();
        orderForRentPage.clickYes();
        orderForRentPage.waitForDataOrder();
    }
    @After
    public void teardown(){
        driver.quit();
    }
}
