# car_catalog
Test job

Запуск в Docker

    docker build  -t car_catalog:v1 .

    docker run -p 8080:8080 imageId

Описание API

    GET - /api/statistics - статистика базы данных.

    GET - /api/cars - список автомобилей.

        Параметр - fields=fieldName… - фильтрация отображаемых полей

        Пример запроса - http://localhost:8080/api/cars

        Пример запроса  c фильтрацией - http://localhost:8080/api/cars?fields=id,brand,year

    POST - /api/cars - добавление авто

        Параметры: 

        licenseNumber - номер автомобиля, обязателен. 

        Формат номера A111AA111 или A111AA11 с использованием букв кириллицы  'АВЕКМНОРСТУХ';

        brand - марка, обязателен;

        model - модель;

        color - цвет;

        year - год, значение от 1990 до текущего;

        Пример запроса - http://localhost:8080/api/cars?licenseNumber=А611АА196&brand=Acura&model=RSX&color=blue&year=2002
 
    DELETE - /api/cars/{id} - удаление авто
    
        Параметр - id - id автомобиля.
 
        Пример запроса - http://localhost:8080/api/cars/2
 
    PUT - /api/cars/{id} - обновление авто
        Параметры: 
        id - id автомобиля
        licenseNumber - номер автомобиля, обязателен. 
        Формат номера A111AA111 или A111AA11 с использованием букв кириллицы  'АВЕКМНОРСТУХ';
        brand - марка, обязателен;
        model - модель;
        color - цвет;
        year - год, значение от 1990 до текущего;

        Пример запроса - http://localhost:8080/api/cars/2?licenseNumber=А611АА198&brand=toyota&model=rsx&color=black&year=1995
