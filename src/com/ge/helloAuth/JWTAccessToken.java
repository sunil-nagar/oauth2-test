package com.ge.helloAuth;

import org.apache.commons.codec.binary.Base64;

public class JWTAccessToken {

	private JWTAccessTokenHeader header;
	private JWTAccessTokenPayload payload;
	private String signature;

	public JWTAccessToken() {

	}

	public void parse(String access_token) {
		String[] access_token_data = access_token.split("\\.");
		String d_header = access_token_data[0];
		d_header = new String(Base64.decodeBase64(d_header));
		this.header = new JWTAccessTokenHeader();
		this.header.parse(d_header);
		String d_payload = access_token_data[1];
		d_payload = new String(Base64.decodeBase64(d_payload));
		this.payload = new JWTAccessTokenPayload();
		this.payload.parse(d_payload);
		this.signature = access_token_data[2];
	}

	public JWTAccessTokenHeader getHeader() {
		return header;
	}

	public void setHeader(JWTAccessTokenHeader header) {
		this.header = header;
	}

	public JWTAccessTokenPayload getPayload() {
		return payload;
	}

	public void setPayload(JWTAccessTokenPayload payload) {
		this.payload = payload;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public String toString() {
		return "JWTAccessToken [header=" + header + ", payload=" + payload + ", signature=" + signature + "]";
	}

}
