package technikal.task.fishmarket.services.fish;

import org.springframework.data.domain.Sort;
import technikal.task.fishmarket.dto.fish.FishDto;
import technikal.task.fishmarket.persistence.entity.Fish;
import java.util.List;

public interface FishService {

  List<Fish> get(Sort sort);

  Fish create(FishDto fishDto);

  void delete(Integer id);
}
