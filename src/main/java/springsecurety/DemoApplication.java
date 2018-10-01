package springsecurety;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import springsecurety.controller.RestControllers;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(DemoApplication.class, args);
		RestControllers employeeConsumerClient = ctx.getBean(RestControllers.class);
		System.out.println(employeeConsumerClient);
		employeeConsumerClient.getEmployee();

	}

}
