package devleopx.coffee.item;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Item {

    private final Long itemId;

    private final String itemName;

    private final ItemType itemType;

    private final Integer price;

    private final String description;

    public Item createItem(Long itemId) {
        return Item.builder()
                .itemId(itemId)
                .itemName(itemName)
                .itemType(itemType)
                .price(price)
                .description(description)
                .build();
    }
}
