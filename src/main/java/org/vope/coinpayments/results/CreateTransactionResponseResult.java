package org.vope.coinpayments.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateTransactionResponseResult {

    private BigDecimal amount;

    private String address;

    @JsonProperty("dest_tag")
    private String destinationTag;

    @JsonProperty("txn_id")
    private String transactionId;

    @JsonProperty("confirms_needed")
    private String confirmationsNeeded;

    private long timeout;

    @JsonProperty("checkout_url")
    private String checkoutUrl;

    @JsonProperty("status_url")
    private String statusUrl;

    @JsonProperty("qrcode_url")
    private String QRCodeUrl;
}
