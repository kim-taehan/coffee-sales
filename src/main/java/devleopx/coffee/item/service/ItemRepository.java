package devleopx.coffee.item.service;

import devleopx.coffee.item.Item;
import devleopx.coffee.item.ItemType;

import java.util.List;

public interface ItemRepository {

    Item selectById(Long itemId);

    List<Item> select(ItemType itemType, String itemName);

    Long insert(Item item);
}
