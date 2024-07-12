package devleopx.coffee.api.item.request;

import devleopx.coffee.item.Item;
import devleopx.coffee.item.ItemType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemCreateRequestTest {

    @Test
    @DisplayName("`ItemCreateRequest` 을 `Item` 데이터로 변환되면 ID는 -1이된다.")
    void itemCreateRequestToItem(){
        // given
        ItemCreateRequest americano = ItemCreateRequest.builder()
                .itemType(ItemType.ESPRESSO_DRINKS)
                .itemName("americano")
                .price(3_000)
                .build();

        // when
        Item item = americano.convertItem();

        // then
        assertThat(item)
                .extracting("itemId", "itemName", "itemType", "price")
                .containsExactly(-1L, americano.itemName(), americano.itemType(), americano.price());
    }


}