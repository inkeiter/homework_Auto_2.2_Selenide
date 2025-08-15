package ru.netology.web;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

class CardDeliveryTest {

    @Test
    void shouldRegistrationOfDeliveryCard() {
    Configuration.holdBrowserOpen = true;
    open("http://localhost:9999/");
    $("[data-test-id='city'] input").setValue("Майкоп");
    String date = LocalDate.now().plusDays(3)
    .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    $("[data-test-id='date'] input").setValue(date);
    $("[data-test-id='name'] input").setValue("Петров Олег");
    $("[data-test-id='phone'] input").setValue("+71234567890");
    $("[data-test-id='agreement']").click();
    $x("//span[text()='Забронировать']").click();
    $x("//*[@class='spin spin_size_m spin_visible spin_theme_alfa-on-color']").shouldBe(visible, Duration.ofSeconds(15));
    $("[data-test-id='notification']").should(appear, Duration.ofSeconds(15));
    }
    @Test
    void shouldRegistrationOfDeliveryCardByAutocomplition() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Ма");
        $x("//span[text()='Майкоп']").click();
        String date = LocalDate.now().plusDays(7)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Петров Олег");
        $("[data-test-id='phone'] input").setValue("+71234567890");
        $("[data-test-id='agreement']").click();
        $x("//span[text()='Забронировать']").click();
        $x("//*[@class='spin spin_size_m spin_visible spin_theme_alfa-on-color']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='notification']").should(appear, Duration.ofSeconds(15));
    }
}
