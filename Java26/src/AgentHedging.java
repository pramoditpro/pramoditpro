import java.time.Duration;
import java.util.concurrent.StructuredTaskScope;

public class AgentHedging {

    public static void main(String[] args) throws Exception {
        AgentHedging agent = new AgentHedging();
        String result = agent.getFirstAiAnswer("Hello");
        System.out.println("Answer is : "+result);
    }
    public String getFirstAiAnswer(String prompt) throws Exception {
        // 'anySuccessfulOrThrow' cancels other tasks as soon as ONE succeeds.
        try (var scope = StructuredTaskScope.open(
                StructuredTaskScope.Joiner.<String>anySuccessfulOrThrow(),
                cfg -> cfg.withTimeout(Duration.ofSeconds(5)) // New in Java 26 config
        )) {
            // Forking three parallel AI calls
            scope.fork(() -> callLlm("GPT-5", prompt));
            scope.fork(() -> callLlm("Claude-4", prompt));
            scope.fork(() -> callLlm("Gemini-2", prompt));

            // join() now returns the winning result directly in Java 26
            return scope.join();
        }
    }

    private String callLlm(String model, String p) throws InterruptedException {
        Thread.sleep((long) (Math.random() * 2000)); // Simulate network lag
        return "Response from " + model;
    }
}