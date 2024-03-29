package aggregation.client.service;

import aggregation.client.repository.CameraDataAggregatedRepoTest;
import aggregation.client.repository.CameraDataAggregatedRepository;
import aggregation.domain.CameraDataAggregated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CameraDataAggregatedOperations {


    private final CameraDataAggregatedRepository cameraDataAggregatedRepository;

    @Autowired
    public CameraDataAggregatedOperations(CameraDataAggregatedRepository cameraDataAggregatedRepository) {
        this.cameraDataAggregatedRepository = cameraDataAggregatedRepository;
    }

    public Mono<CameraDataAggregated> findById(Integer id) {
        return cameraDataAggregatedRepository.findById(id);
    }

    public Flux<CameraDataAggregated> findAll() {
        return cameraDataAggregatedRepository.findAll();
    }
    public Mono<CameraDataAggregated> save(CameraDataAggregated cameraDataAggregated) {
        return cameraDataAggregatedRepository.save(cameraDataAggregated);
    }
}
