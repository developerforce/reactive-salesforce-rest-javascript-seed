var app = angular.module("ngApp", []);

app.controller('Contacts', function($scope, $http) {

  $scope.initialized = false;

  $scope.loggedIn = false;

  $scope.contacts = [];

  $scope.getContacts = function() {
    $http.get("/contacts").
      success(function(data, status, headers, config) {
        $scope.initialized = true;
        $scope.loggedIn = true;
        $scope.contacts = data.records;
      }).
      error(function() {
        $scope.initialized = true;
      });
  };

  $scope.getContacts();

});