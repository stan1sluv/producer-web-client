# Producer API Client

🚀 REST service for sending JSON events to Apache Kafka

## 🛠️ Technologies

- Java 21
- Spring Boot 3.5.5
- Apache Kafka
- ZooKeeper

## ⚙️ Configuration

```yaml
spring:
  application:
    name: producer-api-client
  kafka:
    bootstrap-servers: localhost:9092
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
      topic:
        name: TOPIC_RS.V1 #Default topic
server:
  port: 8080
```

💡 The service is configured with the parameter `MAX_BLOCK_MS_CONFIG=10000`:
- If it's impossible to connect to Kafka within 10 seconds, an exception will be thrown, and information about it will be provided in the HTTP response
- After the exception, background connection attempts continue

## 📡 API Endpoints

### 📨 Sending a message to Kafka

**Endpoint:** `POST /v1/producer/send`

**Parameters:**
- `topic` (optional) - the name of the Kafka topic to send the message to
- Request body - JSON object to send

## 📨 Request examples

### 1. 📤 Sending to a specified topic

```bash
curl --request POST \
  --url 'http://localhost:8080/v1/producer/send?topic=TOPIC_RS.V2' \
  --header 'Content-Type: application/json' \
  --data '{
  "val" : 123
}'
```

### 2. 📤 Sending to the default topic (TOPIC_RS.V1)

```bash
curl --request POST \
  --url http://localhost:8080/v1/producer/send \
  --header 'Content-Type: application/json' \
  --data '{
  "val" : 123
}'
```

## 📥 Response examples

### ✅ Successful response

```json
{
  "timestamp": "2025-08-27T23:09:49.774596533",
  "status": "SUCCESS",
  "message": "Message was sent successfully",
  "error": null
}
```

### ❌ Error (invalid topic name)

```json
{
  "timestamp": "2025-08-27T23:18:11.812",
  "status": "ERROR",
  "message": "Error while sending a message to a topic",
  "error": "org.apache.kafka.common.errors.InvalidTopicException: \"TOPIC_RS.V2\""
}
```

### ❌ Error (method not supported)

```json
{
  "timestamp": "2025-08-27T23:27:59.744",
  "status": "ERROR",
  "message": "Error while sending a message to a topic",
  "error": "Request method 'PUT' is not supported"
}
```

## 🔄 Work logic

1. If the `topic` parameter is specified in the request, the message is sent to the specified topic
2. If the `topic` parameter is not specified, the message is sent to the default topic (TOPIC_RS.V1)
3. A unique key (UUID) is generated for each message
4. If it is impossible to connect to Kafka within 10 seconds, an error is returned to the client