package com.zee.zee5app;

import java.math.BigDecimal;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.zee.zee5app.config.Config;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.repository.impl.UserRepositoryImpl;

public class MainSpring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//we need to establish spring conn. / env
		//we need to initialize application container using java based confiiguraion
		AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
//		UserRepository userRepository = applicationContext.getBean(UserRepository.class);
//		UserRepository userRepository2 = applicationContext.getBean(UserRepository.class);
//		System.out.println(userRepository.equals(userRepository2));
//		
//		DataSource datasource = applicationContext.getBean("dataSource", DataSource.class);
//		System.out.println(datasource != null);
//		
//		try {
//			Register register = new Register("rush0012", "rushik12", "kumar12", "rushik@gmail.com12", "12345612");		
//			register.setContactNumber(new BigDecimal("1234567812"));
//			System.out.println(userRepository.addUser(register));
//		}catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		DataSource ds1 = applicationContext.getBean("ds", DataSource.class);
		DataSource ds2 = applicationContext.getBean("ds", DataSource.class);
		System.out.println(ds1.equals(ds2));
		applicationContext.close();
	}

}
