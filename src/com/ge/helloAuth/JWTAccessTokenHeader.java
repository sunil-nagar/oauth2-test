package com.ge.helloAuth;

import org.json.JSONObject;

public class JWTAccessTokenHeader {

	private String alg;

	public JWTAccessTokenHeader() {

	}

	public void parse(String d_header) {
		JSONObject jsonResponse = new JSONObject(d_header);
		alg = (String) jsonResponse.get("alg");
	}

	public String getAlg() {
		return alg;
	}

	public void setAlg(String alg) {
		this.alg = alg;
	}

	@Override
	public String toString() {
		return "JWTAccessTokenHeader [alg=" + alg + "]";
	}

}
