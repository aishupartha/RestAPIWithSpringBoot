package com.codingchallenge.RestFulApi.controller;

import com.codingchallenge.RestFulApi.exception.ErrorMessage;
import com.codingchallenge.RestFulApi.executor.DataAPIExecutor;
import com.codingchallenge.RestFulApi.model.Exercises;
import com.codingchallenge.RestFulApi.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
public class DataAPIController {
    /*Controller is the gateway to the API .The Controller is responsible for controlling the application logic and acts as the coordinator between the View and the Model.
     The Controller receives an input from the users via the View, then processes the user's data with the help of Model and passes the results back to the View.*/
    @Autowired
    DataAPIExecutor service;

    /* API 1: Get the list of users . The response is converted into Json object  */
    @RequestMapping(value="/userlist",method= RequestMethod.GET,produces={"application/json"})
    public ResponseEntity getUsersList(HttpServletRequest request){
        List<Users> userList=service.getUserList();
        if(userList.size()>0){
           return  new ResponseEntity<>(userList, HttpStatus.OK);
        }
        /*If there is no users  the API returns code 204 no content which means the response is empty */
        else{
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    /* API 2: Get the list of exercises for a user with user_id as path variable . Here the user_id can not be null.
     If its is null then the url is compromised and resource is not found .
    The response is converted into Json object and returns all the exercise for a user with the score and count*/
    @RequestMapping(value="/user/{user_id}/exercise",method= RequestMethod.GET,produces={"application/json"})
    public ResponseEntity getExercise(HttpServletRequest request, @PathVariable("user_id")int userId){

        List<Exercises> exerciseList=service.getExerciseList(userId,0);

        if(exerciseList.size()>0){
            return  new ResponseEntity<>(exerciseList, HttpStatus.OK);
        }
        /*If there is no exercise for a given user this returns code 204 no content which means the response is empty */
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    /*API 3: Get the list of exercises and scores for a selected user and selected exercise. The user_id and exercise_id are the path varaibles
     * The response object contains the list of exercises with score for a selected user and exercise */
    @RequestMapping(value="/user/{user_id}/exercise/{exercise_id}",method= RequestMethod.GET,produces={"application/json"})
    public ResponseEntity getExerciseWithId(HttpServletRequest request, @PathVariable("user_id")int userId,@PathVariable("exercise_id")int exerciseId){

        List<Exercises> exerciseList=service.getExerciseList(userId,exerciseId);
        if(exerciseList.size()>0){
            return  new ResponseEntity<>(exerciseList, HttpStatus.OK);
        }
        /*If there is no exercises and score for the selected user and exercise  the API returns code 204 no content which means the response is empty */
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }
}
