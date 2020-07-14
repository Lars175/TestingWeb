package ru.netology.web;


import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CallbackTest {

    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        SelenideElement form = $("[class=\"form form_size_m form_theme_alfa-on-white\"]");

        form.$("[data-test-id=name] input").setValue("Иванов Олег");
        form.$("[data-test-id=phone] input").setValue("+79991992929");

        form.$("[data-test-id=agreement]").click();
        form.$("[class=\"button__text\"]").click();

        $(".Success_successBlock__2L3Cw").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}




