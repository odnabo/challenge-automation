package challenge.automation.testing.util;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class UnirestUtils {

	public static HttpResponse<JsonNode> getDepartments() {
		return Unirest.get(Constants.URL_SERVICE_CHALLENGE)
				.header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
				.asJson();
	}
}