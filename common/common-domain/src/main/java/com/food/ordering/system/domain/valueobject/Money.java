package com.food.ordering.system.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isGreaterThanZero() {
        return isNotNull() && this.amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isGreaterThan(Money money) {
        return isNotNull() && this.amount.compareTo(money.getAmount()) > 0;
    }

    public Money add(Money money) {
        return new Money(setScale(this.amount.add(money.getAmount())));
    }
    public Money subtract(Money money){
        return new Money(setScale(this.amount.subtract(money.getAmount())));
    }
    public Money multiply(int multiplier){
        return new Money(setScale(this.amount.multiply(BigDecimal.valueOf(multiplier))));

    }



    private BigDecimal setScale(BigDecimal input) {// it will return bkz 1.4 -> 1 , 1.5 -> 2 , 1.6 -> 2 1.11111111 -> 1.11
        return input.setScale(2, RoundingMode.HALF_EVEN);//kümülatif olarak 1.11111111 -> 1.11
    }

    private boolean isNotNull() {
        return this.amount != null;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
