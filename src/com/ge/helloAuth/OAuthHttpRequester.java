package com.ge.helloAuth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class OAuthHttpRequester {

	public static OAuthHttpResponse request(OAuthRequest oauthRequest) throws ClientProtocolException, IOException {
		OAuthHttpResponse response = new OAuthHttpResponse();
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(oauthRequest.getTokenURL());
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("client_id", oauthRequest.getClient_id()));
		urlParameters.add(new BasicNameValuePair("client_secret", oauthRequest.getClient_secret()));
		urlParameters.add(new BasicNameValuePair("grant_type", oauthRequest.getGrant_type()));
		urlParameters.add(new BasicNameValuePair("code", oauthRequest.getCode()));
		urlParameters.add(new BasicNameValuePair("response_type", oauthRequest.getResponse_type()));
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		post.addHeader("Authorization", "Basic " + oauthRequest.getCredentials());
		post.addHeader("Content-Type", "application/x-www-form-urlencoded");
		HttpResponse httpResponse = client.execute(post);
		response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
		BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		response.setResponseContent(result.toString());
		return response;
	}

	public static void main(String[] args) {
	}
	
}