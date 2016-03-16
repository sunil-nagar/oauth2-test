package com.ge.helloAuth;

public class OAuthHttpResponse {

	private int statusCode;
	private String responseContent;

	public OAuthHttpResponse() {

	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getResponseContent() {
		return responseContent;
	}

	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}

	@Override
	public String toString() {
		return "OAuthHttpResponse [statusCode=" + statusCode + ", responseContent=" + responseContent + "]";
	}

}
