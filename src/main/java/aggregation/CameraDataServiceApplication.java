package aggregation;

import aggregation.client.CameraWebClient;
import aggregation.client.ClientUtils;
import aggregation.client.repository.CameraDataAggregatedRepository;
import aggregation.client.service.CameraDataAggregatedOperations;
import aggregation.domain.CameraDataAggregated;
import aggregation.service.CameraService;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = {"aggregation.*", "aggregation.client.*"})
@EnableMongoRepositories
public class CameraDataServiceApplication extends AbstractReactiveMongoConfiguration {


	private static final Logger LOGGER = LoggerFactory.getLogger(CameraDataServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CameraDataServiceApplication.class, args);

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

		Flux<CameraDataAggregated> cameradataAggregatedFlux = ClientUtils.webClientWithTimeout().get()
				.uri("/camerasDataAggregated")
				.retrieve()
				.bodyToFlux(CameraDataAggregated.class);

		Scheduler cameradataAggregatedScheduler = Schedulers.newParallel("Camera data aggregated scheduler");


		cameradataAggregatedFlux.subscribeOn(cameradataAggregatedScheduler)
				.subscribe(element -> LOGGER.info("Camera data aggregated controller request: {}", element));


	}

	@Bean
	public MongoClient mongoClient() {
		return MongoClients.create("mongodb://localhost:27017");
	}

	@Override
	protected String getDatabaseName() {
		return "cameradataaggregation";
	}

}