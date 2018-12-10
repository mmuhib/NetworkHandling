package com.example.muhib.task2_networking;

import org.json.JSONObject;

public interface ResultInterface {
        void OnSuccess(JSONObject result, String type);
        void OnFailure(String responseMessage);
}
