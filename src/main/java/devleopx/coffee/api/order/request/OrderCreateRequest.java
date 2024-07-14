package devleopx.coffee.api.order.request;

import devleopx.coffee.order.DiscountPolicy;
import lombok.Builder;

import java.util.List;

@Builder
public record OrderCreateRequest(
        List<Long> itemIds,
        DiscountPolicy discountPolicy
) {
}
