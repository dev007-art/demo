package com.myself.demo.model;

import com.alibaba.fastjson.JSONObject;
import com.myself.demo.Utils.DemoConstants;

import java.util.UUID;

public class ReturnMessage {
    private ReturnMessage() {
    }

    public static JSONObject success(UUID uuid) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(DemoConstants.CODE, "1");
        jsonObject.put(DemoConstants.MESSAGE, "SUCCESS");
        jsonObject.put("UUID",uuid );
        return jsonObject;
    }
    public static JSONObject fail(String s){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(DemoConstants.CODE, "2");
        jsonObject.put(DemoConstants.MESSAGE, s);
        return jsonObject;
    }

    public static JSONObject createReturnMessage(String code, String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(DemoConstants.CODE, code);
        jsonObject.put(DemoConstants.MESSAGE, message);

        return jsonObject;
    }

    public static JSONObject createReturnMessage(String code, String message, Object data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(DemoConstants.CODE, code);
        jsonObject.put(DemoConstants.MESSAGE, message);
        jsonObject.put(DemoConstants.DATA, data);

        return jsonObject;
    }
}
