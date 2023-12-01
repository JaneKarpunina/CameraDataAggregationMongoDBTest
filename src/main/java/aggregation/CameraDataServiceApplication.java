package aggregation;

import aggregation.client.CameraWebClient;
import aggregation.service.CameraService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CameraDataServiceApplication {

	@Bean
	CameraService employeeRepository() {
		return new CameraService();
	}

	public static void main(String[] args) {
		SpringApplication.run(CameraDataServiceApplication.class, args);

		CameraWebClient cameraWebClient = new CameraWebClient();
		cameraWebClient.consume();
	}
}