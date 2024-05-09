package com.example.real.estate.agency.service;

import com.example.real.estate.agency.entity.RealEstateObject;
import com.example.real.estate.agency.repository.RealEstateObjectRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
public class RealEstateInitGenerator {
    @Autowired
    private RealEstateObjectRepository realEstateObjectRepository;

    private static final String[] MOSCOW_STREETS = {
        "ул. Тверская", "ул. Арбат", "пр-т Ленинский",
        "ул. Новый Арбат", "ул. Садовая", "ул. Бауманская",
        "ул. Покровка", "ул. Лубянка", "ул. Мясницкая",
        "ул. Варшавское шоссе", "ул. Щепкина", "ул. Остоженка",
        "ул. Покрышкина", "ул. Люсиновская"
    };

    private static final String[] APARTMENT_DESCRIPTIONS = {
        "Уютная однокомнатная квартира с красивым видом на город, идеально подходит для молодых профессионалов или студентов.",
        "Просторная квартира с двумя спальнями, свежим ремонтом и современной мебелью, готова к заселению.",
        "Компактная и функциональная студия в центре города, в шаговой доступности от метро и торговых центров.",
        "Двухкомнатная квартира с открытой планировкой, большими окнами и множеством естественного света.",
        "Просторная трехкомнатная квартира с балконом, прекрасно подойдет для большой семьи.",
        "Эксклюзивная квартира в новостройке с дизайнерским ремонтом и последними технологиями в сфере домашнего комфорта.",
        "Квартира в семейном районе с развитой инфраструктурой, близость школ и детских садов.",
        "Роскошная квартира в престижном районе с террасой и панорамными окнами.",
        "Современная квартира с полностью оборудованной кухней, включая посудомоечную машину и микроволновую печь.",
        "Квартира в классическом стиле с камином и винтажной мебелью, расположенная в историческом здании.",
        "Тихая двухкомнатная квартира в зеленом районе, окна выходят в уютный двор.",
        "Квартира с лоджией и прекрасным видом на реку, идеальное место для вдохновения и отдыха.",
        "Пентхаус с эксклюзивным дизайном, большой террасой и потрясающим видом на городской пейзаж.",
        "Квартира студио в современном жилом комплексе с круглосуточной охраной и подземной парковкой.",
        "Трехкомнатная квартира с двумя ванными комнатами и гардеробной, идеальна для семейного проживания.",
        "Квартира в экологически чистом районе с парком и велосипедными дорожками поблизости.",
        "Просторная квартира с высокими потолками, огромными окнами и светлым интерьером.",
        "Уютная квартира с балконом, откуда открывается живописный вид на закат.",
        "Квартира с авторским ремонтом, выполненным по индивидуальному проекту архитектора.",
        "Двухуровневая квартира с гостиной в стиле лофт и спальней на втором уровне.",
        "Квартира в новом жилом комплексе, оборудованная по последнему слову техники с умным домом.",
        "Минималистичная студия с открытой планировкой, большими окнами и минимальным декором.",
        "Элегантная квартира с роскошной ванной комнатой, включая джакузи и двойные раковины.",
        "Просторная квартира с кабинетом для работы из дома и высокоскоростным интернетом.",
        "Квартира с двумя балконами и видом на исторический центр города.",
        "Уникальная квартира в стиле арт-деко с оригинальными художественными произведениями и скульптурами.",
        "Квартира в стиле модерн с интегрированной системой «умный дом» для удобства и безопасности.",
        "Светлая квартира с тремя спальнями, идеально подходит для большой семьи или для приема гостей.",
        "Квартира с теплыми полами и высоким уровнем энергоэффективности, что снижает коммунальные платежи.",
        "Дизайнерская квартира в бизнес-классе с роскошными отделочными материалами и эксклюзивной мебелью.",
        "Квартира с внутренним двориком, оформленным в стиле зен, для максимального расслабления и уединения."
    };

    private final Random random = new Random();

    private String getRandomAddress() {
        String street = MOSCOW_STREETS[random.nextInt(MOSCOW_STREETS.length)];
        int houseNumber = 1 + random.nextInt(100);
        return street + ", дом " + houseNumber;
    }

    private int getRandomArea() {
        return 20 + random.nextInt(230);
    }

    private int getRandomPrice() {
        return 5000000 + random.nextInt(20000000);
    }

    private String getRandomDescription() {
        return APARTMENT_DESCRIPTIONS[random.nextInt(APARTMENT_DESCRIPTIONS.length)];
    }

    private int getRandomBuildYear() {
        return 1900 + random.nextInt(123);
    }

    private int getRandomNumberOfRooms() {
        return 1 + random.nextInt(5);
    }

    private String getRandomPhotoURL() {return "/" + random.nextInt(1, 21) + ".jpeg";}

    public List<RealEstateObject> generateRealEstateObjects(int count) {
        return IntStream.range(0, count).mapToObj(
            i -> new RealEstateObject(
                null,
                getRandomAddress(),
                getRandomArea(),
                getRandomPrice(),
                getRandomDescription(),
                getRandomBuildYear(),
                getRandomNumberOfRooms(),
                getRandomNumberOfRooms(),
                getRandomPhotoURL()
            )
        ).collect(Collectors.toList());
    }

    @PostConstruct
    public void fillDatabase() {
        List<RealEstateObject> realEstateObjects = generateRealEstateObjects(100);
        realEstateObjectRepository.saveAll(realEstateObjects);
    }
}
