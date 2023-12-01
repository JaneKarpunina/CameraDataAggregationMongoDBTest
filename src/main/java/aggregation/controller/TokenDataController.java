package aggregation.controller;

import aggregation.domain.CameraSource;
import aggregation.domain.TokenData;
import aggregation.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tokendata")
public class TokenDataController {

    @Autowired
    private CameraService cameraService;


    public TokenDataController() {
    }

    @GetMapping
    public Mono<TokenData> listCameraSources(@RequestParam(name = "id", required = true) Integer id) {
        return cameraService.findTokenDataById(id);
    }
}
