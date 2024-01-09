package com.eazybytes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//실행해줄 파일을 parant외부에 설정해주었을 경우
//@ComonentScan("해당 클래스 경로")로 작성해줄 수 있다. /-> Optional
@SpringBootApplication
public class EazyBankBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EazyBankBackendApplication.class, args);
	}

}
