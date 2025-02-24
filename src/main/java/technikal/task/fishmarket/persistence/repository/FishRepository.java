package technikal.task.fishmarket.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import technikal.task.fishmarket.persistence.entity.Fish;

@Repository
public interface FishRepository extends JpaRepository<Fish, Integer> {

}
