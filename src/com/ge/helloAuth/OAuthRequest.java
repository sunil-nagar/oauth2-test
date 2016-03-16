package com.ge.helloAuth;

import org.apache.commons.codec.binary.Base64;

public class OAuthRequest {

	private String tokenURL;
	private String client_id;
	private String client_secret;
	private String grant_type;
	private String code;
	private String response_type;

	public OAuthRequest() {

	}

	public String getCredentials() {
		String credentials = client_id + ":" + client_secret;
		credentials = Base64.encodeBase64String(credentials.getBytes());
		return credentials;
	}

	public String getTokenURL() {
		return tokenURL;
	}

	public void setTokenURL(String tokenURL) {
		this.tokenURL = tokenURL;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getResponse_type() {
		return response_type;
	}

	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}

	@Override
	public String toString() {
		return "OAuthRequest [tokenURL=" + tokenURL + ", client_id=" + client_id + ", client_secret=" + client_secret
				+ ", grant_type=" + grant_type + ", code=" + code + ", response_type=" + response_type
				+ ", getCredentials()=" + getCredentials() + "]";
	}

}
