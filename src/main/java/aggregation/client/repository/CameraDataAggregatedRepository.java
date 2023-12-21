package aggregation.client.repository;

import aggregation.domain.CameraDataAggregated;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CameraDataAggregatedRepository extends ReactiveCrudRepository<CameraDataAggregated, Integer> {
}
