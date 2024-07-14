package devleopx.coffee.order;

public enum DiscountPolicy {

    BASIC{
        @Override
        public int discount(int totalAmount) {
            return totalAmount > 10_000 ? totalAmount - 1_000 : totalAmount;
        }
    },
    VIP{
        @Override
        public int discount(int totalAmount) {

            int discount = totalAmount - (totalAmount / 10);
            return discount - (discount % 100) ;
        }
    };

    public static DiscountPolicy defaultedPolicy(){
        return BASIC;
    }

    public abstract int discount(int totalAmount);
}
