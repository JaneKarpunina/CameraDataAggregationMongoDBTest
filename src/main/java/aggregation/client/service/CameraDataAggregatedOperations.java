package aggregation.client.service;

import aggregation.client.repository.CameraDataAggregatedRepoTest;
import aggregation.domain.CameraDataAggregated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CameraDataAggregatedOperations {


    private final CameraDataAggregatedRepoTest cameraDataAggregatedRepoTest;

    @Autowired
    public CameraDataAggregatedOperations(CameraDataAggregatedRepoTest cameraDataAggregatedRepository) {
        this.cameraDataAggregatedRepoTest = cameraDataAggregatedRepository;
    }

    public Mono<CameraDataAggregated> findById(Integer id) {
        return cameraDataAggregatedRepoTest.findById(id);
    }

    public Flux<CameraDataAggregated> findAll() {
        return cameraDataAggregatedRepoTest.findAll();
    }
    public void save(CameraDataAggregated cameraDataAggregated) {
        cameraDataAggregatedRepoTest.save(cameraDataAggregated);
    }
}
