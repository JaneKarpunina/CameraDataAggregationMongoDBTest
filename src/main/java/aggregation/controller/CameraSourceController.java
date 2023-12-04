package aggregation.controller;

import aggregation.domain.Camera;
import aggregation.domain.CameraSource;
import aggregation.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sources")
public class CameraSourceController {

    @Autowired
    private CameraService cameraService;


    public CameraSourceController() {
    }

    @GetMapping
    public Mono<CameraSource> listCameraSources(@RequestParam(name = "id", required = false) Integer id) {
        return cameraService.findSourceById(id);
    }

   /* @PostMapping
    public Mono<Camera> addCameraSources(@RequestBody Camera camera) {
        return cameraService.addCamera(camera);
    }*/

}
