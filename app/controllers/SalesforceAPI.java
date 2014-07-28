package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.F;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;

public class SalesforceAPI {

    private static final String API_VERSION = "31.0";

    private static final String BASE_URL = "/services/data/v" + API_VERSION + "/";

    private static final String QUERY_URL = BASE_URL + "query/";

    public static F.Promise<JsonNode> query(String apiKey, String instanceUrl, String query) {
        return WS.url(instanceUrl + QUERY_URL)
                .setHeader("Authorization", "Bearer " + apiKey)
                .setQueryParameter("q", query)
                .get()
                .map(new F.Function<WSResponse, JsonNode>() {
            @Override
            public JsonNode apply(WSResponse wsResponse) throws Throwable {
                return wsResponse.asJson();
            }
        });
    }

}
