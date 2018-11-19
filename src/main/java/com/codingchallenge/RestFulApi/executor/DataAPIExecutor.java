package com.codingchallenge.RestFulApi.executor;


import com.codingchallenge.RestFulApi.model.Exercises;
import com.codingchallenge.RestFulApi.model.Users;
import com.codingchallenge.RestFulApi.repository.MetadataScript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
/*The service class is where the business logic and also repository call is made to fetch the data*/
public class DataAPIExecutor {
    @Autowired
    MetadataScript reporsitory;
    /*Retrieves the List of users with the model object Users */
    public List<Users> getUserList(){
        return reporsitory.getUserList();
    }

    /*Retrieves the List of Exercise with the model object Exercises */
    public List<Exercises> getExerciseList(int userId,int exerciseId){
        return reporsitory.getExerciseList(userId,exerciseId);

    }
}
