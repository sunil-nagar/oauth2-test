package com.ge.helloAuth;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class JWTAccessTokenPayload {
	private String jti;
	private String sub;
	private ArrayList<String> scope;
	private String client_id;
	private String cid;
	private String azp;
	private String grant_type;
	private String user_id;
	private String origin;
	private String user_name;
	private String email;
	private Integer auth_time;
	private String rev_sig;
	private Integer iat;
	private Integer exp;
	private String iss;
	private String zid;
	private ArrayList<String> aud;

	public JWTAccessTokenPayload() {

	}

	public void parse(String d_payload) {
		JSONObject jsonResponse = new JSONObject(d_payload);
		jti = (String) jsonResponse.get("jti");
		sub = (String) jsonResponse.get("sub");
		scope = toArrayList((JSONArray) jsonResponse.get("scope"));
		client_id = (String) jsonResponse.get("client_id");
		cid = (String) jsonResponse.get("cid");
		azp = (String) jsonResponse.get("azp");
		grant_type = (String) jsonResponse.get("grant_type");
		user_id = (String) jsonResponse.get("user_id");
		origin = (String) jsonResponse.get("origin");
		user_name = (String) jsonResponse.get("user_name");
		email = (String) jsonResponse.get("email");
		auth_time = (Integer) jsonResponse.get("auth_time");
		rev_sig = (String) jsonResponse.get("rev_sig");
		iat = (Integer) jsonResponse.get("iat");
		exp = (Integer) jsonResponse.get("exp");
		iss = (String) jsonResponse.get("iss");
		zid = (String) jsonResponse.get("zid");
		aud = toArrayList((JSONArray) jsonResponse.get("aud"));
	}

	private ArrayList<String> toArrayList(JSONArray arr) {
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = 0; i < arr.length(); i++) {
			temp.add((String) arr.get(i));
		}
		return temp;
	}

	public String getJti() {
		return jti;
	}

	public void setJti(String jti) {
		this.jti = jti;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public ArrayList<String> getScope() {
		return scope;
	}

	public void setScope(ArrayList<String> scope) {
		this.scope = scope;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getAzp() {
		return azp;
	}

	public void setAzp(String azp) {
		this.azp = azp;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAuth_time() {
		return auth_time;
	}

	public void setAuth_time(Integer auth_time) {
		this.auth_time = auth_time;
	}

	public String getRev_sig() {
		return rev_sig;
	}

	public void setRev_sig(String rev_sig) {
		this.rev_sig = rev_sig;
	}

	public Integer getIat() {
		return iat;
	}

	public void setIat(Integer iat) {
		this.iat = iat;
	}

	public Integer getExp() {
		return exp;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}

	public String getIss() {
		return iss;
	}

	public void setIss(String iss) {
		this.iss = iss;
	}

	public String getZid() {
		return zid;
	}

	public void setZid(String zid) {
		this.zid = zid;
	}

	public ArrayList<String> getAud() {
		return aud;
	}

	public void setAud(ArrayList<String> aud) {
		this.aud = aud;
	}

	@Override
	public String toString() {
		return "JWTAccessTokenPayload [jti=" + jti + ", sub=" + sub + ", scope=" + scope + ", client_id=" + client_id
				+ ", cid=" + cid + ", azp=" + azp + ", grant_type=" + grant_type + ", user_id=" + user_id + ", origin="
				+ origin + ", user_name=" + user_name + ", email=" + email + ", auth_time=" + auth_time + ", rev_sig="
				+ rev_sig + ", iat=" + iat + ", exp=" + exp + ", iss=" + iss + ", zid=" + zid + ", aud=" + aud + "]";
	}

}
