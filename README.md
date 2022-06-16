Тестовое задание

Использованные технологии:
- Java 8
- Spring Boot 2.7.0
- Vue.js
- MySQL 8
- Bootstrap

Приложение содержит две функции:
- подсчёт суммы за день<br/>
  http://localhost:8080/api/sales.sum?date=DATE<br/>
  DATE - дата в типе Long<br/>
- вывод топ-3 товара по номеру карты<br/>
  http://localhost:8080/api/sales.top?card=NUMBER&limit=LIMIT<br/>
  NUMBER - номер карты<br/>
  LIMIT - количество товаров<br/>

Скрипт init_db.sql в корневом каталоге для инициализации базы данных.
Сборка приложения ./mvnw package

Чеки хранятся в папке checks, после обработки переносятся в папку checks-old.

http://localhost:8080/
![Screenshot](https://user-images.githubusercontent.com/97829657/174144483-3e325798-50b1-4c59-b6ae-3789f80c942a.png)
