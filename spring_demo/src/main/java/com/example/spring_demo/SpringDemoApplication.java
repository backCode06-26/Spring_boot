package com.example.spring_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// RestController 는 Controller와 ResponseBody을 합쳐놓는 애노테이션으로
// 컨트롤러 처럼 페이지을 매핑할수있고, 반환값을 그대로 웹에 띄울수있다.

@RestController
@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}

	@GetMapping(value = "/")
	public String HelloWorld() {
		return "Hello World";
	}
}
