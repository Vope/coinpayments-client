package org.vope.coinpayments.requests;

import org.vope.coinpayments.CoinpaymentsRequest;

public class BasicAccountInfoRequest implements CoinpaymentsRequest {
    @Override
    public String getPayload() {
        return "cmd=get_basic_info";
    }
}
