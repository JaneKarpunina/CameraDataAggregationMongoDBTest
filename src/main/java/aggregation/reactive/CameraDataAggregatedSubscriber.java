package aggregation.reactive;

import aggregation.client.service.CameraDataAggregatedOperations;
import aggregation.domain.CameraDataAggregated;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class CameraDataAggregatedSubscriber implements Subscriber<CameraDataAggregated> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CameraDataAggregatedSubscriber.class);
    Subscription subscription;

    private final CameraDataAggregatedOperations cameraDataAggregatedOperations;


    public CameraDataAggregatedSubscriber(CameraDataAggregatedOperations cameraDataAggregatedOperations) {
        this.cameraDataAggregatedOperations = cameraDataAggregatedOperations;
    }


    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(CameraDataAggregated element) {

        LOGGER.info("Camera data aggregated : {}", element);

        element.setId(element.getCameraSourceId());
        Mono<CameraDataAggregated> cameraDataAggregatedMono = cameraDataAggregatedOperations.save(element);

        cameraDataAggregatedMono.subscribe(e -> LOGGER.info("Camera data aggregated, save returned: {}", e));
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {
        LOGGER.info("Camera data aggregated completed");
    }
}
