package com.ge.helloAuth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestController extends HttpServlet {

	private static final long serialVersionUID = 5582369006381472289L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (doLogout(request, response)) return;
		verifyCode(request);
		doAuthentication(request, response);
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("TestController.doGet()");
		writer.print(getSessionData(request));
		writer.print(getRequestURL(request));
		writer.print(getRequestParameters(request));
		writer.print(getRequestHeaders(request));

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (doLogout(request, response)) return;
		verifyCode(request);
		doAuthentication(request, response);
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("TestController.doGet()");
		writer.print(getSessionData(request));
		writer.print(getRequestURL(request));
		writer.print(getRequestParameters(request));
		writer.print(getRequestHeaders(request));
		writer.print(getRequestBody(request));
	}

	private boolean doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String action = request.getParameter("action");
		if (action == null)
			return false;
		if ("logout".equals(action)) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("test");
			return true;
		}
		return false;
	}

	private boolean doAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Object authenticated = session.getAttribute("authenticated");
		if (authenticated == null) {
			authenticated = false;
			OAuthHttpAuthorize authorize = new OAuthHttpAuthorize("http://localhost:8080/uaa/oauth/authorize",
					"helloauth", "code");
			String authorizeURL = authorize.getAuthorizeURL();
			session.setAttribute("state", authorize.getState());
			System.out.println(authorizeURL);
			response.sendRedirect(authorizeURL);
		}
		return ((Boolean) authenticated).booleanValue();
	}

	private boolean verifyCode(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String state = request.getParameter("state");
		String code = request.getParameter("code");
		if (state != null && code != null) {
			if (code.equals(session.getAttribute("code")))
				System.out.println("Code does not match");
			session.setAttribute("state", state);
			session.setAttribute("authenticated", true);

			OAuthRequest oauthRequest = new OAuthRequest();
			oauthRequest.setTokenURL("http://localhost:8080/uaa/oauth/token");
			oauthRequest.setClient_id("helloauth");
			oauthRequest.setClient_secret("appclientsecret");
			oauthRequest.setGrant_type("authorization_code");
			oauthRequest.setCode(code);
			oauthRequest.setResponse_type("token");

			try {
				OAuthHttpResponse response = OAuthHttpRequester.request(oauthRequest);
				System.out.println(response.getResponseContent());
				OAuthResponse oauthResponse = new OAuthResponse();
				oauthResponse.parse(response.getResponseContent());
				session.setAttribute("oauthResponse", oauthResponse);
				System.out.println(oauthResponse);

				JWTAccessToken access_token = new JWTAccessToken();
				access_token.parse(oauthResponse.getAccess_token());
				session.setAttribute("access_token", access_token);
				setSessionDataFromToken(session, access_token);
				System.out.println(access_token);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return true;
	}

	private void setSessionDataFromToken(HttpSession session, JWTAccessToken access_token) {
		session.setAttribute("OAUTH_GrantType", access_token.getPayload().getGrant_type());
		session.setAttribute("OAUTH_AuthTime", access_token.getPayload().getAuth_time());
		session.setAttribute("OAUTH_Expiration", access_token.getPayload().getExp());
		session.setAttribute("OAUTH_User_Email", access_token.getPayload().getEmail());
		session.setAttribute("OAUTH_User_Id", access_token.getPayload().getUser_id());
		session.setAttribute("OAUTH_User_Name", access_token.getPayload().getUser_name());
		session.setAttribute("OAUTH_User_Scope", access_token.getPayload().getScope());
	}

	private String getSessionData(HttpServletRequest request) {
		HttpSession session = request.getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(getTableStart());
		sb.append(getTableRow("sessionAttributes", session.getId()));
		Enumeration<String> attributeNames = session.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();
			String attributeValue = toString(session.getAttribute(attributeName));
			sb.append(getTableRow(attributeName, attributeValue));
		}
		sb.append(getTableEnd());
		return sb.toString();
	}

	private String toString(Object obj) {
		return obj.toString();
	}

	private String getRequestURL(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		sb.append(getTableStart());
		sb.append(getTableRow("requestURL", ""));
		sb.append(getTableRow("requestURL", request.getRequestURL().toString()));
		sb.append(getTableEnd());
		return sb.toString();
	}

	private String getRequestParameters(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		sb.append(getTableStart());
		sb.append(getTableRow("requestParameters", ""));
		Map<String, String[]> parameterMap = request.getParameterMap();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			String[] parameterValues = parameterMap.get(parameterName);
			String parameterValue = "";
			for (int i = 0; i < parameterValues.length; i++)
				parameterValue = parameterValue + " " + parameterValues[i];
			sb.append(getTableRow(parameterName, parameterValue));
		}
		sb.append(getTableEnd());
		return sb.toString();
	}

	private String getRequestHeaders(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		sb.append(getTableStart());
		sb.append(getTableRow("requestHeaders", ""));
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			sb.append(getTableRow(headerName, headerValue));
		}
		sb.append(getTableEnd());
		return sb.toString();
	}

	private String getRequestBody(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("<br>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	private String getTableRow(String key, String value) {
		return "<tr><td><b>" + key + "</b></td><td>" + value + "</td></tr>";
	}

	private String getTableStart() {
		return "<table>";
	}

	private String getTableEnd() {
		return "</table>";
	}

}
