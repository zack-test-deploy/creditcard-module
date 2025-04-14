package com.hotsauce.payments.creditcard.core.voidsale;

public record Request(
        int amount,
        int tax,
        int tip
) {

}
