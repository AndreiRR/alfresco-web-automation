package api;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class NodesPrecondition {

  private NodesRequests nodesRequests = new NodesRequests();

  public Map<String, Object> getFolderDetails(String folderName) {
    Map<String, Object> folderDetails = new HashMap<>();

    folderDetails.put("name", folderName);
    folderDetails.put("nodeType", "cm:folder");

    return folderDetails;
  }

  public Response createFolderDetails() {
    Map<String, Object> folderDetails = getFolderDetails("AndreiRR");

    JSONObject requestBody = new JSONObject(folderDetails);
    return nodesRequests.createFolder(requestBody.toString());
  }

  public Response getEntryById(String id) {
    return nodesRequests.getEntryById(id);
  }

  public Response getEntries() {
    return nodesRequests.getEntries();
  }

  public void deleteFolder(String folderId) {
    nodesRequests.deleteFolder(folderId);
  }
}
