package devleopx.coffee.order;

import devleopx.coffee.item.Item;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class Order {

    private final Long orderId;

    private final Long orderNo;

    private final OrderStatus status;

    private Set<Item> items;

    private Integer orderAmount;
}
