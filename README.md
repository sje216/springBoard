## 🚀 프로젝트 소개

Spring 기반 백엔드 시스템 설계 및 구현을 목표로 진행한 프로젝트입니다.  
단순 CRUD를 넘어, 확장성과 유지보수성을 고려한 구조 설계에 집중했습니다.

- RESTful API 설계
- 계층형 아키텍처 적용 (Controller - Service - Repository)
## 🏗️ 아키텍처
<img width="1536" height="1024" alt="" src="https://github.com/user-attachments/assets/9a367559-5529-47bb-af8f-c4c3dd40783b" />



### 📡 실시간 통신 구조

- 클라이언트 간 실제 영상/음성 데이터는 WebRTC를 통해 P2P로 직접 통신
- 서버(Spring)는 signaling 데이터만 중계
- Redis를 통해 사용자 상태 및 연결 정보 관리

- Spring Boot 기반
- 계층형 구조 (Layered Architecture)
- (예: Redis 캐싱 / WebSocket / WebRTC signaling 등)

### 구조
Controller → Service → Repository → DB

### 주요 설계 포인트

- 역할 분리를 통한 유지보수성 향상
- 서비스 레이어에서 비즈니스 로직 집중 관리
- Redis 기반 상태 관리 구조 적용
- WebRTC signaling 서버 구조 설계

---

## ⚙️ 기술 스택

### Backend
- Java
- Spring Boot

### Database
- MySQL
- Redis (세션 및 상태 관리)

### Real-time Communication
- WebSocket
- WebRTC (Signaling 처리)

### Infra / DevOps
- AWS (EC2)
- GitHub Actions (CI/CD)

---

## 📌 주요 기능

### 1. WebRTC Signaling 서버 구현
- 클라이언트 간 연결을 위한 signaling 처리
- Offer / Answer / ICE Candidate 전달 로직 구현
- Peer 간 연결 상태 관리

### 2. 실시간 메시지 처리
- WebSocket 기반 실시간 데이터 송수신
- 다중 사용자 환경 고려한 메시지 흐름 설계

### 3. Redis 기반 상태 관리
- 사용자 세션 및 연결 상태 저장
- 다중 서버 환경 확장 고려

### 4. REST API 설계
- 사용자 및 리소스 관리 API
- 계층형 구조 기반 API 구현

---

## 🔥 트러블슈팅

### 1. WebRTC 연결 불안정 문제

- 문제: Peer 간 연결이 중간에 끊기거나 실패
- 원인: 사용자 상태 및 signaling 데이터 관리 부족
- 해결:
  - Redis를 활용한 사용자 상태 관리 도입
  - signaling 흐름 재정의 및 이벤트 처리 개선

---

### 2. CORS 에러 발생

- 문제: 프론트엔드에서 API 요청 차단
- 원인: 서버 CORS 설정 미흡
- 해결:
  - Spring Security 및 WebMvc 설정 수정
  - 허용 Origin 및 Header 명시

---

### 3. 새로고침 시 사용자 상태 불일치

- 문제: 브라우저 새로고침 시 기존 연결 데이터 유지
- 원인: 서버 측 상태 정리 미흡
- 해결:
  - 연결 종료 시점 로직 개선
  - Redis 데이터 정리 로직 추가

---

## 📈 성능 및 구조 개선

- Redis 도입으로 상태 조회 속도 개선
- 불필요한 DB 접근 최소화
- 실시간 이벤트 처리 구조 개선
- 확장 가능한 signaling 구조 설계

---

## 🧪 실행 방법

```bash
git clone https://github.com/sje216/springBoard.git
cd springBoard
./gradlew build
./gradlew bootRun

## 프로젝트를 통해 얻은 것
- 실시간 통신(WebRTC) 구조에 대한 이해
- Spring 기반 서버 아키텍처 설계 경험
- 상태 관리의 중요성과 Redis 활용 경험
- 트러블슈팅을 통한 문제 해결 능력 향상

## 향후 개선 계획
- 인증/인가 (JWT) 적용
- 서버 이중화 및 확장 구조 개선
- 모니터링 시스템 도입 (로그/메트릭)
- 테스트 코드 강화


