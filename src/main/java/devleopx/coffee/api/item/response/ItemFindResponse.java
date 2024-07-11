package devleopx.coffee.api.item.response;

import devleopx.coffee.item.ItemType;
import lombok.Builder;

@Builder
public record ItemFindResponse(Long itemId, String itemName, Integer price, ItemType itemType) {
}
