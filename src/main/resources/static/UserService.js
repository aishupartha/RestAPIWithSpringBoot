angular.module('demo', [])
.controller('UserService', function($scope, $http) {
/**/
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
