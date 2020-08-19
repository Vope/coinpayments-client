package org.vope.coinpayments.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BasicAccountInfoResponseResult {

    private String username;

    @JsonProperty("merchant_id")
    private String merchantId;

    private String email;

    @JsonProperty("public_name")
    private String publicName;
}
