package io.github.thinkframework.cloud.gateway.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;

/**
 * 网关
 * 支持基本的请求转发
 * @author lixiaobin
 */
@WebServlet(urlPatterns = "/*")
public class GatewayServlet extends GenericServlet {

    private static final Logger logger = LoggerFactory.getLogger(GatewayServlet.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        ClientHttpRequest clientHttpRequest = createRequest(httpServletRequest, httpServletResponse);
        // 执行请求
        ClientHttpResponse clientHttpResponse = clientHttpRequest.execute();
        HttpHeaders httpHeaders = clientHttpResponse.getHeaders();
        httpHeaders.forEach((name,value) -> {
            httpServletResponse.addHeader(name,value.toString());
        });
        byte[] bytes = clientHttpResponse.getBody().readAllBytes();
        httpServletResponse.getOutputStream().write(bytes);
    }

    private ClientHttpRequest createRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        String requestUri = requestUri(httpServletRequest, httpServletResponse); // 解析请求行
        ClientHttpRequest clientHttpRequest = requestFactory.createRequest(URI.create(requestUri), HttpMethod.valueOf(httpServletRequest.getMethod()));
        return clientHttpRequest;
    }

    private String requestUri(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String requestUri = httpServletRequest.getRequestURI();
        if(!(requestUri != null && requestUri.length() != 0)) {
            return requestUri; // 不需要负载均衡
        }
        String serviceId = null;
        String[] paths = requestUri.split("/");
        for (int i =0;i<paths.length;i++) {
            String path = paths[i];
            if(path != null && path.length() != 0) {
                // 默认取第一个就结束
                serviceId = path;
                break;
            }
        }
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceId);
        if (serviceInstances == null || serviceInstances.size() == 0) {
            return requestUri; // 没有服务实例
        }
        // 随机一个服务实例

        Random random = new Random(serviceInstances.size());
        ServiceInstance serviceInstance = loadBalancerClient.choose(serviceId);
        // TODO 移除服务id,冲构成一个方法
        String requestUrl =  httpServletRequest.getRequestURL().toString();
        requestUrl = requestUrl.replaceFirst("/"+serviceId,"");
        // 重新生成URI
        URI reconstructURI = loadBalancerClient.reconstructURI(serviceInstance, URI.create(requestUrl));
        return reconstructURI.toString();
    }

    /**
     * 填充请求头喝请求体
     * @param clientHttpRequest
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws IOException
     */
    private void resolvingClientHttpRequest(ClientHttpRequest clientHttpRequest, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        // 请求头
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            clientHttpRequest.getHeaders().add(headerName,httpServletRequest.getHeader(headerName));
        }
        // 请求体
        byte[] bytes = httpServletRequest.getInputStream().readAllBytes();
        clientHttpRequest.getBody().write(bytes);
    }

    /**
     * 填充响应头和响应体
     * @param clientHttpResponse
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws IOException
     */
    private void resolvingClientHttpResponse(ClientHttpResponse clientHttpResponse, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        // 响应头
        Collection<String> headerNames = httpServletResponse.getHeaderNames();
        for(String headerName : headerNames) {
            clientHttpResponse.getHeaders().add(headerName,httpServletRequest.getHeader(headerName));
        }
        // 响应体
        byte[] bytes = clientHttpResponse.getBody().readAllBytes();
        httpServletResponse.getOutputStream().write(bytes);
    }
}
