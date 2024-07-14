package devleopx.coffee.item;

import devleopx.coffee.item.port.ItemDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

public class FakeItemDao implements ItemDao {

    Map<Long, Item> itemMap = new HashMap<>();

    AtomicLong sequence = new AtomicLong(1);

    @Override
    public Item selectById(Long itemId) {
        return itemMap.get(itemId);
    }

    @Override
    public List<Item> selectItems(ItemType itemType, String itemName) {
        return itemMap.values().stream()
                .filter(checkItemCondition(itemType, itemName))
                .toList();
    }

    private Predicate<Item> checkItemCondition(ItemType itemType, String itemName) {
        return item -> item.getItemName().contains(itemName) && (Objects.isNull(itemType) || item.getItemType() == itemType);
    }

    @Override
    public Long insert(Item item) {
        long itemId = sequence.getAndIncrement();
        itemMap.put(itemId, item.createItem(itemId));
        return itemId;
    }

    @Override
    public void update(Long itemId, Item item) {
        itemMap.put(itemId, item);
    }

    @Override
    public void delete(Long itemId) {
        itemMap.remove(itemId);
    }
}
