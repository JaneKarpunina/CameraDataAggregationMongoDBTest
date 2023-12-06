package aggregation.client.controller;


import aggregation.client.service.CameraDataAggregatedOperations;
import aggregation.domain.CameraDataAggregated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/camerasDataAggregated")
public class CameraDataAggregatedController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CameraDataAggregatedController.class);


    private final CameraDataAggregatedOperations cameraDataAggregatedOperations;

    @Autowired
    public CameraDataAggregatedController(CameraDataAggregatedOperations cameraDataAggregatedOperations) {
        this.cameraDataAggregatedOperations = cameraDataAggregatedOperations;
    }
    @GetMapping
    public Flux<CameraDataAggregated> findCameraDataAggregated() {

        Flux<CameraDataAggregated> cameraDataAggregatedFlux = cameraDataAggregatedOperations.findAll();
        cameraDataAggregatedFlux.subscribe(e -> LOGGER.info("in camera aggregated controller {}", e));
        return cameraDataAggregatedFlux;
    }


}
