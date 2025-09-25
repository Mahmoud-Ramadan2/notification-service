# 📢 Notification Service - Doctor Appointment System

The **Notification Service** is a standalone microservice in the *Doctor Appointment System*.  
It is responsible for sending notifications to users (e.g., email alerts, booking confirmations, reminders).  
The service consumes **Kafka events**, integrates with **Spring Mail** for email delivery, and registers with **Eureka** for service discovery.

---

## 🔧 Tech Stack

- **Java 21**
- **Spring Boot 3.4.5**
- **Spring Cloud 2024.0.1**
- **Spring Kafka**
- **Spring Mail**
- **Spring Cloud Gateway**
- **Eureka Client**
- **Lombok**
- **JUnit 5 + Spring Boot Test**
- **dotenv-java** for environment variable management

---

## 📦 Features

- ✅ Listens to **Kafka topics** for notification-related events
- 📧 Sends **email notifications** (via SMTP, e.g., Gmail)
- 📡 Registers with **Eureka Server** for service discovery
- 🔄 Configurable via **`.env` file** (separates dev/prod configs)
- ♻️ Designed with **SOLID principles** for maintainability and scalability

---

## 📁 Project Structure

```
notification-service/
│
├── src/
│   ├── main/
│   │   ├── java/com/mahmoud/appointmentsystem/notification/  # Core business logic
│   │   └── resources/
│   │       ├── application.properties                         # Uses environment variables
│   │       ├── logback-spring.xml                       # Logging configuration
│   └── test/                                                  # Unit & integration tests
├── pom.xml
├── .env                                                       # Environment config
└── README.md
```

---

## ⚙️ Configuration

### `application.properties`

All sensitive and dynamic values are loaded from a `.env` file.

### 📄 Sample `.env`

```dotenv
SERVER_PORT=8083
SPRING_APPLICATION_NAME=notification-service

# Eureka
EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://localhost:8761/eureka/
EUREKA_INSTANCE_PREFERIPADDRESS=true

# Kafka
KAFKA_BOOTSTRAP_SERVERS=localhost:9092
KAFKA_CONSUMER_GROUP=notification-group
KAFKA_CONSUMER_OFFSET=earliest
KAFKA_CONSUMER_KEY_DESERIALIZER=org.apache.kafka.common.serialization.StringDeserializer
KAFKA_CONSUMER_VALUE_DESERIALIZER=org.springframework.kafka.support.serializer.JsonDeserializer

# Mail Configuration
MAIL_HOST=smtp.gmail.com
MAIL_PORT=587
MAIL_USERNAME=your_email@gmail.com
MAIL_PASSWORD=your_app_password
MAIL_SMTP_AUTH=true
MAIL_SMTP_STARTTLS_ENABLE=true

```

---

## 🧪 Testing

- **JUnit 5** for unit testing
- **Spring Boot Test** for integration testing
- Kafka consumers and mail service can be mocked for isolated testing

---

## 🛰 Dependencies Overview (from `pom.xml`)

| Purpose              | Library                                   |
|----------------------|--------------------------------------------|
| Web Framework        | `spring-boot-starter-web`                 |
| Kafka Messaging      | `spring-kafka`                            |
| Email Notifications  | `spring-boot-starter-mail`                |
| Service Discovery    | `spring-cloud-starter-netflix-eureka-client` |
| API Gateway Support  | `spring-cloud-starter-gateway-mvc`        |
| Env Management       | `dotenv-java`                             |
| Dev Tools            | `spring-boot-devtools`                    |
| Boilerplate Removal  | `lombok`                                  |
| Testing              | `spring-boot-starter-test`                |

---

## 🚀 Running the Service

### Prerequisites

- Java 21
- Maven
- Kafka & Zookeeper running (`localhost:9092`, `localhost:2181`)
- Eureka Server running on port `8761`
- SMTP mail server (e.g., Gmail SMTP)

#### Kafka Quick Start

- Running Kafka with Docker Compose

This project requires Kafka and Zookeeper.  
A preconfigured `docker-compose.kafka.yml` file is included in the root directory.

Start the services with:

```bash
docker compose -f docker-compose.kafka.yml up -d
````
 - docker-compose.yml for kafka
```bash
# Here docker-compose file to install kafka

services:
 # 1. Zookeeper service
 zookeeper:
  image: confluentinc/cp-zookeeper:7.7.0
  container_name: zookeeper
  ports:
   - "2181:2181"
  environment:
   ZOOKEEPER_CLIENT_PORT: 2181
   ZOOKEEPER_TICK_TIME: 2000
  networks:
    - kafka-network
    
 # 2. kafka service
 kafka:
  image: confluentinc/cp-kafka:7.7.0
  container_name: kafka
  ports:
   - "9092:9092"
  environment:
   KAFKA_BROKER_ID: 1
   KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
   KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
   KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
  depends_on:
   - zookeeper
  networks:
    - kafka-network
    
networks:
 kafka-network:
  driver: bridge
 ```
### Service Start Order

1. **Eureka Server**
2. **Gateway Service**
3. **Kafka + Zookeeper**
4. **Notification Service**
5. Other services (e.g., User Service, Appointment Service)

### Run via Maven:

```bash
mvn clean spring-boot:run
```

Or package as JAR:

```bash
mvn clean package
java -jar target/notification-service-0.0.1-SNAPSHOT.jar
```

---

## 📌 Integration with Other Services

| Service              | Port   | Purpose                          |
|----------------------|--------|----------------------------------|
| Eureka Server        | `8761` | Service Discovery                |
| Gateway service      | `8080`  | App Entry Point                  |
| User Service         | `8081` | Authentication & User Management |
| Appointment Service  | `8082` | Booking & Scheduling             |
| Notification Service | `8083` | Notifications (Kafka + Email)    |

- Other services publish **events to Kafka**
- Notification Service consumes those events and sends emails

---

## ✍️ Author

**Mahmoud Ramadan**  
Email: [mahmoudramadan385@gmail.com](mailto:mahmoudramadan385@gmail.com)

---

## 🛡️ License

This project is licensed under a proprietary license.  
For use or redistribution, contact the author.
