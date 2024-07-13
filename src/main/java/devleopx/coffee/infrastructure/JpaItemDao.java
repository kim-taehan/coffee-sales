package devleopx.coffee.infrastructure;

import devleopx.coffee.item.Item;
import devleopx.coffee.item.ItemType;
import devleopx.coffee.item.port.ItemDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JpaItemDao implements ItemDao {

    private final ItemJpaRepository itemJpaRepository;

    @Override
    public Item selectById(Long itemId) {
        ItemEntity itemEntity = this.findById(itemId);
        return itemEntity.convertItem();
    }

    @Override
    public List<Item> select(ItemType itemType, String itemName) {
        return itemJpaRepository.findAll()
                .stream().map(ItemEntity::convertItem)
                .toList();
    }

    @Override
    public Long insert(Item item) {
        ItemEntity itemEntity = itemJpaRepository.save(ItemEntity.of(item));
        return itemEntity.getId();
    }

    public void update(Long itemId, Item item) {
        ItemEntity itemEntity = this.findById(itemId);
        itemEntity.update(item);
    }

    @Override
    public void delete(Long itemId) {
        ItemEntity itemEntity = this.findById(itemId);
        itemJpaRepository.delete(itemEntity);
    }

    private ItemEntity findById(Long itemId) {
        return itemJpaRepository.findById(itemId)
                .orElseThrow(IllegalArgumentException::new);
    }
}
