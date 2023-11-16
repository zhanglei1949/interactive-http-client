package com.alibaba.graphscope.interactive;

import com.alibaba.graphscope.gaia.proto.Argument;
import java.net.http.HttpClient;

/**
 * Send to interactive service via this client.
 */
public class Client {

    public Client(String addressToBound){
        address = addressToBound;
        this.httpClient =
                HttpClient.newBuilder()
                        .connectTimeout(
                                Duration.ofMillis(HiactorConfig.HIACTOR_TIMEOUT.get(graphConfig)))
                        .build();
    }

    private String address;
    private HttpClient client;
}
