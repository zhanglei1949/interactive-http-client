package com.alibaba.graphscope.interactive;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Send to interactive service via this client.
 */
public class Client {

    public Client(String addressToBound) {
        address = addressToBound;

        this.client = HttpClient.newBuilder()
                .connectTimeout(
                        Duration.ofMillis(TIMEOUT_MILLION_SEC))
                .build();
        LOG.info("Create client to interactive service at {}", address);
    }

    public List<Long> SubmitCtrlQuery(long company_id, int hop_limit, double threshold, int limit) {
        byte[] bytesArray = new byte[8 + 4 + 8 + 4 + 1];
        Encoder encoder = new Encoder(bytesArray);
        encoder.put_long(company_id);
        encoder.put_int(hop_limit);
        encoder.put_double(threshold);
        encoder.put_int(limit);
        encoder.put_byte((byte) 1);

        List<Long> result = null;
        try {
            URI httpURI = new URI("http://" + address + INTERACTIVE_QUERY_PATH);
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(httpURI)
                    .headers(CONTENT_TYPE, TEXT_PLAIN)
                    .POST(
                            HttpRequest.BodyPublishers.ofByteArray(
                                    (byte[]) bytesArray))
                    .build();

            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            String responseBody = response.body();

            System.out.println("Status code: " + statusCode);
            System.out.println("Response body: " + responseBody);
            Decoder decoder = new Decoder(responseBody.getBytes());
            int size = decoder.get_int();
            result = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                result.add(decoder.get_long());
            }
            LOG.info("Get {} results", size);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<List<Long>> SubmitGroupQuery(int hop_limit, List<Long> company_ids, int limit) {
        byte[] bytesArray = new byte[4 + 4 + company_ids.size() * 8 + 4 + 1];
        Encoder encoder = new Encoder(bytesArray);
        encoder.put_int(hop_limit);
        encoder.put_int(limit);
        encoder.put_int(company_ids.size());
        for (long company_id : company_ids) {
            encoder.put_long(company_id);
        }
        encoder.put_byte((byte) 2);

        List<List<Long>> result = null;
        try {
            URI httpURI = new URI("http://" + address + INTERACTIVE_QUERY_PATH);
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(httpURI)
                    .headers(CONTENT_TYPE, TEXT_PLAIN)
                    .POST(
                            HttpRequest.BodyPublishers.ofByteArray(
                                    (byte[]) bytesArray))
                    .build();
            HttpResponse<byte[]> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofByteArray());

            int statusCode = response.statusCode();
            byte[] responseBody = response.body();

            Decoder decoder = new Decoder(responseBody);
            int size = decoder.get_int();
	    LOG.info("got result size: {}", size);
	    System.out.println("result size" + size);
            result = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                int size2 = decoder.get_int();
		LOG.info("path size: {}", size2); 
		System.out.println("path size " + size2);
                List<Long> result2 = new ArrayList<>(size2);
                for (int j = 0; j < size2; j++) {
                    result2.add(decoder.get_long());
                }
                result.add(result2);
            }
            LOG.info("Get {} results", size);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String address;
    private HttpClient client;
    private static final int TIMEOUT_MILLION_SEC = 30000;
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String TEXT_PLAIN = "text/plain;charset=UTF-8";
    private static final String INTERACTIVE_QUERY_PATH = "/interactive/query";
    private static final Logger LOG = LoggerFactory.getLogger(Client.class);
}
