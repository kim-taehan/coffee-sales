package devleopx.coffee.api.item.request;

import devleopx.coffee.item.Item;
import devleopx.coffee.item.ItemType;
import lombok.Builder;

@Builder
public record ItemCreateRequest(ItemType itemType, String itemName, int price) {

    public Item convertItem() {
        return Item.builder()
                .itemId(-1L)
                .itemType(itemType)
                .itemName(itemName)
                .price(price)
                .build();
    }
}
