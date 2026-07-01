# 📋 springBoard

Spring Boot 기반의 웹 게시판 프로젝트입니다. 회원가입/로그인부터 게시글·댓글의 생성/조회/수정/삭제(CRUD), 페이징, 키워드 검색, 파일 업로드/다운로드까지 실무 웹 애플리케이션의 핵심 기능을 직접 구현하고 학습한 프로젝트입니다.

---

## 🛠️ Tech Stack (기술 스택)

### Backend
- **Language**: Java 17
- **Framework**: Spring Boot
- **Persistence Framework**: MyBatis
- **Database**: Oracle Database

### Frontend
- **View Engine**: Thymeleaf
- **Languages**: HTML5, CSS3, JavaScript

---

## ✨ Key Features (주요 기능)

### 👤 회원 관리
- **회원가입 및 로그인**: 사용자 인증을 통한 회원 가입 및 보안 로그인 기능

### ✍️ 게시판 (CRUD)
- **게시글 목록 조회**: 대량의 게시글을 효율적으로 관리하기 위한 **페이징(Paging) 및 정렬** 처리
- **게시글 상세 조회**: 상세 페이지 진입 시 자동으로 **조회수 증가** 로직 수행
- **게시글 관리**: 게시글 작성, 수정, 삭제 기능 구현

### 🔍 검색 & 💬 소통
- **키워드 검색**: 제목, 내용 등 원하는 조건으로 게시글을 쉽게 찾을 수 있는 **검색 기능**
- **댓글 관리**: 게시글 내에서 실시간 소통이 가능하도록 **댓글 작성 및 삭제** 기능 제공

### 📁 파일 관리
- **파일 업로드/다운로드**: 게시글 작성 시 파일 첨부 기능 및 업로드된 파일을 안전하게 다운로드할 수 있는 기능 구현

---

## 🚀 How to Run (실행 방법)

### Prerequisites (사전 준비)
- Java 17 이상 설치
- Oracle Database 환경 구축
- IDE (IntelliJ IDEA 등)

### Installation & Run (설치 및 실행)
1. 저장소 클론 (Clone)
   ```bash
   git clone [https://github.com/sje216/springBoard.git](https://github.com/sje216/springBoard.git)

   
