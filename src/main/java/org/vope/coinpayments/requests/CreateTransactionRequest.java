package org.vope.coinpayments.requests;

import org.vope.coinpayments.CoinpaymentsRequest;

public class CreateTransactionRequest implements CoinpaymentsRequest {

    private final String amount;
    private final String currency1;
    private final String currency2;
    private final String buyerEmail;
    private String address;
    private String buyerName;
    private String itemName;
    private String itemNumber;
    private String invoice;
    private String custom;
    private String ipnUrl;
    private String successUrl;
    private String cancelUrl;

    public CreateTransactionRequest(String amount, String currency1, String currency2, String buyerEmail) {
        this.amount = amount;
        this.currency1 = currency1;
        this.currency2 = currency2;
        this.buyerEmail = buyerEmail;
    }

    public CreateTransactionRequest(String amount, String currency1, String currency2, String buyerEmail,
                                    String address, String buyerName, String itemName, String itemNumber,
                                    String invoice, String custom, String ipnUrl, String successUrl, String cancelUrl) {
        this.amount = amount;
        this.currency1 = currency1;
        this.currency2 = currency2;
        this.buyerEmail = buyerEmail;
        this.address = address;
        this.buyerName = buyerName;
        this.itemName = itemName;
        this.itemNumber = itemNumber;
        this.invoice = invoice;
        this.custom = custom;
        this.ipnUrl = ipnUrl;
        this.successUrl = successUrl;
        this.cancelUrl = cancelUrl;
    }

    @Override
    public String getPayload() {
        final StringBuilder builder = new StringBuilder("cmd=create_transaction");
        builder.append("&amount=").append(amount);
        builder.append("&currency1=").append(currency1);
        builder.append("&currency2=").append(currency2);
        builder.append("&buyer_email=").append(buyerEmail);

        if (address != null) {
            builder.append("&address=").append(address);
        }
        if (buyerName != null) {
            builder.append("&buyer_name=").append(buyerName);
        }
        if (itemName != null) {
            builder.append("&item_name=").append(itemName);
        }
        if (itemNumber != null) {
            builder.append("&item_number=").append(itemNumber);
        }
        if (invoice != null) {
            builder.append("&invoice=").append(invoice);
        }
        if (custom != null) {
            builder.append("&custom=").append(custom);
        }
        if (ipnUrl != null) {
            builder.append("&ipn_url=").append(ipnUrl);
        }
        if (successUrl != null) {
            builder.append("&success_url=").append(successUrl);
        }
        if (cancelUrl != null) {
            builder.append("&cancel_url=").append(cancelUrl);
        }
        return builder.toString();
    }
}
