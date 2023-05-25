package org.springframework.http.client;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.AsyncClientHttpRequest;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.IOException;
import java.net.URI;

public class Netty4ClientHttpRequestFactoryTest {

    public static void main(String[] args) throws Exception {
        Netty4ClientHttpRequestFactory netty4ClientHttpRequestFactory = new Netty4ClientHttpRequestFactory();
        ClientHttpRequest clientHttpRequest = netty4ClientHttpRequestFactory.createRequest(URI.create("https://www.bing.com"), HttpMethod.GET);
        ClientHttpResponse clientHttpResponse = clientHttpRequest.execute();
        AsyncClientHttpRequest asyncClientHttpRequest = netty4ClientHttpRequestFactory.createAsyncRequest(URI.create("https://www.bing.com"), HttpMethod.GET);
        ListenableFuture<ClientHttpResponse> listenableFuture =  asyncClientHttpRequest.executeAsync();
//        listenableFuture.
    }


}
