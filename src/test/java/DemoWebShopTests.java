import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;

public class DemoWebShopTests {

    @Test
    void addProductToCardTest(){
        String authorizationCookie =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .body("addtocart_43.EnteredQuantity=1")
                .when()
                        .post("http://demowebshop.tricentis.com/addproducttocart/details/43/1")
                .then()
                        .statusCode(200)
                        .extract().cookie("Nop.customer");

        open("http://demowebshop.tricentis.com/");
        getWebDriver().manage().addCookie(new Cookie("Nop.customer", authorizationCookie));
        refresh();

        $(".cart-qty").shouldHave(text("1"));
    }
}
