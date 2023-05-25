package org.springframework.http.client;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;

import java.net.URI;

public class ReactorClientHttpConnectorTest {

    public static void main(String[] args) throws Exception {
        ReactorClientHttpConnector reactorClientHttpConnector = new ReactorClientHttpConnector();
        reactorClientHttpConnector.connect(HttpMethod.GET, URI.create("https://www.bing.com"), clientHttpRequest -> {
            return null;
        });
    }
}
