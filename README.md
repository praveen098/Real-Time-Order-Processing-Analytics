# 📦 Real-Time Order Processing & Analytics System

A high-throughput real-time order ingestion, processing, and analytics platform using Kafka, Spring Boot, PostgreSQL, and Elasticsearch. Designed to demonstrate scalable architecture for event-driven systems with persistent storage, schema validation, and real-time insights.

---

## 🚀 Features

- ✅ **Order Ingestion API** – Accepts new orders via REST with validation and persistence.
- 🔄 **Event-Driven Pipeline** – Kafka-based producer/consumer model for decoupled processing.
- 🧾 **Schema Registry** – Ensures backward compatibility using Confluent Schema Registry.
- 📊 **Real-Time Analytics** – Stores events in Elasticsearch for fast querying and visualization.
- 🧪 **Local Dev Environment** – Easily spin up using Docker Compose with all services pre-configured.

---

## 🧱 Tech Stack

- **Backend**: Java, Spring Boot, Spring Web, Spring Kafka
- **Messaging**: Apache Kafka, Kafka Connect
- **Storage**: PostgreSQL, Elasticsearch
- **Orchestration**: Docker Compose
- **Monitoring**: Kafka UI (optional), Kibana (optional)

---

## 🗂 Project Structure

```
real-time-order-processing/
├── api/                     # Spring Boot REST API for placing orders
├── consumer/                # Kafka consumer for processing and persisting order events
├── docker-compose.yml       # Spins up Kafka, Zookeeper, Postgres, Elasticsearch
├── connectors/              # Kafka Connect config files
└── README.md
```

---

## ⚙️ Getting Started

### Prerequisites

- Docker + Docker Compose
- Java 17+
- Postman or curl (for testing)

### Spin Up Services

```bash
docker-compose up --build
```

This will start:
- Kafka & Zookeeper
- PostgreSQL (orders DB)
- Elasticsearch
- Kafka Connect

### Place an Order

Make a POST request to:

```
POST http://localhost:8080/api/orders
```

Sample payload:
```json
{
  "orderId": "my-order-123",
  "amount": 99.99,
  "customer": "john_doe"
}
```

---

## 📊 Analytics Queries

Once orders are processed:
- **Postgres** stores structured order data
- **Elasticsearch** indexes it for real-time dashboards

Example:
```sql
SELECT * FROM order_placed ORDER BY created_at DESC LIMIT 10;
```

---

## 🧪 Testing & Validation

- JUnit tests for API endpoints and Kafka event flow
- Schema validation with Avro & Confluent Registry
- Retry and dead-letter handling supported for consumer errors

---

## 🛠 Future Improvements

- Integrate with a frontend dashboard (Next.js or React)
- Add fraud detection module using ML
- Implement monitoring with Prometheus + Grafana

---

## 📬 Contact

Built by [Praveen Sharma](https://www.linkedin.com/in/praveen-sharma-18893b146)  
GitHub: [praveen098](https://github.com/praveen098)

If this helped you, leave a ⭐ or fork the repo!
