# Booking


### Skills

- Kotlin
- Java 17
- Spring Boot 3.2.3
- Mysql
- Flyway
- Redis

### Naming

- 유저 : User
- 인증 : Auth
- 위탁 : Consignment
- 대여 : Rental

### Fetures

- 유저
  - 회원 가입
- 인증
  - 로그인
  - 토큰 재발행
- 위탁
  - 위탁 도서 전체 조회
  - 위탁 도서 조회
  - 도서 위탁
- 대여
  - 도서 대여
  - 도서 반납

### 애플리케이션 실행 절차

#### 환경 세팅

- Mysql
    - scheme 생성
        - woodo
    - url
        - localhost:3306
    - username
        - root
    - password
        - 1234
- Redis
    - host
        - 127.0.0.1
        - 6379

#### Local Database setup with Docker

```$ docker pull mysql```

```$ docker run --name woodo-mysql -e MYSQL_ROOT_PASSWORD=1234 -d -p 3306:3306 mysql:latest```

```$ docker exec -it woodo-mysql bash```

```# mysql -u root -p```

```비밀번호 입력```

```mysql> CREATE SCHEMA woodo```

```$ docker pull redis```

```$ docker run -p 6379:6379 redis```

#### Application 실행 순서

1. git clone
2. BootRun

#### Swagger

- localhost:8080/swagger-ui/index.html 접속 후 사용
- 회원가입, 로그인 기능을 제외한 기능은 Authorization 필요
- api/v1/auth/login 에서 받아온 ```access token``` 값을 우측 상단 Authorize 클릭 후 value에 입력
  - Bearer 추가 필요 없음

#### 구현 및 추가 설명

- 행위를 취하는 유저의 정보를 body로 받는 것에 대해 어색함을 느껴 security 기능을 추가하여 principal 사용을 할 수 있도록 하였습니다.
- Redis는 Redis Key Expired Event를 활용하여 도서 대여 후 약 10초가 지나고 반납 처리할 수 있도록 구현하였습니다.
- 편의를 위해 일부 데이터를 삽입하는 sql문을 작성하였습니다.
- 편한 환경 설정을 위해 database username, password는 간단하게 정하였습니다.
- 추가적으로 application yml도 환경변수 사용을 안하는 등 바로 실행할 수 있도록 구성하였습니다.