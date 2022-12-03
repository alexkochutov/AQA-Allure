package ru.netology.delivery.test;

import java.time.Duration;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;
import io.qameta.allure.selenide.AllureSelenide;
import com.codeborne.selenide.logevents.SelenideLogger;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.exactOwnText;

class DeliveryTest {

    @BeforeAll
    static void setupAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $("[data-test-id='city'] .input__control").setValue(validUser.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        $("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue(firstMeetingDate);
        $("[data-test-id='name'] .input__control").setValue(validUser.getName());
        $("[data-test-id='phone'] .input__control").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__title").shouldBe(exactOwnText("Успешно!"));
        $("[data-test-id='success-notification'] .notification__content").shouldBe(exactOwnText("Встреча успешно запланирована на \n" + firstMeetingDate));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        $("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue(secondMeetingDate);
        $("button.button").click();
        $("[data-test-id='replan-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='replan-notification'] .notification__title").shouldBe(exactOwnText("Необходимо подтверждение"));
        $("[data-test-id='replan-notification'] .notification__content").shouldBe(exactOwnText("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $("[data-test-id='replan-notification'] button").click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__title").shouldBe(exactOwnText("Успешно!"));
        $("[data-test-id='success-notification'] .notification__content").shouldBe(exactOwnText("Встреча успешно запланирована на \n" + secondMeetingDate));
    }
}