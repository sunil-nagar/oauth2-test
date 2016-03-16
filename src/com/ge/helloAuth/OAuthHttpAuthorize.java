package com.ge.helloAuth;

import java.util.Random;

public class OAuthHttpAuthorize {

	private String authorizeBaseURL;
	private String client_id;
	private String response_type;
	private String state;

	public OAuthHttpAuthorize(String authorizeURL, String client_id, String response_type) {
		this.authorizeBaseURL = authorizeURL;
		this.client_id = client_id;
		this.response_type = response_type;
		this.state = generateState();
	}

	// http://localhost:8080/uaa/oauth/authorize?client_id=helloauth&response_type=code&state=mBz31h
	public String getAuthorizeURL() {
		return authorizeBaseURL + "?client_id=" + client_id + "&response_type=" + response_type + "&state=" + state;
	}

	private String generateState() {
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		for (int i = 0; i < 6; i++) {
			sb.append(alphabet.charAt(r.nextInt(alphabet.length())));
		}
		return sb.toString();
	}

	public String getAuthorizeBaseURL() {
		return authorizeBaseURL;
	}

	public void setAuthorizeBaseURL(String authorizeBaseURL) {
		this.authorizeBaseURL = authorizeBaseURL;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getResponse_type() {
		return response_type;
	}

	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "OAuthHttpAuthorize [authorizeBaseURL=" + authorizeBaseURL + ", client_id=" + client_id
				+ ", response_type=" + response_type + ", state=" + state + "]";
	}

}
