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
    basicGetRequestTestCase("/property", "test-value");
  }

  @Test
  public void propertyHelperTest() {
    basicGetRequestTestCase("/helper", "Handlebar helper resolved me!");
  }

  @Test
  public void propertyHelperWithFileTest() {
    basicGetRequestTestCase("/helper_with_file", "Handlebar helper resolved me from custom file!");
  }

  private void basicGetRequestTestCase(String path, String expectedBody) {
    getCommonRequestSpecification()
        .when()
        .get(path)
        .then()
        .log().body(true)
        .assertThat()
        .body(equalTo("The response is: " + expectedBody));
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
