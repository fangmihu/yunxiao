package com.tangxigua.yunxiao.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ElasticsearchUtils {

    static private RestClientBuilder restClientBuilder;
    static private RestClient restClient = null;
    static boolean inited = false;
    static public void init(String username, String password, HttpHost... serviceHosts) {
        if (!inited) {
            if (StringUtils.isEmpty(username)||  StringUtils.isEmpty(password)) {
                restClientBuilder = RestClient.builder(serviceHosts);
            } else {
                final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                credentialsProvider.setCredentials(AuthScope.ANY,
                        new UsernamePasswordCredentials(username, password));
                restClientBuilder = RestClient.builder(serviceHosts).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                        return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                });
            }
            restClientBuilder.setMaxRetryTimeoutMillis(10000);
            inited = true;
        }else{
            return;
        }
    }
    static public RestClient getRestClient(){
        if(inited){
            if(restClient == null){
                restClient = restClientBuilder.build();
            }
            return restClient;
        }else{
            return null;
        }
    }

    static public boolean index(String index, String type, String id, String entityJsonString) throws IOException{
        HttpEntity entity = new NStringEntity(entityJsonString, ContentType.APPLICATION_JSON);
        Map<String, String> params = new HashMap();
        params.put("pretty", "true");
        params.put("refresh","true");
        Response response = getRestClient().performRequest("PUT", getEndpoint(index, type, id), params, entity);
        return response.getStatusLine().getStatusCode() == 201;
    }

    static public String get(String index, String type, String id) throws IOException{
        HttpEntity entity = new NStringEntity("{}", ContentType.APPLICATION_JSON);
        Map<String, String> params = new HashMap();
        params.put("pretty", "true");
        Response response = getRestClient().performRequest("GET", getEndpoint(index, type, id), params, entity);
        return EntityUtils.toString(response.getEntity());

    }

    static public boolean delete(String index, String type, String id) throws IOException{
        HttpEntity entity = new NStringEntity("{}", ContentType.APPLICATION_JSON);
        Map<String, String> params = new HashMap();
        params.put("pretty", "true");
        params.put("refresh","true");
        Response response = getRestClient().performRequest("Delete", getEndpoint(index, type, id), params, entity);
        return response.getStatusLine().getStatusCode() == 200;
    }

    static public boolean update(String index, String type, String id, String entityJsonString) throws IOException{
        HttpEntity entity = new NStringEntity(entityJsonString, ContentType.APPLICATION_JSON);
        Map<String, String> params = new HashMap();
        params.put("pretty", "true");
        params.put("refresh","true");
        Response response = getRestClient().performRequest("POST", getEndpoint(index, type, id + "/_update"), params, entity);
        return response.getStatusLine().getStatusCode() == 200;
    }

    static public boolean bulk(String index, String type, String entityJsonString) throws IOException{
        HttpEntity entity = new NStringEntity(entityJsonString, ContentType.APPLICATION_JSON);
        Map<String, String> params = new HashMap();
        params.put("pretty", "true");
        params.put("refresh","true");
        Response response = getRestClient().performRequest("POST", getEndpoint(index, type, "_bulk"), params, entity);
        return response.getStatusLine().getStatusCode() == 200;
    }

    static public String multiGet(String index, String type, String entityJsonString) throws IOException {
        HttpEntity entity = new NStringEntity(entityJsonString, ContentType.APPLICATION_JSON);
        Map<String, String> params = new HashMap();
        params.put("pretty", "true");
        Response response = getRestClient().performRequest("GET", getEndpoint(index, type, "_mget"), params, entity);
        return EntityUtils.toString(response.getEntity());
    }

    static public boolean sendCmd(String method, String endPoint,String queryString){
        HttpEntity entity = new NStringEntity(queryString, ContentType.APPLICATION_JSON);
        try {
            Map<String, String> params = new HashMap();
            params.put("pretty", "true");
            Response response = getRestClient().performRequest(method, endPoint, params, entity);
            return response.getStatusLine().getStatusCode() == 200;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    static public String search(String index, String type,String queryString) throws IOException{
        HttpEntity entity = new NStringEntity(queryString, ContentType.APPLICATION_JSON);
        Map<String, String> params = new HashMap();
        params.put("pretty", "true");
        Response response = getRestClient().performRequest("GET", getEndpoint(index, type, "_search"), params, entity);
        return EntityUtils.toString(response.getEntity());

    }

    static public String getEndpoint(String index, String type,String action){
        if(StringUtils.isEmpty(index) || StringUtils.isEmpty(action)){
            //todo 原来的逻辑是抛出异常
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer("/");
        stringBuffer.append(index);
        if(StringUtils.isNotEmpty(type)){
            stringBuffer.append("/").append(type);
        }
        stringBuffer.append("/").append(action);
        return stringBuffer.toString();
    }
}
