# 💸 Simple Banking API

Java 17 / Spring Boot / JPA 기반의 간단한 계좌 관리 시스템입니다.  
계좌 생성, 삭제, 입출금, 이체, 거래내역 조회 기능을 제공합니다.

## 📦 기술 스택

- Java 17
- Spring Boot
- Spring Data JPA (Hibernate)
- H2 In-Memory Database
- Swagger (springdoc-openapi)
- Docker / Docker Compose

---

## 🚀 실행 방법

### 1. 프로젝트 빌드

```bash
./gradlew clean build
```

### 2. Docker Compose로 실행

```bash
docker-compose up --build
```

### 3. Swagger 문서 접속
http://localhost:8080/swagger-ui.html

### 4. H2 Database 콘솔 접속
http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:testdb
- 사용자명: sa
- 비밀번호: (비어 있음)

---

## ⚙️ API 기능

### 계좌 관리
- 계좌 생성
- 계좌 삭제

### 거래 처리
- 입금
- 출금 (일 최대 1,000,000원 제한)
- 이체 (일 최대 3,000,000원 제한, 수수료 1%)
- 거래내역 조회 (최신순 정렬)

---

## 🛠 개발 시 참고 사항
- Swagger를 통해 API 명세 확인 가능
