package ru.netology.delivery.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.selenide.AllureSelenide;
import com.codeborne.selenide.logevents.SelenideLogger;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class DirectEnteringTest {
    int OFFSET_DAYS = 7;

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

    //
    //  Two positive tests with valid simple and complex (divided by dash) city names
    //

    @Test
    void simpleCityName() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__title").shouldBe(exactOwnText("Успешно!"));
        $("[data-test-id='success-notification'] .notification__content").shouldBe(exactOwnText("Встреча успешно запланирована на " + date));
    }

    @Test
    void complexCityName() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue("Горно-Алтайск");
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__title").shouldBe(exactOwnText("Успешно!"));
        $("[data-test-id='success-notification'] .notification__content").shouldBe(exactOwnText("Встреча успешно запланирована на " + date));
    }

    //
    //  Two positive tests with date divider as dash and slash
    //

    @Test
    void dateDividerIsDash() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date.replaceAll("\\.", "-"));
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__title").shouldBe(exactOwnText("Успешно!"));
        $("[data-test-id='success-notification'] .notification__content").shouldBe(exactOwnText("Встреча успешно запланирована на " + date));
    }

    @Test
    void dateDividerIsSlash() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date.replaceAll("\\.", "/"));
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__title").shouldBe(exactOwnText("Успешно!"));
        $("[data-test-id='success-notification'] .notification__content").shouldBe(exactOwnText("Встреча успешно запланирована на " + date));
    }

    //
    //  Positive test with complex last name divided by dash
    //

    @Test
    void complexLastName() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Василий Степанов-Архипов");
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__title").shouldBe(exactOwnText("Успешно!"));
        $("[data-test-id='success-notification'] .notification__content").shouldBe(exactOwnText("Встреча успешно запланирована на " + date));
    }

    //
    //  Negative tests for "City Name" field
    //

    @Test
    void emptyCityField() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Василий Степанов-Архипов");
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        form.$("[data-test-id='city'].input_invalid .input__sub").shouldBe(exactOwnText("Поле обязательно для заполнения"));
    }

    @Test
    void cityNotFromAllowedList() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue("Среднеуральск");
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Василий Степанов-Архипов");
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        form.$("[data-test-id='city'].input_invalid .input__sub").shouldBe(exactOwnText("Доставка в выбранный город недоступна"));
    }

    @Test
    void validCityNameWithSpecialSymbols() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue("!" + validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Василий Степанов-Архипов");
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        form.$("[data-test-id='city'].input_invalid .input__sub").shouldBe(exactOwnText("Доставка в выбранный город недоступна"));
    }

    @Test
    void digitsInsteadOfString() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue("1234567890");
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Василий Степанов-Архипов");
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        form.$("[data-test-id='city'].input_invalid .input__sub").shouldBe(exactOwnText("Доставка в выбранный город недоступна"));
    }

    @Test
    void cityInLatinChars() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue("Moscow");
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Василий Степанов-Архипов");
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        form.$("[data-test-id='city'].input_invalid .input__sub").shouldBe(exactOwnText("Доставка в выбранный город недоступна"));
    }

    //
    //  Negative tests for "Date" field
    //

    @Test
    void emptyDateField() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        form.$("[data-test-id='date'] .input_invalid .input__sub").shouldBe(exactOwnText("Неверно введена дата"));
    }

    @Test
    void dateInThePast() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(-7);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        form.$("[data-test-id='date'] .input_invalid .input__sub").shouldBe(exactOwnText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void dateIsToday() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(0);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        form.$("[data-test-id='date'] .input_invalid .input__sub").shouldBe(exactOwnText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void dateIsLessThanThreeDays() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(2);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        form.$("[data-test-id='date'] .input_invalid .input__sub").shouldBe(exactOwnText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void latinSymbolInsteadOfDate() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue("dD");
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        form.$("[data-test-id='date'] .input_invalid .input__sub").shouldBe(exactOwnText("Неверно введена дата"));
    }

    @Test
    void cyrillicSymbolInsteadOfDate() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue("фФ");
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        form.$("[data-test-id='date'] .input_invalid .input__sub").shouldBe(exactOwnText("Неверно введена дата"));
    }

    @Test
    void specialSymbolInsteadOfDate() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue("#");
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        form.$("[data-test-id='date'] .input_invalid .input__sub").shouldBe(exactOwnText("Неверно введена дата"));
    }

    @Test
    void datePresentedByOneDigit() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue("1");
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        form.$("[data-test-id='date'] .input_invalid .input__sub").shouldBe(exactOwnText("Неверно введена дата"));
    }

    @Test
    void datePresentedByFiveDigit() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue("13.04.197");
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        form.$("[data-test-id='date'] .input_invalid .input__sub").shouldBe(exactOwnText("Неверно введена дата"));
    }

    //
    //  Negative tests for "Name" field
    //

    @Test
    void emptyNameField() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        form.$("[data-test-id='name'].input_invalid .input__sub").shouldBe(exactOwnText("Поле обязательно для заполнения"));
    }

    @Test
    void latinSymbol() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Vasiliy Smirnov");
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        form.$("[data-test-id='name'].input_invalid .input__sub").shouldBe(exactOwnText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameContainsDigits() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Василий 15-й");
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        form.$("[data-test-id='name'].input_invalid .input__sub").shouldBe(exactOwnText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameContainsSpecialSymbols() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("!" + validUser.getName());
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        form.$("[data-test-id='name'].input_invalid .input__sub").shouldBe(exactOwnText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    //
    //  Negative tests for "Phone" field
    //

    @Test
    void emptyPhoneField() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        form.$("[data-test-id='phone'].input_invalid .input__sub").shouldBe(exactOwnText("Поле обязательно для заполнения"));
    }

    @Test
    void validNumberWithoutLeadingPlus() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue("79011234567");
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__title").shouldBe(exactOwnText("Успешно!"));
        $("[data-test-id='success-notification'] .notification__content").shouldBe(exactOwnText("Встреча успешно запланирована на " + date));
    }

    @Test
    void validNumberWithTrailingPlus() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone() + "+");
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__title").shouldBe(exactOwnText("Успешно!"));
        $("[data-test-id='success-notification'] .notification__content").shouldBe(exactOwnText("Встреча успешно запланирована на " + date));
    }

    @Test
    void invalidNumberConsistsOfOneDigit() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue("+7");
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__title").shouldBe(exactOwnText("Успешно!"));
        $("[data-test-id='success-notification'] .notification__content").shouldBe(exactOwnText("Встреча успешно запланирована на " + date));
    }

    @Test
    void invalidNumberWithLengthLessThanRequired() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue("+7901123456");
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__title").shouldBe(exactOwnText("Успешно!"));
        $("[data-test-id='success-notification'] .notification__content").shouldBe(exactOwnText("Встреча успешно запланирована на " + date));
    }

    @Test
    void invalidNumberWithLengthMoreThanRequired() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue("+790112345678");
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__title").shouldBe(exactOwnText("Успешно!"));
        $("[data-test-id='success-notification'] .notification__content").shouldBe(exactOwnText("Встреча успешно запланирована на " + date));
    }

    @Test
    void validNumberWithBraces() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue("+7(901)12345678");
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__title").shouldBe(exactOwnText("Успешно!"));
        $("[data-test-id='success-notification'] .notification__content").shouldBe(exactOwnText("Встреча успешно запланирована на " + date));
    }

    @Test
    void validNumberDividedByDash() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue("+7-901-123-45-67");
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__title").shouldBe(exactOwnText("Успешно!"));
        $("[data-test-id='success-notification'] .notification__content").shouldBe(exactOwnText("Встреча успешно запланирована на " + date));
    }

    @Test
    void numberContainsCyrillicSymbols() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue("+790112Ф4567");
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__title").shouldBe(exactOwnText("Успешно!"));
        $("[data-test-id='success-notification'] .notification__content").shouldBe(exactOwnText("Встреча успешно запланирована на " + date));
    }

    @Test
    void numberContainsLatinSymbols() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue("+790112F4567");
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__title").shouldBe(exactOwnText("Успешно!"));
        $("[data-test-id='success-notification'] .notification__content").shouldBe(exactOwnText("Встреча успешно запланирована на " + date));
    }

    @Test
    void numberContainsSpecialSymbols() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue("+79011#&4567");
        form.$("[data-test-id='agreement']").click();
        form.$("button.button").click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__title").shouldBe(exactOwnText("Успешно!"));
        $("[data-test-id='success-notification'] .notification__content").shouldBe(exactOwnText("Встреча успешно запланирована на " + date));
    }

    //
    //  Negative tests for CheckBox
    //

    @Test
    void emptyCheckBox() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue(validUser.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        String date = DataGenerator.generateDate(OFFSET_DAYS);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(validUser.getName());
        form.$("[data-test-id='phone'] input").setValue(validUser.getPhone());
        form.$("button.button").click();
        form.$("[data-test-id='agreement']").shouldHave(cssClass("input_invalid"));
    }
}