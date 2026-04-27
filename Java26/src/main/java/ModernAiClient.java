import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class ModernAiClient {
    private final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_3) // Direct HTTP/3 support
            .build();

    public static void main(String[] args) throws Exception {
        ModernAiClient agent = new ModernAiClient();
        agent.callApi();
        }

    public void callApi() throws Exception {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                .POST(HttpRequest.BodyPublishers.ofString("{}"))
                .build();

        //client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
          //      .thenAccept(res -> System.out.println("Status: " + res.statusCode()));

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(res -> System.out.println("Status: " + res.statusCode()))
                .join(); // Wait for the asynchronous operation to complete
    }
}