package aggregation.client;

import aggregation.client.service.CameraDataAggregatedOperations;
import aggregation.domain.Camera;
import aggregation.reactive.CameraSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;


@Service
public class CameraWebClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CameraWebClient.class);

    private final WebClient client = ClientUtils.webClientWithTimeout();

    //private final ApplicationContext applicationContext;
    private final CameraDataAggregatedOperations cameraDataAggregatedOperations;

    @Autowired
    public CameraWebClient(CameraDataAggregatedOperations cameraDataAggregatedOperations) {

        this.cameraDataAggregatedOperations = cameraDataAggregatedOperations;
    }

    public void consume() {

        Flux<Camera> cameraFlux = client.get()
                .uri("/cameras")
                .retrieve()
                .bodyToFlux(Camera.class);
        Scheduler cameraScheduler = Schedulers.newParallel("Camera scheduler");

        cameraFlux.subscribeOn(cameraScheduler).subscribe(new CameraSubscriber(cameraDataAggregatedOperations));


     }
}