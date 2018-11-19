package com.codingchallenge.RestFulApi.controller;

import com.codingchallenge.RestFulApi.executor.DataAPIExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class DataAPIController {
    @Autowired
    DataAPIExecutor service;
    @RequestMapping(value="/userlist",method= RequestMethod.GET,produces={"application/json"})
    public ResponseEntity getUsersList(HttpServletRequest request){
        return service.getUserList();
    }
    @RequestMapping(value="/user/{user_id}/exercise",method= RequestMethod.GET,produces={"application/json"})
    public ResponseEntity getExercise(HttpServletRequest request, @PathVariable("user_id")int userId){
        return service.getExerciseList(userId,0);
    }
    @RequestMapping(value="/user/{user_id}/exercise/{exercise_id}",method= RequestMethod.GET,produces={"application/json"})
    public ResponseEntity getExerciseWithId(HttpServletRequest request, @PathVariable("user_id")int userId,@PathVariable("exercise_id")int exerciseId){
        return service.getExerciseList(userId,exerciseId);
    }
}
