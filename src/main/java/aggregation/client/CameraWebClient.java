package aggregation.client;

import aggregation.domain.Camera;
import aggregation.domain.CameraDataAggregated;
import aggregation.domain.CameraSource;
import aggregation.domain.TokenData;
import aggregation.reactive.CameraDataAggregatedSubscriber;
import aggregation.reactive.CameraProcessor;
import aggregation.reactive.CameraSubscriber;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.Collections;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Consumer;


public class CameraWebClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CameraWebClient.class);

    Scheduler cameraScheduler = Schedulers.newParallel("Camera scheduler");

    WebClient client = WebClient.builder()
            .baseUrl("http://localhost:8080")
            .defaultCookie("cookieKey", "cookieValue")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            //.defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
            .build();

    public void consume() {

        Flux<Camera> cameraFlux = client.get()
                .uri("/cameras")
                .retrieve()
                .bodyToFlux(Camera.class);


        cameraFlux.subscribeOn(cameraScheduler).subscribe(new CameraSubscriber());


     }
}