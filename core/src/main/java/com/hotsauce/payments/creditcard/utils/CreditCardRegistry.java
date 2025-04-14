package com.hotsauce.payments.creditcard.utils;

import com.hotsauce.payments.creditcard.annotations.CreditCardImpl;
import com.hotsauce.payments.creditcard.core.CreditCard;

import java.util.ServiceLoader;

public class CreditCardRegistry {
    public static CreditCard load(String type) {
        ServiceLoader<CreditCard> loader = ServiceLoader.load(CreditCard.class);
        for (CreditCard impl : loader) {
            CreditCardImpl annotation = impl.getClass().getAnnotation(CreditCardImpl.class);
            if (annotation != null && annotation.value().equalsIgnoreCase(type)) {
                return impl;
            }
        }
        throw new RuntimeException("No CreditCard implementation found for type: " + type);
    }
}
