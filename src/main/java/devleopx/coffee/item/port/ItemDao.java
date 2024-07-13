package devleopx.coffee.item.port;

import devleopx.coffee.item.Item;
import devleopx.coffee.item.ItemType;

import java.util.List;

public interface ItemDao {

    Item selectById(Long itemId);

    List<Item> selectItems(ItemType itemType, String itemName);

    Long insert(Item item);

    void update(Long itemId, Item item);

    void delete(Long itemId);
}
