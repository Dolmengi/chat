# Chatting Application

## 프로젝트 개요

Java와 Spring Boot를 기반으로 구축된 채팅 애플리케이션입니다.

## 기술 스택

*   **언어:** Java 25
*   **프레임워크:** Spring Boot 4.0.1, Spring Cloud
*   **빌드 도구:** Gradle
*   **기타:** Lombok, JUnit

## 모듈 구성

*   `common`: 프로젝트 내 모든 모듈에서 공통으로 사용하는 코드
*   `api-server`: API 요청 처리
*   `pub-server`: 메시지 발행/구독(Pub/Sub) 처리
*   `presence-server`: 사용자 접속 상태(Presence) 처리
*   `test-connector`: 테스트 연결용 모듈

## 빌드 방법

```bash
./gradlew build
```

## 실행 방법

각 서비스는 다음 명령어로 개별 실행할 수 있습니다:

```bash
./gradlew :<service-name>:bootRun
```

예를 들어 `api-server`를 실행하려면:

```bash
./gradlew :api-server:bootRun
```
