package devleopx.coffee.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemJpaRepository extends JpaRepository<ItemEntity, Long>, ItemQueryDslRepository {
}
