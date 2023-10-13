package org.example;
import io.github.cdimascio.dotenv.Dotenv;
public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().filename("env").load();
        try {
            Task task = new Task(args[0]);
            TestPlansAutomation tpa = new TestPlansAutomation(dotenv.get("AzureApisDomain"), args[1], args[2]);
            String rsp = tpa.CreateSharedParameters(
                    new AzureTestSteps(task).GetAzureSharedParameters(task)
            );
            System.out.println(rsp);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }
}