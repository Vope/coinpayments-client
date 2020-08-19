package org.vope.coinpayments.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ExchangeRateItemResponseResult {

    @JsonProperty("is_fiat")
    private long isFiat;

    @JsonProperty("rate_btc")
    private BigDecimal rateBtc;

    @JsonProperty("last_update")
    private Date lastUpdate;

    @JsonProperty("tx_fee")
    private BigDecimal txFee;

    private String status;

    private String name;

    private long confirms;

    @JsonProperty("can_convert")
    private int canConvert;

    private Set<String> capabilities;

    private String explorer;

    private int accepted;
}
