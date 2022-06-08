package com.gh.tone.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class HttpClientWithPoolApp {
    private PoolingHttpClientConnectionManager poolConnManager;
    private final int maxTotalPool = 200;
    private final int maxConPerRoute = 20;
    private final int socketTimeout = 2000;
    private final int connectionRequestTimeout = 2000;
    private final int connectTimeout = 1000;

    public void init() {
        try {
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
            HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslsf)
                    .build();
            poolConnManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            poolConnManager.setMaxTotal(maxTotalPool);
            poolConnManager.setDefaultMaxPerRoute(maxConPerRoute);
            SocketConfig sc = SocketConfig.custom().setSoTimeout(socketTimeout).build();
            poolConnManager.setDefaultSocketConfig(sc);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    public CloseableHttpClient getConnection() {
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)
                .setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(poolConnManager)
                .setDefaultRequestConfig(requestConfig).build();
        if (poolConnManager != null && poolConnManager.getTotalStats() != null) {
            System.out.println("now client pool " + poolConnManager.getTotalStats().toString());
        }
        return httpClient;
    }

    public String postMsg(String url) {
        String returnStr = null;
        //参数检测
        if (url == null || "".equals(url)) {
            return returnStr;
        }
        HttpPost httpPost = new HttpPost(url);
        try {

            httpPost.setEntity(new UrlEncodedFormEntity(new ArrayList<>(), "UTF-8"));
            System.out.println(" 开始发送 请求：url" + url);

            CloseableHttpClient client = HttpClients.createDefault();//this.getConnection();
            CloseableHttpResponse response = client.execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            System.out.println("status=" + status);
            if (status >= 200 && status < 400) {
                HttpEntity entity = response.getEntity();
                String resopnse = "";
                if (entity != null) {
                    resopnse = EntityUtils.toString(entity, "utf-8");
                }
                System.out.println(" 接收响应：url" + url + " status=" + status);
                return entity != null ? resopnse : null;
            } else {
                HttpEntity entity = response.getEntity();
                httpPost.abort();
                System.out.println(" 接收响应：url" + url + " status=" + status + " resopnse=" + EntityUtils.toString(entity, "utf-8"));
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        } catch (Exception e) {
            httpPost.abort();
            e.printStackTrace();
        }
        return returnStr;
    }

    public static void main(String[] args) {
        HttpClientWithPoolApp demo02 = new HttpClientWithPoolApp();
        String url = "http://www.baidu.com";

        for (int i = 0; i < 100; i++) {

        }
        String result = demo02.postMsg(url);
        System.out.println(result);
    }
}
