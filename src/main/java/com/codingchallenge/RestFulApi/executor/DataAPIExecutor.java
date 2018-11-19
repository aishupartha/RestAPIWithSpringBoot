package com.codingchallenge.RestFulApi.executor;

import com.codingchallenge.RestFulApi.exception.ErrorMessage;
import com.codingchallenge.RestFulApi.repository.MetadataScript;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class DataAPIExecutor {
    @Autowired
    MetadataScript reporsitory;
    public static Gson gson=new Gson();

    public ResponseEntity getUserList(){
        String userList=gson.toJson(reporsitory.getUserList());
        HttpStatus status=HttpStatus.OK;
        if(userList==null || userList.isEmpty()) {
            HashMap<String,String> errorMessage=new HashMap<String,String>();
            errorMessage.put("ERROR_MESSAGE",ErrorMessage.ERR_USER_LIST);
            userList = gson.toJson(errorMessage);
            status=HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity(userList, status);
    }

    public ResponseEntity getExerciseList(int userId,int exerciseId){
        HttpStatus status=HttpStatus.OK;
        if(userId==0){
            new ResponseEntity(ErrorMessage.ERR_USER_NOTAVAILABLE,HttpStatus.NO_CONTENT);
        }
        String exerciseList=gson.toJson(reporsitory.getExerciseList(userId,exerciseId));
        if(exerciseList==null || exerciseList.isEmpty()){
            HashMap<String,String> errorMessage=new HashMap<String,String>();
            errorMessage.put("ERROR_MESSAGE",ErrorMessage.ERR_EXERCISE_LIST);
            exerciseList= gson.toJson(errorMessage);
            status=HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity(exerciseList, status);
    }
}
