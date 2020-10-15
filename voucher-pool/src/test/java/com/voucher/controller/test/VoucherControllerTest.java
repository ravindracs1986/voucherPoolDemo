package com.voucher.controller.test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.MediaType;

import com.voucher.model.RecipientDto;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;

public class VoucherControllerTest {
	
	
	@BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }
	
	/**
	 * test all recipient service 
	 */
	@Test
    public void testAllRecipientsSuccess() {
        get("/voucher-pool/api/v1/allRecipients").toString();
     }
	
	/**
	 * test create voucher 
	 */
	@Test
    public void testGenerateVoucherSuccess() {
	RecipientDto recipientDto =new RecipientDto();
		recipientDto.setOfferName("OFFER_20");
		recipientDto.setExpDate("20/10/2020");
		 final Response response = RestAssured.given()
			        .contentType(MediaType.APPLICATION_JSON_VALUE)
			        .body(recipientDto)
			        .post("/voucher-pool/api/v1/generateVoucherCode");
			    assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
	
	}
	/**
	 * testing +ve senario for voucher validation
	 */
	@Test
    public void testValidateVoucherSuccess() {
        get("/voucher-pool/api/v1/validateVoucherCode?voucherCode=DNvHxKzu&email=ravindra1@gmail.com").then().body("status", equalTo("SUCCESS"));
     }

	/**
	 * testing fail senario if vouchercode wrong
	 */
	@Test
    public void testValidateVoucherCodeFailed() {
        get("/voucher-pool/api/v1/validateVoucherCode?voucherCode=DNvHxKzu444&email=ravindra1@gmail.com").then().body("status", equalTo("Fail"));
     }
	/**
	 * testing fail senario if email associated wrong 
	 */
	@Test
    public void testValidateVoucherEmailFailed() {
        get("/voucher-pool/api/v1/validateVoucherCode?voucherCode=DNvHxKzu&email=ravi@gmail.com").then().body("status", equalTo("Fail"));
     }
	/**
	 * testing success for all voucher service by passing email
	 */
	@Test
    public void testAllVoucherEmailSuccess() {
        get("/voucher-pool/api/v1/voucherCodes?email=ravindra1@gmail.com").then().body("status", equalTo("SUCCESS"));
     }
	/**
	 * testing fail for all voucher service by wrong passing email
	 */
	@Test
    public void testAllVoucherEmailFailed() {
        get("/voucher-pool/api/v1/voucherCodes?email=ravi@gmail.com").then().body("status", equalTo("Fail"));
     }
}
