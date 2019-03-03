package com.github.kovaku.wiremockselflearn;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest({"server.port=8080"})
public class ApplicationTests {

  @Value("http://localhost:${server.port}")
  private String wireMockHost;

  @Test
  public void todayTest() {
    getCommonRequestSpecification()
        .when()
        .get("/today")
        .then()
        .assertThat()
        .body(equalTo(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)));
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
