package aggregation.client.repository;

import aggregation.domain.CameraDataAggregated;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CameraDataAggregatedRepoTest {

    static Map<Integer, CameraDataAggregated> cameraDataAggregatedMap = new HashMap<>();

    {
        //cameraDataAggregatedMap.put()
    }

    public void save(CameraDataAggregated cameraDataAggregated) {

        if (!cameraDataAggregatedMap.containsKey(cameraDataAggregated.getId())) {
            cameraDataAggregatedMap.put(cameraDataAggregated.getId(), cameraDataAggregated);
        }
    }

    public Flux<CameraDataAggregated> findAll() {
        return Flux.fromIterable(cameraDataAggregatedMap.values());
    }

    public Mono<CameraDataAggregated> findById(Integer id) {
        return Mono.just(cameraDataAggregatedMap.get(id));
    }
}
