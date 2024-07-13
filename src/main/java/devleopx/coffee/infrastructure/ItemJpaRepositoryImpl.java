package devleopx.coffee.infrastructure;

import com.querydsl.jpa.impl.JPAQuery;
import devleopx.coffee.item.Item;
import devleopx.coffee.item.ItemType;

import java.util.List;

import static devleopx.coffee.infrastructure.QItemEntity.*;

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
