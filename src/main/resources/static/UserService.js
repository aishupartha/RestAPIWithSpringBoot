angular.module('demo', [])
.controller('UserService', function($scope, $http) {
/* The functions are defined for consuming the API and
setting the object in the scope to be retrived by the Html.
 The execriceTable is initialized as empty array and when size>0 then the table is rendered */
$scope.exercisesTable=[];
    $http.get('/userlist')
        .then(function(response) {
           $scope.users = response.data;
        });

    $scope.getExercises = function(id) {
        $http.get('/user/'+ id +'/exercise')
                .then(function(response) {
                    $scope.exercises = response.data;
                     $scope.exercisesTable = response.data;
                     $scope.userid = id;
                });
    };
    $scope.getData = function(exerciseid) {
     $http.get('/user/'+  $scope.userid +'/exercise/'+exerciseid)
                    .then(function(response) {
                        console.log('response', response.data);
                         $scope.exercisesTable = response.data;
                    });
        };
});
