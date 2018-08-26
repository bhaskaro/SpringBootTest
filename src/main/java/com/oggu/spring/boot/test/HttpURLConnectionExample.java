package com.oggu.spring.boot.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpURLConnectionExample {

	private static final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		HttpURLConnectionExample http = new HttpURLConnectionExample();

		System.out.println("Testing 1 - Send Http GET request");

		for (int i = 0; i < 1; i++) {
			http.sendGet();
		}

		System.out.println("\nTesting 2 - Send Http POST request");
		for (int i = 0; i < 1; i++) {
			http.sendPost();
		}

		Map<String, String> header = new HashMap<>();

		header.put("User-Agent", USER_AGENT);
		header.put("Accept-Language", "en-US,en;q=0.5");
		header.put("Content-type", "text/plain;charset=UTF-8");

		System.out.println("\nTesting 3 - Send Http POST/PUT/PATCH request");
		for (int i = 0; i < 1; i++) {
			http.send("http://localhost:9090/greet/", "patch", "voggu", header);
		}

		CloseableHttpClient httpc = HttpClientBuilder.create().build();

		HttpPatch updateRequest = new HttpPatch("http://localhost:9090/greet/");
		updateRequest.setEntity(new StringEntity("voggu", ContentType.TEXT_PLAIN));

		HttpResponse response = httpc.execute(updateRequest);

		System.out.println("patch response : " + response.getStatusLine().getStatusCode() + " - "
				+ response.getStatusLine().getReasonPhrase());

		String inputLine;

		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		try {
			while ((inputLine = br.readLine()) != null) {
				System.out.println(" ======================== " + inputLine);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// HTTP GET request
	private void sendGet() throws Exception {

		String url = "http://localhost:9090/greet/voggu";

		HttpURLConnection con = (HttpURLConnection) getURLConnection(url);

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

	}

	// HTTP POST request
	private void sendPost() throws Exception {

		String url = "http://localhost:9090/greet/";

		HttpURLConnection con = (HttpURLConnection) getURLConnection(url);

		// add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-type", "text/plain");

		String urlParameters = "voggu";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

	}

	private void send(String url, String method, String payload, Map<String, String> header) throws Exception {

		HttpURLConnection con = (HttpURLConnection) getURLConnection(url);

		if (header != null) {
			for (String key : header.keySet()) {
				con.setRequestProperty(key, header.get(key));
			}
		}

		if (method.equalsIgnoreCase("get") || method.equalsIgnoreCase("delete")) {
			// add reuqest header
			con.setRequestMethod(method.toUpperCase());
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} else {
			if (method.equalsIgnoreCase("patch")) {
				// add reuqest header
				System.out.println("==================== method is patch");
				con.setRequestProperty("X-HTTP-Method-Override", "PATCH");
				con.setRequestMethod("POST");
			} else {
				// add reuqest header
				con.setRequestMethod(method.toUpperCase());
			}

			// Send post/put/patch request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			String urlParameters = payload;
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			System.out.println("\nSending 'POST/PUT/PATCH' request to URL : " + url);
			System.out.println("POST/PUT/PATCH parameters : " + urlParameters);
		}

		int responseCode = con.getResponseCode();
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

	}

	@SuppressWarnings("restriction")
	private static final URLConnection getURLConnection(String url) throws MalformedURLException, IOException {

		URLConnection con = null;

		if (new URL(url).getProtocol().equals("https")) {
			URL obj = new URL(null, url, new sun.net.www.protocol.https.Handler());
			con = obj.openConnection();
		} else {
			URL obj = new URL(null, url, new sun.net.www.protocol.http.Handler());
			con = obj.openConnection();

		}

		return con;
	}

}
