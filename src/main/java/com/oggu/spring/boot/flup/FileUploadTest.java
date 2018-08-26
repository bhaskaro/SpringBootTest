package com.oggu.spring.boot.flup;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;

public class FileUploadTest {

	public static void main(String[] args) throws IOException {

		String url = "http://localhost:9090/api/upload/";
		File textFile = new File("C:\\Users\\Bhaskar\\Downloads\\apache-ftpserver-1.1.1\\README.txt");

		for (int i = 0; i < 1000; i++) {

			fileupload2(url, textFile);
		}
	}

	private static void fileupload2(String url, File file) throws ClientProtocolException, IOException {

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addPart("file", new FileBody(file));

		HttpPost request = new HttpPost(url);
		request.setEntity(builder.build());

		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = client.execute(request);

		System.out.println("response == " + response.getStatusLine().getReasonPhrase());
	}

}
