package devleopx.coffee.infrastructure.item;

import devleopx.coffee.infrastructure.item.ItemEntity;
import devleopx.coffee.item.Item;
import devleopx.coffee.item.ItemType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class ItemEntityTest {

    @Test
    @DisplayName("`ItemEntity`를 `Item` 형태로 변경할 수 있다.")
    void convertItem() {
        // given
        ItemEntity itemEntity = ItemEntity.builder()
                .id(100L)
                .itemName(UUID.randomUUID().toString().substring(10))
                .itemType(ItemType.ESPRESSO_DRINKS)
                .price(2_000)
                .build();

        // when
        Item item = itemEntity.convertItem();

        // then
        assertThat(item)
                .extracting("itemId", "itemName", "itemType", "price")
                .containsExactly(itemEntity.getId(), itemEntity.getItemName(), itemEntity.getItemType(), itemEntity.getPrice());

    }

    @Test
    @DisplayName("`Item`를 `ItemEntity` 형태로 변경할 수 있다.")
    void of() {
        // given
        Item item = Item.builder()
                .itemId(100L)
                .itemName(UUID.randomUUID().toString().substring(10))
                .itemType(ItemType.ESPRESSO_DRINKS)
                .price(2_000)
                .build();

        // when
        ItemEntity itemEntity = ItemEntity.of(item);

        // then
        assertThat(itemEntity)
                .extracting("itemName", "itemType", "price")
                .containsExactly( item.getItemName(), item.getItemType(), item.getPrice());

    }

    @Test
    @DisplayName("itemName, itemType, price 를 변경할 수 있다.")
    void update() {

        // given
        ItemEntity itemEntity = ItemEntity.builder()
                .id(100L)
                .itemName(UUID.randomUUID().toString().substring(10))
                .itemType(ItemType.ESPRESSO_DRINKS)
                .price(2_000)
                .build();
        Item item = Item.builder()
                .itemId(100L)
                .itemName(UUID.randomUUID().toString().substring(10))
                .itemType(ItemType.NON_COFFEE)
                .price(3_000)
                .build();

        // when
        itemEntity.update(item);

        // then
        assertThat(itemEntity)
                .extracting("itemName", "itemType", "price")
                .containsExactly(item.getItemName(), item.getItemType(), item.getPrice());


    }
}