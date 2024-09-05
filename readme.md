### Пример работы с хранилищем объектов MinIO в Java  
MinIO — это объектное хранилище данных с открытым исходным кодом, совместимое с Amazon S3 API.

Оно позволяет хранить неструктурированные данные, такие как фото, видео, логи, резервные копии и т. д.

Основные возможности MinIO:

* масштабируемое хранение объектов;

* S3-совместимый API;

* высокая доступность;

* шифрование данных;

* многопользовательский доступ;

* версионирование объектов;

* репликация данных;

* кэширование на уровне хоста;

* расширяемость;

* веб-интерфейс.
#### Стек  
* Spring Boot 3, Java 17, Maven
* Docker-Compose + MinIO  

#### Запуск проекта (вне кластера)  
***Внимание! Перед началом работы у вас на ПК должен быть установлен Docker, Docker Compose и Maven!***  
1. Созддайте в корне проекта каталог data (место, куда будут сохраняться файлы):  
```
mkdir data
```  
2. Запусить сервис MinIO (хранилище объектов). В корне проекта вызвать команду:  
```
docker-compose up -d
```
Результат работы:  
```
roman@roman-MS-7C83:~/IdeaProjects/minio-spring$ docker-compose ps
Name               Command                  State                                             Ports                                       
------------------------------------------------------------------------------------------------------------------------------------------
minio   /usr/bin/docker-entrypoint ...   Up (healthy)   0.0.0.0:9000->9000/tcp,:::9000->9000/tcp, 0.0.0.0:9001->9001/tcp,:::9001->9001/tcp
```
3. Зайдите через браузер в консоль хранилища (http://localhost:9001/buckets) и создайте там корзину с именем "new" (без кавычек). Логин: minio. Пароль: miniopassword  
4. В разделе http://localhost:9001/access-keys создайте ключи доступа к хранилищу и пропишите их в проекте Java:  
```
@Configuration
public class MiniIO {
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("WRlv4AYIKYCGqMfUsCfZ", "wpm4xZuYVXEx8zRuXVv8s8zCvYx1s2enl7uAwjzE")
                .build();
    }
}
```  
5 Вызовите в корне проекта команду сборки проекта:  
```
mvn clean package
```
Запустите проект (порт 8080)  
```
mvn spring-boot:run
```
6. Импортируйте коллекции postman из одноименной папки в приложение postman. В коллекции два метода: для сохранения файлов в MinIO и их получения через REST API.  
* POST http://localhost:8080/api/file/upload (сохранить файл)
* GET http://localhost:8080/api/file/download/new/report.pdf (получить файл)