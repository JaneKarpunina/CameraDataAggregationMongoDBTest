package aggregation.controller;

import aggregation.domain.Camera;
import aggregation.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cameras")
public class CamerasController {

    @Autowired
    private CameraService cameraService;


    public CamerasController() {
    }

    @GetMapping
    public Flux<Camera> listStudents(@RequestParam(name = "id", required = false) Integer id) {

        return cameraService.findCamerasById(id);
    }

    @PostMapping
    public Mono<Camera> addCamera(@RequestBody Camera camera) {
        return cameraService.addCamera(camera);
    }

   /* @PutMapping("/{id}")
    public Mono<ResponseEntity<Student>> updateStudent(@PathVariable long id, @RequestBody Student student) {
        return cameraService.updateStudent(id, student)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteStudent(@PathVariable long id) {
        return cameraService.findStudentById(id)
                .flatMap(s ->
                        cameraService.deleteStudent(s)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }*/
}
