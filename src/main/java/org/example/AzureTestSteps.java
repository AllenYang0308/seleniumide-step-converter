package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;

public class AzureTestSteps {
    // List<JSONObject> objectList = new ArrayList<>();
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

        // String headerStr = "<parameterSet><paramNames><param>elementName<\\\\/param><param>desc<\\\\/param><param>interval<\\\\/param><param>module<\\\\/param><param>url<\\\\/param><param>by<\\\\/param><param>key<\\\\/param><param>frame<\\\\/param><param>pattern<\\\\/param><param>NL<\\\\/param><\\\\/paramNames><paramData lastId=\"14\"><dataRow id=\"1\">";
        // String tailStr = "<\\\\/dataRow><\\\\/paramData><\\\\/parameterSet>";
        // String paramValue = task.GetSharedParameters();

        // this.updateValue.put("value", task.GetSharedParameters());

        // this.updateValue.put("value", paramValue);
        this.updateValue.put("value", "%s");

        this.objectList.add(updateValue.toString());
    }

    public String GetAzureSharedParameters(Task task) {
        // String headerStr = "<parameterSet><paramNames><param>elementName</param><param>desc</param><param>interval</param><param>module</param><param>url</param><param>by</param><param>key</param><param>frame</param><param>pattern</param><param>NL</param></paramNames><paramData lastId=\\\"14\\\"><dataRow id=\\\"1\\\">";
        // String tailStr = "</dataRow></paramData></parameterSet>";
        // String paramValue = headerStr+task.GetSharedParameters()+tailStr;
        // return this.objectList.toString().formatted(paramValue);

        return this.objectList.toString().formatted(task.GetSharedParameters());
    }
}