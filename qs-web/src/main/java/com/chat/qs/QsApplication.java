package com.chat.qs;

import com.chat.qs.config.socket.NettyServer;
import com.chat.qs.word.enable.EnableWord;
import com.ming.tools.generate.template.enable.EnableGenerate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.chat.qs.mapper")
@SpringBootApplication
//@EnableWord
@EnableGenerate(basePackages = {"com.chat.qs"})
public class QsApplication {

	public static void main(String[] args) {
		SpringApplication.run(QsApplication.class, args);
		try {
			new NettyServer(12345).start();
			System.out.println("Netty Server start...");
		}catch(Exception e) {
			System.out.println("Netty Server Error:"+e.getMessage());
		}
	}

}
