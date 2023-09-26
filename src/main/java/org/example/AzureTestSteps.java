package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;

public class AzureTestSteps {
    List<String> objectList = new ArrayList<>();
    JSONObject updateTitle = new JSONObject();
    JSONObject updateValue = new JSONObject();

    public AzureTestSteps(Task task) {
        this.updateTitle.put("op", "add");
        this.updateTitle.put("path", "/fields/System.Title");
        this.updateTitle.put("from", Optional.ofNullable(null));
        this.updateTitle.put("value", task.GetTaskName());
        this.objectList.add(updateTitle.toString());
        this.updateValue.put("op", "add");
        this.updateValue.put("path", "/fields/Microsoft.VSTS.TCM.Parameters");
        this.updateValue.put("from", Optional.ofNullable(null));
        this.updateValue.put("value", "%s");
        this.objectList.add(updateValue.toString());
    }

    public String GetAzureSharedParameters(Task task) {
        return this.objectList.toString().formatted(task.GetSharedParameters());
    }
}