package devleopx.coffee.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;


class DiscountPolicyTest {

    @ParameterizedTest
    @CsvSource({"9_000, 9_000", "9_900, 9_900", "10_000, 10_000", "11_000, 10_000", "12_000, 11_000"})
    @DisplayName("일반고객은 10_000 초과 구매시 1_000 이 할인된다.")
    void discountBasic(int totalPrice, int expectedDiscountPrice) {

        // given
        DiscountPolicy basic = DiscountPolicy.BASIC;

        // when
        int discount = basic.discount(totalPrice);

        // then
        assertThat(discount).isEqualTo(expectedDiscountPrice);
    }

    @ParameterizedTest
    @CsvSource({"9_000, 8_100", "9_900, 8_900", "10_000, 9_000", "11_500, 10_300", "12_500, 11_200"})
    @DisplayName("Vip 고객은 무조건 10% 가 할인된다. (100원 이하 버림)")
    void discountVip(int totalPrice, int expectedDiscountPrice) {

        // given
        DiscountPolicy basic = DiscountPolicy.VIP;

        // when
        int discount = basic.discount(totalPrice);

        // then
        assertThat(discount).isEqualTo(expectedDiscountPrice);
    }


}