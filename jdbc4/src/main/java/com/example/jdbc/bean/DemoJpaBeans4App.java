package com.example.jdbc.bean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class DemoJpaBeans4App {

	public static void main(String[] args) {
		SpringApplication.run(DemoJpaBeans4App.class, args);
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(MyConfig.class);
		ctx.refresh();
		HelloWorld obj=ctx.getBean(HelloWorld.class);
		HelloWorld obj1=ctx.getBean(HelloWorld.class);
		obj.setData("Naveen AA fan");
		obj.setMsg("Naveen is always AA fan");
		System.out.println(obj);
		System.out.println(obj1);
	}

}
