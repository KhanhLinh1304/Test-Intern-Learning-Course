package com.example.learning_intern.payload.response;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseFormat {
    public ResponseEntity<APIResponse> success(Object object){
        return ResponseEntity.ok().body(new APIResponse(1,200, object,"ok" ));
    }
    public ResponseEntity<APIResponse> badRequest(String message){
        return ResponseEntity.status(400).body(new APIResponse(0,400,null,message));
    }
    public ResponseEntity<APIResponse> notFound(String message){
        return ResponseEntity.status(404).body(new APIResponse(0, 404, null, message));
    }
    public ResponseEntity<APIResponse> confictRequest(String message){
        return ResponseEntity.status(407).body(new APIResponse(0,407, null, message));
    }

    public ResponseEntity<APIResponse> badCredential(String message){
        return ResponseEntity.status(401).body(new APIResponse(0,401, null, message));
    }
}
