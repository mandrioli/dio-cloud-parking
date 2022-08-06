package me.dio.parking.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import me.dio.parking.controller.dto.ParkingCreateDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class ParkingControllerIT extends AbstractContainerBase {
class ParkingControllerIT {
	
	@LocalServerPort
	private int randomPort;
	
	@BeforeEach
	public void setUpTest() {
		RestAssured.port = randomPort;
	}

	@Test
	void whenFindAllThenCheckResult() {
		RestAssured.given()
					.auth().basic("user", "12345")
					.when()
					.get("/parking")
					.then()
					.statusCode(HttpStatus.OK.value());
					//.body("license[0]", Matchers.equalTo("MJT 4931"));
					//.extract().response().body().prettyPrint();
	}

	@Test
	void whenCreateThenCheckIsCreated() {
		
		var createDTO = new ParkingCreateDTO();
		createDTO.setColor("Branco");
		createDTO.setLicense("ABC 5555");
		createDTO.setModel("Audi");
		createDTO.setState("SC");
		
		RestAssured.given()
		.auth().basic("user", "12345")
		.when()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.body(createDTO)
		.post("/parking")
		.then()
		.statusCode(HttpStatus.CREATED.value())
		.body("license", Matchers.equalTo("ABC 5555"));
	}

}
