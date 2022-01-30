# car_catalog
Test job

Запуск в Docker
docker build  -t car_catalog:v1 .
docker run -p 8080:8080 imageId

Описание API

GET - /api/statistics - статистика базы данных.

Ответ : 200,
{
    "carsCount": 1,
    "carsByBrand": [
        {
            "brand": "toyota",
            "count": 1
        }
    ],
    "carsByAge": [
        {
            "count": 1,
            "period": "90s"
        },
        {
            "count": 0,
            "period": "00s"
        },
        {
            "count": 0,
            "period": "10s"
        },
        {
            "count": 0,
            "period": "20s"
        }
    ],
    "carsByColor": [
        {
            "color": "black",
            "count": 1
        }
    ]
}
GET - /api/cars - список автомобилей.
Параметр - fields=fieldName… - фильтрация отображаемых полей

Запрос - /api/cars
Ответ: 200,
[
    {
        "id": 3,
        "licenseNumber": "А611АА196",
        "brand": "Acura",
        "model": "RSX",
        "color": "blue",
        "year": 2002
    }
]
Запрос  c фильтрацией - /api/cars?fields=id,brand,year
Ответ: 200,
[
    {
        "id": 3,
        "brand": "Acura",
        "year": 2002
    }
]

POST - /api/cars - добавление авто
Параметры: 
licenseNumber - номер автомобиля, обязателен. 
Формат номера A111AA111 или A111AA11 с использованием букв кириллицы  'АВЕКМНОРСТУХ';
brand - марка, обязателен;
model - модель;
color - цвет;
year - год, значение от 1990 до текущего;

Успешный запрос - /api/cars?licenseNumber=А611АА196&brand=Acura&model=RSX&color=blue&year=2002

Ответ: 200, “Car added”

Запрос с некорректно введенными данными, либо при попытке добавить авто с ранее добавленным номером - /api/cars?licenseNumber=asdas&brand=Acura&model=RSX&color=blue&year=2002
Ответ: 400,
{
    "message": "Wrong number. Use A111AA111 or A111AA11 format with 'АВЕКМНОРСТУХ' cyrillic letters",
    "status": "BAD_REQUEST",
    "timestamp": "2022-01-30T12:11:34.9148292"
}
 
DELETE - /api/cars/{id} - удаление авто
Параметр - id - id автомобиля.
 
Успешный запрос - /api/cars/2
Ответ: 200, “Car deleted”
 
Ошибочный запрос - /api/cars/2
Ответ: 400
{
    "message": "Car with id 2 not found",
    "status": "BAD_REQUEST",
    "timestamp": "2022-01-30T12:33:56.257105"
}
 
PUT - /api/cars/{id} - обновление авто
Параметры: 
id - id автомобиля
licenseNumber - номер автомобиля, обязателен. 
Формат номера A111AA111 или A111AA11 с использованием букв кириллицы  'АВЕКМНОРСТУХ';
brand - марка, обязателен;
model - модель;
color - цвет;
year - год, значение от 1990 до текущего;

Успешный запрос - /api/cars/2
Ответ: 200, “Car deleted”
 
Запрос с некорректно введенными данными, либо при попытке изменить номер на существующий у другого авто - http://localhost:8080/api/cars/2?licenseNumber=А611АА198&brand=toyota&model=rsx&color=black&year=1995
Ответ: 400
{
    "message": "Other car with number А611АА198 exists",
    "status": "BAD_REQUEST",
    "timestamp": "2022-01-30T12:51:52.8503246"
}
 
