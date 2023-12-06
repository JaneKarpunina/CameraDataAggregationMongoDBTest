package aggregation.client.controller;


import aggregation.client.CameraWebClient;
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

    @Autowired
    private CameraDataAggregatedOperations cameraDataAggregatedOperations;

    public CameraDataAggregatedController() {
    }


    @GetMapping
    //@ResponseBody
    public Flux<CameraDataAggregated> findCameraDataAggregated() {
        LOGGER.info("in camera aggregated controller");
        cameraDataAggregatedOperations.findAll().subscribe(e -> LOGGER.info("in camera aggregated controller {}", e));
        return cameraDataAggregatedOperations.findAll();
    }


}
