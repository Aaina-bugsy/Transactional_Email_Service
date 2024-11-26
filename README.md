# Transactional Email Service

## Overview
The Transactional Email Service is a robust microservice that sends transactional emails such as password resets, welcome emails, and notifications. The service is built using Java, Spring Boot, and integrates with SendGrid ( or Mailgun ) for email delivery. It incorporates RabbitMQ for queuing and MySQL for logging email transactions.

The service also supports deployment using Docker for easy containerization and scalability.

---

## Features 
- **Transactional Emails** : Send password resets, notifications, and other transactional emails.
- **Retry Mechanism** : Ensures reliable email delivery in case of failures.
- **Queue Management** : Uses RabbitMQ to handle email requests asynchronously.
- **Logging** : Logs email details and statuses in MySQL.
- **Multiple Email Providers** : Supports both Mailgun and SendGrid for email delivery.
- **Containerization** : Docker support for easy deployment.

---

## Tech Stack ⚙️
- **Backend**: Java, Spring Boot
- **Queueing**: RabbitMQ
- **Email Providers**: Mailgun, SendGrid
- **Database**: MySQL
- **Containerization**: Docker

---

## File Structure
```plaintext
src/
├── main/
│   ├── java/com/example/transactionalemailservice/
│   │   ├── config/
│   │   │   ├── SendGridConfig.java
│   │   │   ├── RabbitMQConfig.java
│   │   ├── controller/
│   │   │   ├── EmailController.java
│   │   ├── dto/
│   │   │   ├── EmailRequest.java
│   │   ├── entity/
│   │   │   ├── Email_e.java
│   │   ├── repository/
│   │   │   ├── EmailRepository.java
│   │   ├── service/
│   │   │   ├── EmailConsumer.java
│   │   │   ├── EmailService.java
│   │   ├── TransactionalEmailServiceApplication.java
├── resources/
│   ├── application.properties
```
---

## Steps to Run Locally 🛠️
### 1. Clone the Repository 📂
Clone this repository to your local machine using the following command:

```bash
git clone https://github.com/your-username/transactional-email-service.git
cd transactional-email-service
```

### 2. Set Up RabbitMQ Install and run RabbitMQ 📥
```bash
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management

```
Access the RabbitMQ Management Console at http://localhost:15672 



### 3. Set Up MySQL
Create a database named email_service and table named emails and configure the credentials in application.properties:
```sql
CREATE DATABASE email_service;
```

### 4. Configure Email Provider
####  For Mailgun
Add the following properties in application.properties:
```properties
mailgun.api.base-url=https://api.mailgun.net/v3/yourdomain.com
mailgun.api.key=your_mailgun_api_key
```

#### For SendGrid
Add these instead:
```properties
sendgrid.api.key=your_sendgrid_api_key
```

### 5. Run the Application
```bash
mvn spring-boot:run
```

### 6. Test the Endpoints
Send a POST request to http://localhost:8080/emails/send
```json
{
    "recipient": "example@example.com",
    "subject": "Test Email",
    "body": "This is a test email."
}

```
---

## Docker Deployment
#### 1. Build the Docker Image

```bash
docker build -t transactional-email-service .

```
#### 2. Run the Docker Container

```bash
docker run -d -p 8080:8080 --name email-service transactional-email-service
```
---

## API Endpoints
### Send Email
- Endpoint: /emails/send
- Method: POST
- Request Body:
```json
{
    "recipient": "recipient@example.com",
    "subject": "Subject Line",
    "body": "Email body content"
}
```
- Response:
```json
{
    "message": "Email queued for sending."
}

```
---

## Future Enchancements
- Add support for more email providers like Amazon SES.
- Implement advanced analytics and reporting for email delivery.
- Enhance retry logic with exponential backoff.
