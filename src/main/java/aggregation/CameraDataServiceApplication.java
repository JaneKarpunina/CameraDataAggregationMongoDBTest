package aggregation;

import aggregation.client.CameraWebClient;
import aggregation.client.repository.CameraDataAggregatedRepository;
import aggregation.client.service.CameraDataAggregatedOperations;
import aggregation.domain.CameraDataAggregated;
import aggregation.service.CameraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class CameraDataServiceApplication {


	private static final Logger LOGGER = LoggerFactory.getLogger(CameraDataServiceApplication.class);
	/*@Bean
	CameraService employeeRepository() {
		return new CameraService();
	} *///TODO: what is that???why???

	/*@Bean
	CameraWebClient cameraWebClient(CameraDataAggregatedOperations cameraDataAggregatedOperations)  {
		return new CameraWebClient(cameraDataAggregatedOperations);
	}*/

	public static void main(String[] args) {
		SpringApplication.run(CameraDataServiceApplication.class, args);

		//CameraWebClient cameraWebClient = ApplicationContext.getBean();

		ApplicationContext context =
				new AnnotationConfigApplicationContext(CameraDataServiceApplication.class);
		for (String str : context.getBeanDefinitionNames()){
			System.out.println(str);
		}
		((CameraWebClient)context.getBean("cameraWebClient")).consume();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		Flux<CameraDataAggregated> cameraDataAggregated =
				((CameraDataAggregatedOperations)context.getBean("cameraDataAggregatedOperations"))
						.findAll();

		cameraDataAggregated.subscribe(e -> LOGGER.info("Camera data aggregated persisted : {}", e));


	}
}