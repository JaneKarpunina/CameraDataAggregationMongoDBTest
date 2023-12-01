package aggregation.reactive;

import aggregation.domain.Camera;
import aggregation.domain.CameraDataAggregated;
import aggregation.domain.CameraSource;
import aggregation.domain.TokenData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class CameraProcessor extends SubmissionPublisher<CameraDataAggregated>
        implements Flow.Processor<Camera, CameraDataAggregated> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CameraProcessor.class);


    WebClient client = WebClient.builder()
            .baseUrl("http://localhost:8080")
            .defaultCookie("cookieKey", "cookieValue")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Camera camera) {

        LOGGER.info("Cameras get: {}", camera);


        Mono<CameraSource> cameraSource = client.get()
                .uri(camera.getSourceDataUrl())
                .retrieve()
                .bodyToMono(CameraSource.class);

        Mono<TokenData> tokenData = client.get()
                .uri(camera.getTokenDataUrl())
                .retrieve()
                .bodyToMono(TokenData.class);

        Mono<CameraDataAggregated> cameraDataAggregatedMono =
                Mono.zip(cameraSource, tokenData, CameraDataAggregated::new);

        cameraDataAggregatedMono.subscribe(this::submit);

        subscription.request(1);

        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onComplete() {

        }
    }
