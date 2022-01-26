# web-lab-4
Переписать приложение из предыдущей лабораторной работы с использованием следующих технологий:
- Уровень back-end должен быть основан на Java EE (необходимо использовать EJB).
- Уровень front-end должен быть построен на Vue.js с использованием обычных полей ввода HTML
- Взаимодействие между уровнями back-end и front-end должно быть организовано посредством REST API.
<details><summary> Описание работы</summary>

Приложение по-прежнему должно включать в себя 2 страницы - стартовую и основную страницу приложения. Обе страницы приложения должны быть адаптированы для отображения в 3 режимах:
- "Десктопный" - для устройств, ширина экрана которых равна или превышает 1235 пикселей.
- "Планшетный" - для устройств, ширина экрана которых равна или превышает 699, но меньше 1235 пикселей.
- "Мобильный"- для устройств, ширина экрана которых меньше 699 пикселей.

### Стартовая страница должна содержать:
- "Шапку", содержащую ФИО студента, номер группы и номер варианта.
- Форму для ввода логина и пароля. Информация о зарегистрированных в системе пользователях должна храниться в отдельной таблице БД (пароль должен храниться в виде хэш-суммы). Доступ неавторизованных пользователей к основной странице приложения должен быть запрещён.
  

### Основная страница должна содержать:
- Набор полей ввода для задания координат точки и радиуса области в соответствии с вариантом задания: Radio {'-3','-2','-1','0','1','2','3','4','5'} для координаты по оси X, Text (-5 ... 5) для координаты по оси Y, и Radio {'-3','-2','-1','0','1','2','3','4','5'} для задания радиуса области. Если поле ввода допускает ввод заведомо некорректных данных (таких, например, как буквы в координатах точки или отрицательный радиус), то приложение должно осуществлять их валидацию.
- Динамически обновляемую картинку, изображающую область на координатной плоскости в соответствии с номером варианта и точки, координаты которых были заданы пользователем. Клик по картинке должен инициировать сценарий, осуществляющий определение координат новой точки и отправку их на сервер для проверки её попадания в область. Цвет точек должен зависить от факта попадания / непопадания в область. Смена радиуса также должна инициировать перерисовку картинки.
- Таблицу со списком результатов предыдущих проверок.
- Ссылку, по которой аутентифицированный пользователь может закрыть свою сессию и вернуться на стартовую страницу приложения.
### Требования к приложению
- Все результаты проверки должны сохраняться в базе данных под управлением СУБД PostgreSQL.
- Для доступа к БД необходимо использовать ORM EclipseLink.
- Для управления списком результатов должен использоваться Application-scoped Managed Bean.
- Конфигурация управляемых бинов должна быть задана с помощью аннотаций.
- Правила навигации между страницами приложения должны быть заданы в отдельном конфигурационном файле.
### Дополнительные требования к приложению
- Все результаты проверки должны сохраняться в базе данных под управлением СУБД Oracle.
- Для доступа к БД необходимо использовать JPA.
</details>

---
## Helpful links
### jax-rs examples
 - https://codedestine.com/jax-rs-produces-restful-web-services/
 - https://www.dokwork.ru/2014/08/restful.html
 - https://learntutorials.net/ru/java-ee/topic/7008/java-restful-web-services--jax-rs-
 - https://www.youtube.com/watch?v=dmMdLW92hBA
### vue
 - https://medium.com/bb-tutorials-and-thoughts/how-to-develop-and-build-vue-js-app-with-java-backend-4e9d72bb8ce5
### beans
 - https://tomee.apache.org/examples-trunk/cdi-application-scope/
 - https://docs.oracle.com/middleware/12211/jdev/user-guide/GUID-5F91CE59-D4DF-4BCD-9D27-632AA83485D4.htm#OJDUG1013
### cors
 - https://forum.camunda.org/t/enable-cors-on-wildfly/673/2
 - https://www.baeldung.com/cors-in-jax-rs
### storing passwords
 - https://www.vaadata.com/blog/how-to-securely-store-passwords-in-database/
 - https://www.baeldung.com/java-password-hashing
### jwt
 - https://stackoverflow.com/questions/26777083/how-to-implement-rest-token-based-authentication-with-jax-rs-and-jersey
 - https://habr.com/ru/post/340146/
 - https://developer.okta.com/blog/2018/10/31/jwts-with-java
 - https://antoniogoncalves.org/2016/10/03/securing-jax-rs-endpoints-with-jwt/
 - https://medium.com/nuances-of-programming/%D0%BF%D0%BE%D0%BB%D0%BD%D0%BE%D0%B5-%D1%80%D1%83%D0%BA%D0%BE%D0%B2%D0%BE%D0%B4%D1%81%D1%82%D0%B2%D0%BE-%D0%BF%D0%BE-%D1%83%D0%BF%D1%80%D0%B0%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D1%8E-jwt-%D0%B2%D0%BE-%D1%84%D1%80%D0%BE%D0%BD%D1%82%D0%B5%D0%BD%D0%B4-%D0%BA%D0%BB%D0%B8%D0%B5%D0%BD%D1%82%D0%B0%D1%85-graphql-b9b5103062a3
 - https://stackoverflow.com/questions/43027499/vuex-state-on-page-refresh
