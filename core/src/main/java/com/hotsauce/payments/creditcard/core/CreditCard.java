package com.hotsauce.payments.creditcard.core;

public interface CreditCard {
    com.hotsauce.payments.creditcard.core.sale.Response sale(com.hotsauce.payments.creditcard.core.sale.Request request);
    com.hotsauce.payments.creditcard.core.voidsale.Response voidSale(com.hotsauce.payments.creditcard.core.voidsale.Response response);
}
