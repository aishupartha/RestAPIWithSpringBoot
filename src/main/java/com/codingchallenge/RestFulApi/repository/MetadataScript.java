package com.codingchallenge.RestFulApi.repository;

import com.codingchallenge.RestFulApi.model.Exercises;
import com.codingchallenge.RestFulApi.model.Users;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
/* The Database connection is obtained in the Dao or the repository class and also the query is executed and the model object is being returned as output*/
public class MetadataScript {
    public static final String host="localhost:5432/intuitivesurgical";
    public static final String username="aishwarya";
    public static final String password="intuitive";
    public static final String usersList= " SELECT id as Id ,last_name as Name FROM Users ";
    public static final String exerciseList= " SELECT  u.last_name as UserName, u.id as UserId,e.id as ExerciseId ,e.name as Exercise,s.score as Score ," +
            " ROW_NUMBER () OVER ( partition BY e.name Order BY s.score ) as Count From Score s " +
            " INNER JOIN users u on s.user_id=u.id " +
            " INNER JOIN exercise e on s.exercise_id=e.id "+
            " WHERE u.id= %d ";

    /*The connection details is a static method which establishes the driver manager connection using the given host name,username and password
    * It executes the given query and returns the ResultSet.
    * This avoids the multiple times the connection needs to be procured*/
    public static ResultSet connectionDetails(String query){
        ResultSet resultSet=null;
        try{
             Connection connection = DriverManager.getConnection("jdbc:postgresql://"+host, username, password);
            Statement statement = connection.createStatement();
             resultSet = statement.executeQuery(query);
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return resultSet;
    }
    /*Once the database connection is made usersList query is executed and the results are stored in the model object Users
    * This methods returns List<Users> */
    public List<Users> getUserList(){
        List<Users> users=new ArrayList<Users>();
        Users user=null;
        try {
            ResultSet result = connectionDetails(usersList);
            while (result.next()) {
                user=new Users();
                user.setId( result.getInt("Id"));
                user.setName(result.getString("Name"));
                users.add(user);
            }
        }
             catch (SQLException e) {
                e.printStackTrace();

            }
            catch (Exception e) {
                  e.printStackTrace();

             }
        return users;
    }
    /*getExerciseList uses user_id , exerciseid as input parameters.
    If exercise id is 0 or null then the exercise is fetched for a selected user.
    If both are valid then exercise is generated for selected user and exercise.
     The return object is Exercise model*/
    public List<Exercises>getExerciseList(int user_id ,int exerciseid ){
        List<Exercises> exercises=new ArrayList<Exercises>();
        Exercises exercise=null;
        try{

           String query=String.format(exerciseList,user_id);
           if(exerciseid!=0){
               query=query+ " AND e.id="+exerciseid;
           }
            ResultSet result = connectionDetails(query);

            while (result.next()) {
                exercise=new Exercises();
                exercise.setUserName(result.getString("UserName"));
                exercise.setExercise(result.getString("Exercise"));
                exercise.setExerciseId(result.getInt("ExerciseId"));
                exercise.setUserId(result.getInt("UserId"));
                exercise.setCount(result.getInt("Count"));
                exercise.setScore(result.getDouble("Score"));
                exercises.add(exercise);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        catch (Exception e) {
            e.printStackTrace();

        }
        return exercises;
    }
}
