package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            Task task = new Task(args[0]);
            TestPlansAutomation tpa = new TestPlansAutomation(args[1], args[2]);
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