# Bookmarker Project <br><br>
주제: Flyway를 통한 DB 버전 관리와 GitAction 및 Docker Compose를 활용한 프로젝트 배포 <br>
기간: 2024.09.04 ~ 2024.09.10 (1주) <br>

----------------------<br><br>

### 사용 기술 환경

Backend: Spring Boot 3.2.7, JPA, Java 17 <br>

DB: PostgreSQL, H2, Flyway <br>

Frontend: Next.js, TypeScript <br>

CI/CD: GitAction, Docker Compose <br>

기타: Shell Script(Dockerfile) <br>

----------------------<br><br>

### 기능 구현 목록


1. Next.js와 Spring Boot를 활용한 RESTful API 기반 북마크 관리 시스템
  -  북마크 리스트에 대한 CRUD 기능을 구현하여 사용자 편의성 제공. <br><br>

2. Flyway를 이용한 DB 버전 관리 및 유연한 DB 환경 전환
  -  Flyway를 통해 데이터베이스 스키마의 버전 관리를 수행, 테스트, 개발, 배포 환경에 따라 DB 벤더(PostgreSQL, H2)를 유연하게 전환 및 관리.  <br><br>


3. Next.js 서버 컴포넌트와 클라이언트 컴포넌트의 역할 분리
  -  Next.js에서 서버 컴포넌트와 클라이언트 컴포넌트를 분리하여, 요청 처리의 효율성을 높이고 사용자 인터페이스를 최적화.  <br><br>


4. Docker Compose 기반 프로젝트 배포 및 관리
  -  docker-compose up을 통해 컨테이너 기반의 프로젝트 배포 및 관리를 자동화하여 개발 환경과 프로덕션 환경의 일관성을 유지.  <br><br>


5. GitHub Actions를 통한 CI/CD 자동화 파이프라인 구축
  -  GitHub Actions를 사용해 빌드, 테스트, 배포 과정의 자동화 구현. 지속 가능한 CI/CD 파이프라인을 구축하여 프로젝트의 효율적인 관리와 배포를 보장.  <br><br>

----------------------<br><br>
