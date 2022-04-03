package ac.cnu.realcoding.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLRepository extends ReactiveCrudRepository<URLInformation, Long> {
}
