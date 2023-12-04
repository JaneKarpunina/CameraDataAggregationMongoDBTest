package aggregation.reactive;

import aggregation.client.service.CameraDataAggregatedOperations;
import aggregation.domain.CameraDataAggregated;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        //TODO: inject operations in CameraWebClient and give it to this subscriber;try this too
        cameraDataAggregatedOperations.save(element);
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
