package devleopx.coffee.item.service;

import devleopx.coffee.item.Item;
import devleopx.coffee.item.ItemType;

import java.util.List;


public interface ItemService {

    Item find(Long itemId);

    List<Item> findItems(ItemType itemType, String itemName);

    Long create(Item item);

    Long modify(Long itemId, Item item);

    void remove(Long itemId);
}
