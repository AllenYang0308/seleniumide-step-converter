// package org.example;
//
// import okhttp3.*;
//
// import java.io.IOException;
// import java.util.Arrays;
//
// public class Main {
//     public static void main(String[] args) {
//         // Azure DevOps API URL
//         Task task = new Task(args[0]);
//         String baseUrl = "https://dev.azure.com/ccxnyang/UITest/_apis/wit/workitems/";
//         String parameter = "${Shared%20Parameter}";
//         String apiVersion = "?api-version=6.0";
//         String apiUrl = baseUrl + parameter + apiVersion;
//
//         String username = "ccxn.yang@gmail.com";
//         String password = "t3qlgdmdz3semqkk4zhe4tave4l6x45cv2pqzeclv34dsugu4siq";
//         String credentials = username+":"+password;
//         String base64Credentials = okhttp3.Credentials.basic(username, password);
//         System.out.println(base64Credentials);
//         System.out.println(credentials);
//
//
//         // 请求体数据（JSON数据）
//         String requestBody = new AzureTestSteps(task).GetAzureSharedParameters(task);
//         byte[] requestBodyByte = requestBody.getBytes();
//         System.out.println(Arrays.toString(requestBodyByte));
//         System.out.println(requestBody);
//
//         // 创建OkHttpClient
//         // OkHttpClient client = new OkHttpClient.Builder();
//         OkHttpClient client = new OkHttpClient();
//         // 创建请求体
//         // RequestBody body = RequestBody.create(MediaType.parse("application/json-patch+json; harset=utf-8"), requestBody);
//         RequestBody body = RequestBody.create(requestBodyByte, MediaType.parse("application/json-patch+json; harset=utf-8"));
//         // 创建POST请求
//         Request request = new Request.Builder()
//                 .url(apiUrl)
//                 .header("Authorization", base64Credentials)
//                 .post(body)
//                 .build();
//
//         // 发送请求并处理响应
//         try {
//             Response response = client.newCall(request).execute();
//             if (response.isSuccessful()) {
//                 String responseBody = response.body().string();
//                 System.out.println("Response: " + responseBody);
//             } else {
//                 System.out.println("Request failed with response code: " + response.code());
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }




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