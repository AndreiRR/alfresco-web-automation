package api;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;

@Slf4j
public class NodesRequests {

  private static final String BASE_URL = "http://qaexercise.envalfresco.com/alfresco/api/-default-/public/alfresco/versions/1/nodes";
  private static final String CREATE_URI = "/-my-/children";
  private static final String DELETE_URI = "/-my-/children";
  private static final String ENTRIES_URI = "/-my-/children";

  /**
   * POST request to create event
   *
   * @param requestBody event details
   * @return event details
   */
  public Response createFolder(String requestBody) {
    RequestSpecification spec = new RequestSpecBuilder()
        .setContentType(ContentType.JSON)
        .setBaseUri(BASE_URL)
        .build();

    log.info("Sending request to create folder with details: {}", requestBody);
    Response response = given()
        .accept(ContentType.JSON)
        .auth().preemptive().basic("guest@example.com","Password")
        .spec(spec)
        .body(requestBody)
        .post(CREATE_URI);

    return response
        .then()
        .statusCode(HttpStatus.SC_CREATED)
        .extract()
        .response();
  }

  public Response getEntryById(String id) {
    RequestSpecification spec = new RequestSpecBuilder()
        .setContentType(ContentType.JSON)
        .setBaseUri(BASE_URL)
        .build();

    log.info("Sending request to get entry with id {}..." , id);
    Response response = given()
        .auth().preemptive().basic("guest@example.com","Password")
        .spec(spec)
        .get(BASE_URL + "/" + id);

    return response
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .response();
  }

  public Response getEntries() {
    RequestSpecification spec = new RequestSpecBuilder()
        .setContentType(ContentType.JSON)
        .setBaseUri(BASE_URL)
        .build();

    log.info("Sending request to get entries");
    Response response = given()
        .auth().preemptive().basic("guest@example.com","Password")
        .spec(spec)
        .get(ENTRIES_URI);

    return response
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .response();
  }

  public Response deleteFolder(String id) {
    RequestSpecification spec = new RequestSpecBuilder()
        .setContentType(ContentType.JSON)
        .setBaseUri(BASE_URL)
        .build();

    log.info("Sending request to delete folder with id: {}", id);
    Response response = given()
        .accept(ContentType.JSON)
        .auth().preemptive().basic("guest@example.com","Password")
        .spec(spec)
        .delete(BASE_URL + "/" + id);

    return response
        .then()
        .statusCode(HttpStatus.SC_NO_CONTENT)
        .extract()
        .response();
  }
}
