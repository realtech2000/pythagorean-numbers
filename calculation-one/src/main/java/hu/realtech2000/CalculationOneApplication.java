package hu.realtech2000;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;

@SpringBootApplication
@EnableBinding(Processor.class)
public class CalculationOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculationOneApplication.class, args);
	}
}
