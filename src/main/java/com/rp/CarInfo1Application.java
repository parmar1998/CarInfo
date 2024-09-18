package com.rp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class CarInfo1Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CarInfo1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fetchAndProcessCricketData();
	}

	private void fetchAndProcessCricketData() {
		String apiUrl = "https://api.cuvora.com/car/partner/cricket-data";
		String apiKey = "test-creds@2320";

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("apiKey", apiKey);
		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

		if (response.getStatusCode().is2xxSuccessful()) {
			processCricketData(response.getBody());
		} else {
			System.out.println("Error: " + response.getStatusCode() + " - " + response.getBody());
		}
	}

	private void processCricketData(String jsonData) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode rootNode = objectMapper.readTree(jsonData);
			JsonNode dataNode = rootNode.path("data");

			int highestScore = 0;
			String highestScoreTeam = "";
			int matchCount300Plus = 0;

			for (JsonNode match : dataNode) {
				int score1 = getScore(match.path("t1s"));
				int score2 = getScore(match.path("t2s"));

				if (score1 > highestScore) {
					highestScore = score1;
					highestScoreTeam = match.path("t1").asText();
				}
				if (score2 > highestScore) {
					highestScore = score2;
					highestScoreTeam = match.path("t2").asText();
				}

				if (score1 + score2 > 300) {
					matchCount300Plus++;
				}
			}

			System.out.println("Highest Score: " + highestScore + " and Team Name is: " + highestScoreTeam);
			System.out.println("Number Of Matches with total 300 Plus Score: " + matchCount300Plus);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// use regex here to split the score
	private int getScore(JsonNode scoreNode) {
		String scoreText = scoreNode.asText();
		return Integer.parseInt(scoreText.split("/")[0]);
	}
}
