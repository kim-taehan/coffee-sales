package devleopx.coffee.api.item.response;

import devleopx.coffee.item.Item;
import devleopx.coffee.item.ItemType;
import lombok.Builder;

@Builder
public record ItemFindResponse(Long itemId, String itemName, Integer price, ItemType itemType) {
    public static ItemFindResponse of(Item item) {

        return ItemFindResponse.builder()
                .itemId(item.getItemId())
                .itemName(item.getItemName())
                .price(item.getPrice())
                .itemType(item.getItemType())
                .build();
    }
}
