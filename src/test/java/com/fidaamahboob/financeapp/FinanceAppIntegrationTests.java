package com.fidaamahboob.financeapp;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.fidaamahboob.financeapp.api.model.FinanceData;
import com.fidaamahboob.financeapp.api.repository.FinancialDataRepository;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FinanceAppIntegrationTests {

	@Autowired
	private FinancialDataRepository financialDataRepository;

	@Autowired
	private TestRestTemplate restTemplate;

	@Container
	static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0")
			.withDatabaseName("testdb")
			.withUsername("testuser")
			.withPassword("testpass");

	@BeforeAll
	static void setUp() {
		mySQLContainer.start();
		// Set the MySQL container connection properties dynamically
		System.setProperty("spring.datasource.url", mySQLContainer.getJdbcUrl());
		System.setProperty("spring.datasource.username", mySQLContainer.getUsername());
		System.setProperty("spring.datasource.password", mySQLContainer.getPassword());
	}

	@Test
	void shouldreturnRecordById() {
		// given
		FinanceData data = new FinanceData(1L, "13/08/2024", 2200, 500, "Paid salary and rent");
		financialDataRepository.save(data);

		// when
		ResponseEntity<FinanceData> resp = restTemplate.getForEntity("/finance/v1/data/record/1", FinanceData.class);

		// Then
		assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(resp.getBody()).isNotNull();
		assertThat(resp.getBody().getIncome()).isEqualTo(2200);
	}

	@Test
	public void testCreateFinanceData() {
		FinanceData data = new FinanceData(1L, "25/03/2021", 2200, 500, "new record");

		ResponseEntity<FinanceData> response = restTemplate.postForEntity("/finance/v1/data/record/create", data,
				FinanceData.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("25/03/2021", response.getBody().getDate());
		assertEquals("new record", response.getBody().getDescription());
		assertEquals(2200, response.getBody().getIncome());
		assertEquals(500, response.getBody().getExpense());
	}

	@Test
	public void testGetFinanceDataById() {

		//Given
		FinanceData data = new FinanceData(1L, "25/03/2021", 2200, 500, "new record");
		financialDataRepository.save(data);


		//When
		ResponseEntity<FinanceData> response = restTemplate.getForEntity("/finance/v1/data/record/1", FinanceData.class);


		//Then
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(1L, response.getBody().getId());
	}

	@Test
	public void testGetAllFinanceData_EmptyList() {
		ResponseEntity<FinanceData[]> response = restTemplate.getForEntity("/finance/v1/data/record/all", FinanceData[].class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().length == 0);
	}

	@AfterAll
	static void tearDown() {
		mySQLContainer.close();
	}

}
