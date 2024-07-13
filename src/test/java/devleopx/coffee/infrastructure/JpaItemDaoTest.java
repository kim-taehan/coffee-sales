package devleopx.coffee.infrastructure;

import devleopx.coffee.item.Item;
import devleopx.coffee.item.ItemType;
import devleopx.coffee.item.port.ItemDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@Transactional
class JpaItemDaoTest {

    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private ItemJpaRepository itemJpaRepository;

    private ItemDao itemDao;

    private ItemEntity americano;
    private ItemEntity latte;
    @BeforeEach
    void init(){
        itemDao = new JpaItemDao(itemJpaRepository);
        americano = itemJpaRepository.save(ItemEntity.builder().itemType(ItemType.ESPRESSO_DRINKS).itemName("americano").price(3_000).build());
        latte = itemJpaRepository.save(ItemEntity.builder().itemType(ItemType.ESPRESSO_DRINKS).itemName("latte").price(3_500).build());
        entityManager.flush();
    }

    @Test
    @DisplayName("테이블에 없는 ID가 없는 경우 `IllegalArgumentException` 발생한다.")
    void selectByIdNoId(){
        // given
        // when
        // then
        assertThatThrownBy(() -> itemDao.selectById(latte.getId() + 1))
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

}