package com.hotsauce.payments.creditcard.core.sale;

public record Request(
        int amount,
        int tax,
        int tip
) {

}
