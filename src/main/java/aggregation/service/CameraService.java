package aggregation.service;

import aggregation.domain.Camera;
import aggregation.domain.CameraSource;
import aggregation.domain.TokenData;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class CameraService {

    Map<Integer, Camera> cameraMap = new HashMap<>();
    Map<Integer, CameraSource> sourcesMap = new HashMap<>();
    Map<Integer, TokenData> tokenDataMap = new HashMap<>();

    {
        Camera cam1 = new Camera(1, "http://localhost:8080/sources?id=1",
                "http://localhost:8080/tokendata?id=1");
        Camera cam2 = new Camera(2, "http://localhost:8080/sources?id=2",
                "http://localhost:8080/tokendata?id=2");
        Camera cam3 = new Camera(3, "http://localhost:8080/sources?id=3",
                "http://localhost:8080/tokendata?id=3");
        cameraMap.put(cam1.getId(), cam1);
        cameraMap.put(cam2.getId(), cam2);
        cameraMap.put(cam3.getId(), cam3);

        CameraSource cameraSource1 = new CameraSource(1, "LIVE", "http://localhost:8080/stream1");
        CameraSource cameraSource2 = new CameraSource(2, "ARCHIVE", "http://localhost:8080/stream2");
        CameraSource cameraSource3 = new CameraSource(3, "LIVE", "http://localhost:8080/stream3");
        sourcesMap.put(cameraSource1.getId(), cameraSource1);
        sourcesMap.put(cameraSource2.getId(), cameraSource2);
        sourcesMap.put(cameraSource3.getId(), cameraSource3);

        TokenData tokenData1 = new TokenData(1, "security", "100000");
        TokenData tokenData2 = new TokenData(2, "security2", "30000");
        TokenData tokenData3 = new TokenData(3, "security3", "50000");
        tokenDataMap.put(tokenData1.getId(), tokenData1);
        tokenDataMap.put(tokenData2.getId(), tokenData2);
        tokenDataMap.put(tokenData3.getId(), tokenData3);
    }

    Flux<Camera> getAllCameras() {

        return Flux.fromIterable(cameraMap.values());

    }


    public Flux<Camera> findCamerasById(Integer id) {
        if (id != null) {
            return Flux.just(cameraMap.get(id));
        }
        return Flux.fromIterable(cameraMap.values());

    }

    public Mono<Camera> addCamera(Camera camera) {

        cameraMap.put(camera.getId(), camera);

        return Mono.just(camera);

    }


    public Mono<CameraSource> findSourceById(Integer id) {
      //  if (id != null) {
            return Mono.just(sourcesMap.get(id));
      //  }
      //  return Flux.fromIterable(sourcesMap.values());
    }

    public Mono<TokenData> findTokenDataById(Integer id) {

       // if (id != null) {
            return Mono.just(tokenDataMap.get(id));
       // }
       // return Mono.fromIterable(tokenDataMap.values());

    }
}
