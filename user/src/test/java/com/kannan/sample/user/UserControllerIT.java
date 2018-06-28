package com.kannan.sample.user;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIT {

	private static final String HTTP_LOCALHOST = "http://localhost:";
	private static String successResponse = "{\"status\":\"OK\",\"message\":\"Success\",\"data\":[{\"id\":1,\"userName\":\"Kannan\",\"status\":\"Active\"}]}";
	private static String failureResponse = "{\"status\":\"NO_CONTENT\",\"message\":\"User Name Kannan1 not found\",\"data\":null}";

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testRetrieveUser() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/user/Kannan"), HttpMethod.GET,
				entity, String.class);
		JSONAssert.assertEquals(successResponse, response.getBody(), false);
	}

	@Test
	public void testRetrieveInvalidUser() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/user/Kannan1"), HttpMethod.GET,
				entity, String.class);
		JSONAssert.assertEquals(failureResponse, response.getBody(), false);
	}

	private String createURLWithPort(String uri) {
		return HTTP_LOCALHOST + port + uri;
	}
}
