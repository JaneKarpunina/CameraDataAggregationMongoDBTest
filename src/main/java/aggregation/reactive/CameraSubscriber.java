package aggregation.reactive;

import aggregation.domain.Camera;
import aggregation.domain.CameraDataAggregated;
import aggregation.domain.CameraSource;
import aggregation.domain.TokenData;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class CameraSubscriber implements Subscriber<Camera> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CameraSubscriber.class);
    Subscription subscription;

    WebClient client = WebClient.builder()
            .baseUrl("http://localhost:8080")
            .defaultCookie("cookieKey", "cookieValue")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Camera camera) {
        LOGGER.info("Camera data  : {}", camera);

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

        cameraDataAggregatedMono.subscribe(new CameraDataAggregatedSubscriber());
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
