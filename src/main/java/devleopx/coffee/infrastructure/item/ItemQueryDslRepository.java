package devleopx.coffee.infrastructure.item;

import devleopx.coffee.infrastructure.item.ItemEntity;
import devleopx.coffee.item.ItemType;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemQueryDslRepository {

    List<ItemEntity> findItems(ItemType itemType, String itemName);
}
