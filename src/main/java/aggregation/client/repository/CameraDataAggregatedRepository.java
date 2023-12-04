package aggregation.client.repository;

import aggregation.domain.CameraDataAggregated;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CameraDataAggregatedRepository extends ReactiveCrudRepository<CameraDataAggregated, String> {
    //Flux<CameraDataAggregated> findAll();

    //Mono<CameraDataAggregated> findById(Integer id, Class<CameraDataAggregated> cameraDataAggregatedClass);
}
