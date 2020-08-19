package org.vope.coinpayments;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.vope.coinpayments.requests.BasicAccountInfoRequest;
import org.vope.coinpayments.requests.CreateTransactionRequest;
import org.vope.coinpayments.requests.ExchangeRatesRequest;
import org.vope.coinpayments.results.BasicAccountInfoResponseResult;
import org.vope.coinpayments.results.CreateTransactionResponseResult;
import org.vope.coinpayments.results.ExchangeRateItemResponseResult;

import java.io.IOException;
import java.util.Map;

public class CoinpaymentsClient {

    private static final String API_URL = "https://www.coinpayments.net/api.php";

    private final CoinpaymentsProperties properties;
    private final HmacUtils hmacUtils;
    private final CloseableHttpClient client;
    private final ObjectMapper mapper;

    public CoinpaymentsClient(CoinpaymentsProperties properties) {
        this.properties = properties;
        this.hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_512, properties.getPrivateKey());
        this.client = HttpClients.createDefault();
        this.mapper = new ObjectMapper();
    }

    /**
     * https://www.coinpayments.net/apidoc-get-basic-info
     */
    public BasicAccountInfoResponseResult getBasicAccountInfo() throws CoinpaymentsException, IOException {
        final BasicAccountInfoRequest request = new BasicAccountInfoRequest();
        final Object result = execute(request);
        return mapper.convertValue(result, new TypeReference<>() {
        });
    }

    /**
     * https://www.coinpayments.net/apidoc-rates
     */
    public Map<String, ExchangeRateItemResponseResult> getExchangeRates(ExchangeRatesRequest request) throws CoinpaymentsException, IOException {
        final Object result = execute(request);
        return mapper.convertValue(result, new TypeReference<>() {});
    }

    /**
     * https://www.coinpayments.net/apidoc-create-transaction
     */
    public CreateTransactionResponseResult createTransaction(CreateTransactionRequest request) throws CoinpaymentsException, IOException {
        final Object result = execute(request);
        return mapper.convertValue(result, new TypeReference<>() {});
    }

    /**
     * Execute post request
     *
     * @return result field of {@link GenericResponseBody}
     */
    private Object execute(CoinpaymentsRequest request) throws CoinpaymentsException, IOException {
        final HttpPost post = new HttpPost(API_URL);
        post.addHeader("Connection", "close");
        post.addHeader("Accept", "*/*");
        post.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        post.addHeader("Cookie2", "$Version=1");
        post.addHeader("Accept-Language", "en-US");

        final String requestSpecificPayload = request.getPayload();
        final String resultPayload = requestSpecificPayload + "&version=1&key=" + properties.getPublicKey() + "&format=json";

        final String signature = hmacUtils.hmacHex(resultPayload);
        post.addHeader("HMAC", signature);
        post.setEntity(new StringEntity(resultPayload));

        CloseableHttpResponse response = client.execute(post);
        if (response == null) {
            throw new CoinpaymentsException("Not received response");
        }

        final HttpEntity responseEntity = response.getEntity();
        if (responseEntity == null) {
            final String message =
                    String.format("Empty body with status code = %d", response.getStatusLine().getStatusCode());
            throw new CoinpaymentsException(message);
        }

        final String content = EntityUtils.toString(responseEntity);
        System.out.println(content);

        final GenericResponseBody<?> genericResponseBody = mapper.readValue(content, GenericResponseBody.class);
        if (!genericResponseBody.getError().equals("ok")) {
            throw new CoinpaymentsException(genericResponseBody.getError());
        }

        return genericResponseBody.getResult();
    }
}
