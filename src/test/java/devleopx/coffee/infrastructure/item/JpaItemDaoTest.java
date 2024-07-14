package devleopx.coffee.infrastructure.item;


import devleopx.coffee.item.Item;
import devleopx.coffee.item.ItemType;
import devleopx.coffee.item.port.ItemDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static devleopx.coffee.item.ItemType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@ActiveProfiles("test")
@Transactional
@Slf4j
class JpaItemDaoTest {

    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private ItemJpaRepository itemJpaRepository;

    private ItemDao itemDao;

    private ItemEntity americano;
    private ItemEntity latte;
    private ItemEntity lemonade;
    @BeforeEach
    void init(){
        itemDao = new JpaItemDao(itemJpaRepository);
        americano = itemJpaRepository.save(ItemEntity.builder().itemType(ESPRESSO_DRINKS).itemName("americano").price(3_000).build());
        latte = itemJpaRepository.save(ItemEntity.builder().itemType(ESPRESSO_DRINKS).itemName("latte").price(3_500).build());
        lemonade = itemJpaRepository.save(ItemEntity.builder().itemType(NON_COFFEE).itemName("lemonade").price(3_500).build());
        entityManager.flush();
    }

    @Test
    @DisplayName("테이블에 없는 ID가 없는 경우 `IllegalArgumentException` 발생한다.")
    void selectByIdNoId(){
        // given
        // when
        // then
        assertThatThrownBy(() -> itemDao.selectById(100L))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("id로 item 을 조회할 수 있다.")
    void selectById(){
        // given
        Long itemId = americano.getId();

        // when
        Item item = itemDao.selectById(itemId);

        // then
        assertThat(item)
                .extracting("itemId", "itemName", "itemType", "price")
                .containsExactly(americano.getId(), americano.getItemName(), americano.getItemType(), americano.getPrice());
    }

    @Test
    @DisplayName("itemType 과 itemName 으로 구분에서 조회할 수 있다.")
    void selectItems() {
        // given
        String itemName = americano.getItemName();
        ItemType itemType = americano.getItemType();

        // when
        List<Item> items = itemDao.selectItems(itemType, itemName);

        // then
        assertThat(items).hasSize(1)
                .first()
                .extracting("itemId", "itemName", "itemType", "price")
                .containsExactly(americano.getId(), americano.getItemName(), americano.getItemType(), americano.getPrice());
    }

    @Test
    @DisplayName("`insert` 을 할 수 있다.")
    void insert() {
        // given
        Item macchiato = Item.builder()
                .itemType(ESPRESSO_DRINKS)
                .itemName("macchiato")
                .price(5_000)
                .build();

        // when
        Long itemId = itemDao.insert(macchiato);

        // then
        assertThat(itemId).isGreaterThan(0L);
        assertThat(itemDao.selectById(itemId))
                .extracting("itemId", "itemName", "itemType", "price")
                .containsExactly(itemId, macchiato.getItemName(), macchiato.getItemType(), macchiato.getPrice());
    }

    @Test
    @DisplayName("동일한 itemName 으로 등록시 오류가 발생한다.")
    void insertOrThrows() {
        // given
        Item americano2 = Item.builder()
                .itemType(ESPRESSO_DRINKS)
                .itemName(americano.getItemName())
                .price(5_000)
                .build();

        // when & then
        itemDao.insert(americano2);
        assertThatThrownBy(() -> entityManager.flush()).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("update 는 정상적으로 동작한다.")
    void update() {
        // given
        Long itemId = americano.getId();
        Item updateItem = Item.builder()
                .itemType(DRIP_COFFEE)
                .itemName("americano")
                .price(10_000)
                .build();

        // when
        itemDao.update(itemId, updateItem);
        Item findItem = itemDao.selectById(itemId);

        // then
        assertThat(findItem)
                .extracting("itemId", "itemName", "itemType", "price")
                .containsExactly(itemId, findItem.getItemName(), findItem.getItemType(), findItem.getPrice());
    }

    @Test
    @DisplayName("itemId 가 없는 경우 `IllegalArgumentException` 가 발생한다.")
    void updateByThrows() {
        // given
        Item updateItem = Item.builder()
                .itemType(DRIP_COFFEE)
                .itemName("americano")
                .price(10_000)
                .build();

        // when
        // then
        assertThatThrownBy(() -> itemDao.update(-1L, updateItem)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("itemid 로 item을 제거할 수 있다.")
    void delete() {
        // given
        Long itemId = lemonade.getId();

        // when
        itemDao.delete(itemId);

        // then
        List<Item> items = itemDao.selectItems(lemonade.getItemType(), lemonade.getItemName());
        assertThat(items).hasSize(0);
    }

}