package org.vope.coinpayments;

import lombok.Builder;
import lombok.Getter;

public class CoinpaymentsProperties {
    @Getter
    private final String publicKey;
    @Getter
    private final String privateKey;

    @Builder
    public CoinpaymentsProperties(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }
}
