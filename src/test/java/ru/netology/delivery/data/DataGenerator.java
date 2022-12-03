package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.now().plusDays(shift).format(format);
    }

    public static String generateCity() {
        String[] cities = {
                "Абакан", "Анадырь", "Архангельск", "Астрахань", "Барнаул", "Белгород", "Биробиджан", "Благовещенск",
                "Брянск", "ВеликийНовгород", "Владивосток", "Владикавказ", "Владимир", "Волгоград", "Вологда", "Воронеж",
                "Грозный", "Екатеринбург", "Иваново", "Ижевск", "Иркутск", "Йошкар-Ола", "Казань", "Калининград",
                "Калуга", "Кемерово", "Киров", "Кострома", "Красногорск", "Краснодар", "Красноярск", "Курган", "Курск",
                "Кызыл", "Липецк", "Магадан", "Магас", "Майкоп", "Махачкала", "Москва", "Нальчик", "Нарьян-Мар",
                "НижнийНовгород", "Новосибирск", "Омск", "Орёл", "Оренбург", "Пенза", "Пермь", "Петрозаводск",
                "Петропавловск-Камчатский", "Псков", "Ростов-на-Дону", "Рязань", "Салехард", "Самара", "Санкт-Петербург",
                "Саранск", "Саратов", "Севастополь", "Симферополь", "Смоленск", "Ставрополь", "Сыктывкар", "Тамбов",
                "Тверь", "Томск", "Тула", "Тюмень", "Улан-Удэ", "Ульяновск", "Уфа", "Хабаровск", "Ханты-Мансийск",
                "Чебоксары", "Челябинск", "Чита", "Элиста", "Южно-Сахалинск", "Якутск", "Ярославль", "Гатчина",
                "Горно-Алтайск", "Мурманск", "Черкесск"
        };
        int random = (int) (Math.random() * cities.length);
        return cities[random];
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().name();
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber().replaceAll("[()\\-]", "");
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            return new UserInfo(generateCity(), generateName(locale), generatePhone(locale));
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}