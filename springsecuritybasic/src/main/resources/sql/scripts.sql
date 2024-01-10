create databases eazybank;

show tables;
-- users 테이블 생성
CREATE TABLE users (
                       id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(500) NOT NULL,
                       enabled BOOLEAN NOT NULL
);

-- authorities 테이블 생성
CREATE TABLE authorities (
                             id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             username VARCHAR(50) NOT NULL,
                             authority VARCHAR(50) NOT NULL
    -- CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);

-- 커스텀 테이블을 통한 인증을 위한
-- customer 테이블 생성
CREATE TABLE customer (
                          id int NOT NULL AUTO_INCREMENT,
                          email varchar(45) NOT NULL,
                          pwd varchar(200) NOT NULL,
                          role varchar(45) NOT NULL,
                          PRIMARY KEY (id)
)

    -- -- authorities 테이블에 대한 유니크 인덱스 생성
-- CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);

    INSERT IGNORE INTO users VALUES (NULL, 'happy', '12345', '1');
INSERT IGNORE INTO authorities VALUES (NULL, 'happy', 'write');
INSERT IGNORE INTO customer(email, pwd, role ) VALUES ('gyhung@example.com', 54321, 'admin');

