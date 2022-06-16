Тестовое задание

Использованные технологии:
- Java 8
- Spring Boot 2.7.0
- Vue.js
- MySQL 8
- Bootstrap

Приложение содержит две функции:
- подсчёт суммы за день
  http://localhost:8080/api/sales.sum?date=DATE
  DATE - дата в типе Long
- вывод топ-3 товара по номеру карты
  http://localhost:8080/api/sales.top?card=NUMBER&limit=LIMIT
  NUMBER - номер карты
  LIMIT - количество товаров

Скрипт init_db.sql в корневом каталоге для инициализации базы данных.
Сборка приложения ./mvnw package

Чеки хранятся в папке checks, после обработки переносятся в папку checks-old.
