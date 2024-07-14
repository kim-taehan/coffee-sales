package devleopx.coffee.item.service;

import devleopx.coffee.item.FakeItemDao;
import devleopx.coffee.item.Item;
import devleopx.coffee.item.ItemType;
import devleopx.coffee.item.port.ItemDao;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemServiceImplTest {

    private ItemDao itemDao;
    private ItemService itemService;

    @BeforeEach
    void init() {
        itemDao = new FakeItemDao();
        itemService = new ItemServiceImpl(itemDao);
    }

    @Test
    @DisplayName("itemId 로 특정 item을 조회할 수 있다.")
    void find() {
        // given
        Item americano = createItem(ItemType.ESPRESSO_DRINKS, "americano", 3_000);
        createItem(ItemType.ESPRESSO_DRINKS, "latte", 3_500);
        Long americanoId = itemDao.insert(americano);

        // when
        Item item = itemService.find(americanoId);

        // then
        assertThat(item).extracting("itemId", "itemName", "itemType", "price")
                .contains(americanoId, americano.getItemName(), americano.getItemType(), americano.getPrice());

    }

    @Test
    @DisplayName("itemId 로 특정 조건으로 필터링해서 조회할 수 있다.")
    void findItems() {
        // given
        Item americano = createItem(ItemType.ESPRESSO_DRINKS, "americano", 3_000);
        Item latte = createItem(ItemType.ESPRESSO_DRINKS, "latte", 3_500);
        Item lemonade = createItem(ItemType.NON_COFFEE, "lemonade", 4_500);
        Long americanoId = itemDao.insert(americano);
        Long latteId = itemDao.insert(latte);
        itemDao.insert(lemonade);

        // when
        List<Item> items = itemService.findItems(ItemType.ESPRESSO_DRINKS, "");

        // then
        assertThat(items).hasSize(2)
                .extracting("itemId", "itemName", "itemType", "price")
                .contains(Tuple.tuple(americanoId, americano.getItemName(), americano.getItemType(), americano.getPrice()),
                        Tuple.tuple(latteId, latte.getItemName(), latte.getItemType(), latte.getPrice()));

    }

    private Item createItem(ItemType itemType, String itemName, int price) {
        return Item.builder()
                .itemType(itemType)
                .itemName(itemName)
                .price(price)
                .build();
    }

    @Test
    @DisplayName("신규 item을 저장할 수 있다.")
    void create() {
        // given
        Item americano = createItem(ItemType.ESPRESSO_DRINKS, "americano", 3_000);

        // when
        Long itemId = itemService.create(americano);

        // then
        Item item = itemDao.selectById(itemId);
        assertThat(item).extracting("itemId", "itemName", "itemType", "price")
                .contains(itemId, americano.getItemName(), americano.getItemType(), americano.getPrice());

    }


}