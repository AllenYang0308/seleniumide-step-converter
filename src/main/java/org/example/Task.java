package org.example;

import org.json.JSONArray;
import org.json.JSONTokener;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;

public class Task {

    List<Steps> task = new ArrayList<>();
    JSONObject jsonObject;

    public Task(String stepFile) {
        try {
            JSONTokener tokener = new JSONTokener(
                    new FileReader(
                            stepFile
                    )
            );
            jsonObject = new JSONObject(tokener);
            JSONArray commandsObjectArray = jsonObject.getJSONArray(
                    "tests"
            ).getJSONObject(
                    0
            ).getJSONArray(
                    "commands"
            );
            this.addStep(commandsObjectArray);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String GetTaskName() {
        return this.jsonObject.getString("name");
    }

    public void addStep(JSONArray jsonArray) {

        for (Integer i=0; i < jsonArray.length(); i++) {
            this.task.add(
                    new Steps(jsonArray.getJSONObject(i))
            );
        }
    }

    public List<Steps> getTask() {
        return this.task;
    }

    public String GetSharedParameters() {
        List<String> tmp = new ArrayList<>();
        String pattern = "<kvp key=\"%s\" value=\"%s\"/>";
        String result;
        for (Steps step: this.getTask()) {
            if (step.getElementName() != null) {
                String elementName = step.getElementName();
                elementName = elementName.replace("\"", "&amp;quot;");
                elementName = elementName.replace(">", "&amp;gt;");
                elementName = elementName.replace("<", "&amp;lt;");
                tmp.add(pattern.formatted("elementName", elementName));
            }
            // if (step.getElementName() != null) { tmp.add(pattern.formatted("elementName", step.getElementName()));}
            if (step.getDesc() != null) { tmp.add(pattern.formatted("desc", step.getDesc()));}
            if (step.getInterval() != null) { tmp.add(pattern.formatted("interval", step.getInterval()));}
            if (step.getUrl() != null) { tmp.add(pattern.formatted("url", step.getUrl()));}
            if (step.getBy() != null) { tmp.add(pattern.formatted("by", step.getBy()));}
            if (step.getKey() != null) { tmp.add(pattern.formatted("key", step.getKey()));}
            if (step.getFrame() != null) { tmp.add(pattern.formatted("frame", step.getFrame()));}
            if (step.getPattern() != null) { tmp.add(pattern.formatted("pattern", step.getPattern()));}
            if (step.getModule() != null) {
                tmp.add(pattern.formatted("module", step.getModule()));
                tmp.add(pattern.formatted("NL", "/"));
            }
        }
        String headerStr = "<parameterSet><paramNames><param>elementName</param><param>desc</param><param>interval</param><param>module</param><param>url</param><param>by</param><param>key</param><param>frame</param><param>pattern</param><param>NL</param></paramNames><paramData lastId=\"14\"><dataRow id=\"1\">";
        String tailStr = "</dataRow></paramData></parameterSet>";
        result = String.join("", tmp);
        return result;
    }
}
