package devleopx.coffee.item.service;

import devleopx.coffee.item.Item;
import devleopx.coffee.item.ItemType;
import devleopx.coffee.item.port.ItemDao;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;

    @PostConstruct
    void init() {
        itemDao.insert(
                Item.builder()
                        .itemType(ItemType.ESPRESSO_DRINKS)
                        .itemName("americano")
                        .price(3_000)
                        .build()
        );
        itemDao.insert(
                Item.builder()
                        .itemType(ItemType.ESPRESSO_DRINKS)
                        .itemName("latte")
                        .price(4_000)
                        .build()
        );
        itemDao.insert(
                Item.builder()
                        .itemType(ItemType.ESPRESSO_DRINKS)
                        .itemName("cappuccino")
                        .price(4_500)
                        .build()
        );
        itemDao.insert(
                Item.builder()
                        .itemType(ItemType.NON_COFFEE)
                        .itemName("remonade")
                        .price(4_500)
                        .build()
        );
    }

    @Override
    public Item find(Long itemId) {
        return null;
    }

    @Override
    public List<Item> findItems(ItemType itemType, String itemName) {
        return itemDao.select(itemType, itemName);
    }

    @Override
    public Long create(Item item) {
        return itemDao.insert(item);
    }

    @Override
    public Long modify(Long itemId, Item item) {
        return null;
    }

    @Override
    public void remove(Long itemId) {

    }
}
