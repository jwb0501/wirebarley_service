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

---

## 📘 응답 코드 목록 (Response Code List)
✅ 성공 코드
| 코드     | 메시지          |
| ------ | ------------ |
| `S001` | 계좌가 생성되었습니다. |
| `S002` | 계좌가 삭제되었습니다. |
| `S003` | 입금이 완료되었습니다. |
| `S004` | 출금이 완료되었습니다. |
| `S005` | 이체가 완료되었습니다. |
| `S006` | 조회가 완료되었습니다. |

❌ 오류 코드
| 코드     | 메시지                           |
| ------ | ----------------------------- |
| `E001` | 계좌를 찾을 수 없습니다.                |
| `E002` | 이미 존재하는 계좌번호입니다.              |
| `E003` | 잔액이 부족합니다.                    |
| `E004` | 일 출금 한도를 초과했습니다.              |
| `E005` | 일 이체 한도를 초과했습니다.              |
| `E006` | 요청 값이 올바르지 않습니다.              |
| `E007` | 동시에 수정된 요청이 존재합니다. 다시 시도해주세요. |
