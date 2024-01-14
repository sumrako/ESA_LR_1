# Лабораторная работа 1 (JavaEE)
## Glassfish
В качестве сервера приложений был использован Oracle GlassFish Server.

Команда для запуска сервера glassfish
```bash
asadmin start-domain domain1
```
![image](https://github.com/sumrako/ESA_LR_1/assets/67976572/59a92881-895b-48f3-98f1-9ebffbadc8bd)


Команда для остановки сервера glassfish
```bash
asadmin stop-domain domain1
```
![image](https://github.com/sumrako/ESA_LR_1/assets/67976572/28efdf19-b780-43b8-9639-c64976535af3)


После запуска сервера по адресу [http://localhost:4848](http://localhost:4848) доступна консоль администратора
![image](https://github.com/sumrako/ESA_LR_1/assets/67976572/01e06c6d-69cb-46ad-a3d3-4ab10b0e6d2c)


# Для развертывания приложения на сервер glassfish была произведена интеграция glassfish с IntelliJ IDEA


После развертывания ui приложения доступен по ссылкам [http://localhost:8080/javaEE-1.0-SNAPSHOT](http://localhost:8080/javaEE-1.0-SNAPSHOT) и
![image](https://github.com/sumrako/ESA_LR_1/assets/67976572/f1980727-4ba8-46da-98ba-7e942a53cf7f)

## PostgreSQL
В качестве СУБД была использована PostgreSQL. При установке postgres на локальный компьютер была создана локальная база данных, она и использовалась в работе.

Параматры подключения к базе данных прописаны в файле [persistence.xml](https://github.com/vellarLa/ESA_LAB_1/blob/master/src/main/resources/META-INF/persistence.xml).

Параметер
```bash
<property name="jakarta.persistence.schema-generation.database.action" value="create"/>
```
включает автоматическую генерачию таблиц, если таковые еще не были созданы.

## Data layer
Предметная область - театр. В качестве сущностей взяты:
- visitor - посетитель театра (зритель)
- performance - спектакль
- timetable - расписание (зал, спектакль, время)
- ticket - билет

Схема базы данных:


Для сокрытия деталей реализации хранения сущностей был смоделирован дополнительный слой DTO.
Для работы с базой данный был смоделирован DAO слой.

## Business layer
Логика приложения реализована в Service классах (Stateless Session Beans)
В бизнес-слое реализована логика поиска свободных мест (ряд и место) с учетом выбора сеанса.

## View layer
За обработку запросов отвечают сервлеты, пользовательский интерфейс реализован на jsp страницах.

### Демонстрация работоспособности
Стартовая страница, если пользователь не в системе. На ней посетитель вводит данные (на кого оформить билет) и указывает, имеет ли потенциальный посетитель какие-то льготы
(от этого зависит стоимость билета (310 - обычная цена; 200 - льготная))
![image](https://github.com/sumrako/ESA_LR_1/assets/67976572/48cec8b3-7234-4177-8e2e-b58d94e8dbd2)


Далее пользователю предоставляется выбор спектакля:
![image](https://github.com/sumrako/ESA_LR_1/assets/67976572/dfd73170-5f4b-42c8-bdc6-5a46575c5c03)



После выбора спектакля, пользователь может выбрать время, на которое хочет купить билет
![image](https://github.com/sumrako/ESA_LR_1/assets/67976572/5f05bb9e-21a3-4f2f-993c-aec236368e92)



После выбора времени мы видим список свободных мест
![image](https://github.com/sumrako/ESA_LR_1/assets/67976572/b0689995-c3d3-432f-b74d-c92d32cbc36c)



После покупки билета, эти места исчезают из списка свободных и отображаются по ссылке "my tickets". Из "my tickets" можно удалить билет, нажав на "delete"

![image](https://github.com/sumrako/ESA_LR_1/assets/67976572/94055dd4-affe-4f75-8ba1-41a908d3d95c)



 


