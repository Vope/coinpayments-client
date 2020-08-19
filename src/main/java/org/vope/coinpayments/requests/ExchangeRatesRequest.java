package org.vope.coinpayments.requests;

import lombok.Builder;
import org.vope.coinpayments.CoinpaymentsRequest;

public class ExchangeRatesRequest implements CoinpaymentsRequest {

    private final short returnShortResponse;
    private final short accepted;

    public ExchangeRatesRequest() {
        this.returnShortResponse = 0;
        this.accepted = 1;
    }

    @Builder
    public ExchangeRatesRequest(short returnShortResponse, short accepted) {
        this.returnShortResponse = returnShortResponse;
        this.accepted = accepted;
    }

    @Override
    public String getPayload() {
        return "cmd=rates" + "&short=" + returnShortResponse + "&accepted=" + accepted;
    }
}
