package org.example;

import lombok.Data;
import org.json.JSONObject;

@Data
public class Steps {
    String elementName;
    String desc;
    Integer interval;
    String url;
    String storeKey;
    String by;
    String pattern;
    String result;
    Integer tab;
    String frame;
    String key;
    String fileName;
    String module;

    public Steps(JSONObject step) {
        String command = step.getString("command");
        switch (command) {
            case "open" -> {
                this.module = "open_website";
                this.setOpenWebsite(step);
            }
            case "click" -> {
                this.module = "find_element_and_click";
                this.setBy(step);
                this.setElementName(step);
            }

            case "type" -> {
                this.module = "find_element_and_sendkey";
                this.setBy(step);
                this.setElementName(step);
                this.setKey(step);
            }

            case "runScript" -> {
                this.module = "webdriver_run_script";
                this.setRunScript(step);
                this.interval = 1;
            }

            case "mouseOver" -> {
                this.module = "find_element_and_hover";
                this.setBy(step);
                this.setElementName(step);
            }

            // case "setWindowSize" -> {
            //     this.module = "set_windows_size";
            //     this.setWindowsSize(step);
            // }
        }
    }

    private void setOpenWebsite(JSONObject step) {
        this.url = step.getString("target");
        this.desc = this.module;
        this.interval = 1;
    }

    // public void setWindowsSize(JSONObject step) {
    //     String target;
    //     target = step.getString("target");
    //     this.elementName = target;
    //     this.interval = 1;
    // }

    public void setRunScript(JSONObject step) {
        String target;
        target = step.getString("target");
        this.elementName = target;
        this.interval = 1;
    }

    private void setElementName(JSONObject step) {
        String target;
        target = step.getString("target");
        this.elementName = target.split(this.by+"=")[1];
        this.desc = step.optString("comment", this.module);
        this.interval = 1;
    }

    private void setBy(JSONObject step) {
        String target;
        target = step.getString("target");
        this.by = target.split("=")[0];
        this.desc = step.optString("comment", this.module);
        this.interval = 0;
    }

    private void setKey(JSONObject step) {
        this.key = step.getString("value");
        this.interval = 1;
    }
}