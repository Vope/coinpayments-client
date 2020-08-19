package org.vope.coinpayments;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GenericResponseBody<T> {
    private String error;
    private T result;
}
