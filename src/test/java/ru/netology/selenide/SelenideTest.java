package ru.netology.selenide;

import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.cssSelector;

public class selenideTest {

    private String generateDate(long addDays, String pattern){
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }
    @Test
    public void shouldCompleted() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        String planningDate=generateDate(5,"dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Петров Петр Петрович");
        $("[data-test-id='phone'] input").setValue("+79125555525");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));

    }


}