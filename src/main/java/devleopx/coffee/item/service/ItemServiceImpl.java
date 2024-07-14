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

    @Override
    public Item find(Long itemId) {
        return itemDao.selectById(itemId);
    }

    @Override
    public List<Item> findItems(ItemType itemType, String itemName) {
        return itemDao.selectItems(itemType, itemName);
    }

    @Override
    public Long create(Item item) {
        return itemDao.insert(item);
    }


}
