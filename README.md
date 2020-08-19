### Coinpayments Client
Coinpayments API Client for Java

```java
final CoinpaymentsProperties properties = new CoinpaymentsProperties("publicKey", "privateKey");
final CoinpaymentsClient client = new CoinpaymentsClient(properties);
final CreateTransactionRequest request = new CreateTransactionRequest("3.14", "USD", "BTC");
try {
    final CreateTransactionResponseResult result = client.createTransaction(request);
} catch(CoinpaymentsException ex) {}
``` 