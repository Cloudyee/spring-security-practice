package com.eazybytes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//실행해줄 파일을 parant외부에 설정해주었을 경우
//@ComonentScan("해당 클래스 경로")로 작성해줄 수 있다. /-> Optional

//jpa repo를 폴더 밖에 작성했을 경우
//@EnableJpaRepositories("레포키토리 경로")

//엔티티를 폴더 밖에 작성했을 경우 엔티티 경로 작성
//@EntityScan("엔티티 경로")
//@EnableWebSecurity(debug = true)
@SpringBootApplication
public class EazyBankBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EazyBankBackendApplication.class, args);
	}
}
