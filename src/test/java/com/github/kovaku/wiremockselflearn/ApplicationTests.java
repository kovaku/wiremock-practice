package com.github.kovaku.wiremockselflearn;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.internal.ResponseSpecificationImpl;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest({"server.port=8080", "resolve.me=test-value"})
public class ApplicationTests {

  @Value("http://localhost:${server.port}")
  private String wireMockHost;

  @Test
  public void todayTest() {
    getCommonRequestSpecification()
        .when()
        .get("/today")
        .then()
        .log().body(true)
        .assertThat()
        .body(equalTo(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)));
  }

  @Test
  public void springPropertyTest() {
    getCommonRequestSpecification()
        .when()
        .get("/property")
        .then()
        .log().body(true)
        .assertThat()
        .body(equalTo("The response is: test-value"));
  }

  private RequestSpecification getCommonRequestSpecification() {
    return given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .baseUri(wireMockHost)
        .log()
        .all(true);
  }
}
