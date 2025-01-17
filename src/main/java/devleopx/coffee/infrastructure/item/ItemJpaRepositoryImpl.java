package devleopx.coffee.infrastructure.item;

import devleopx.coffee.infrastructure.Querydsl4RepositorySupport;
import devleopx.coffee.item.ItemType;

import java.util.List;

import static devleopx.coffee.infrastructure.item.QItemEntity.itemEntity;


public class ItemJpaRepositoryImpl extends Querydsl4RepositorySupport implements ItemQueryDslRepository {

    public ItemJpaRepositoryImpl() {
        super(ItemEntity.class);
    }

    @Override
    public List<ItemEntity> findItems(ItemType itemType, String itemName) {
        return selectFrom(itemEntity)
                .where(
                        itemEntity.itemType.eq(itemType)
                        , itemEntity.itemName.startsWith(itemName)
                ).fetch();

    }
}
