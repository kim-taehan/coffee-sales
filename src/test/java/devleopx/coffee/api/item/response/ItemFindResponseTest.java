package devleopx.coffee.api.item.response;

import devleopx.coffee.item.Item;
import devleopx.coffee.item.ItemType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemFindResponseTest {


    @DisplayName("")
    @Test
    void itemToItemFindResponse(){
        // given
        Item americano = Item.builder()
                .itemId(100L)
                .itemName("americano")
                .itemType(ItemType.ESPRESSO_DRINKS)
                .price(3_000)
                .build();

        // when
        ItemFindResponse itemFindResponse = ItemFindResponse.of(americano);

        // then
        assertThat(itemFindResponse)
                .extracting("itemId", "itemName", "itemType", "price")
                .containsExactly(americano.getItemId(), americano.getItemName(), americano.getItemType(), americano.getPrice());
    }


}