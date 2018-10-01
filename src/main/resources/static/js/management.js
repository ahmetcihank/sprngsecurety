/**
 * Created by SOFTWARE02 on 01.09.2018.
 */

var app = angular.module("myApp", ["ngRoute"]);


app.config(function($routeProvider) {
    $routeProvider

        .when("/", {
            templateUrl : '/main',
        }).
    when("/map",{
        templateUrl:'/map'
    });
});


app.controller('managementController', function($scope, $http, managerService,employeeService) {
     var globmanagers ="";
    $http({
        method: "GET",
        url: "http://localhost:7373/Managers"

    }).then(function mySuccess(response) {
        $scope.managers = response.data;
        managers = response.data;
        console.log(managers);


    }, function myError(response) {
        $scope.managers = response.statusText;

    });


    $scope.selectManager= function (mid) {
       var foundManager =managers.find(function (element) {
             return element.id === mid;
         });

        employeeService.addEmployee(foundManager.employees);

    };


    $scope.saveManagers = function () {

        var name = document.getElementById("managerName").value;
        var position = document.getElementById("managerPosition").value;
        var lastname = "Hell Yeah";

        $http({
            method: "GET",
            url: "http://localhost:7373/saveManager",
            params: {'name': name, 'position': position, 'lastname': lastname}
        }).then(function mSuccess(responseData) {
            $scope.myWelcome = responseData.data;
            managerService.addManagers(responseData.data);
            var elmtTable = document.getElementById('mangerTable');
            var tableRows = elmtTable.getElementsByTagName('tr');
            var rowCount = tableRows.length;

            for (var x = rowCount - 1; x > 0; x--) {
                elmtTable.deleteRow(x);
            }

            window.setTimeout(function () {
                $(".alert").fadeTo(500, 0).slideUp(500, function () {
                    $(this).remove();
                });
            }, 4000);
        },function myError(response) {
            $scope.myWelcome = response.statusText;
        });

    };

    $scope.deleteManagers = function (id) {
        $http({
            method: "GET",
            url: "http://localhost:7373/deleteManager",
            params: {'id': id}
        }).then(function mSuccess(responseData) {
            $scope.myWelcome = responseData.data;
            managerService.addManagers(responseData.data);
            var elmtTable = document.getElementById('mangerTable');
            var tableRows = elmtTable.getElementsByTagName('tr');
            var rowCount = tableRows.length;

            for (var x = rowCount - 1; x > 0; x--) {
                elmtTable.deleteRow(x);
            }

            window.setTimeout(function () {
                $(".alert").fadeTo(500, 0).slideUp(500, function () {
                    $(this).remove();
                });
            }, 2000);
        },function myError(response) {
            $scope.myWelcome = response.statusText;
        });
    }

    $scope.$watch(function () { return managerService.getManagers();   }, function (value) {
        $scope.managers = value;
    });

    $scope.$watch(function () { return employeeService.getEmployee();   }, function (value) {
        $scope.employees = value;
    });


});



app.service('managerService', function() {
    var managers = [];

    var addManagers = function(newlist) {
        managers = newlist;
    };

    var getManagers = function(){
        return managers;
    };

    return {
        addManagers: addManagers,
        getManagers: getManagers,
    };
});



app.service('employeeService', function () {

    var employees = {};

    var addEmployee = function(newlist) {
        employees = newlist;
    };

    var getEmployee = function(){
        return employees;
    };

    return {
        addEmployee: addEmployee,
        getEmployee: getEmployee,
    };

});

///Map Page Section


app.controller('mapController', function($scope, $http) {
    var vehicles ="";
    var managers ="";
    var selectedManager= "";
    var map;
    $http({
        method: "GET",
        url: "http://localhost:7373/Managers"

    }).then(function mySuccess(response) {
        $scope.managers = response.data;
        managers = response.data;
        console.log(managers);


    }, function myError(response) {
        $scope.managers = response.statusText;

    });

    $scope.createPushpin = function (lat, lon) {
     var location = new Microsoft.Maps.Location(lat, lon);
     var pushpin =new Microsoft.Maps.Pushpin(location,null);
     map.entities.push(pushpin);

        map.setView({
            center: new Microsoft.Maps.Location(lat, lon),
            zoom: 5
        });
    };


    $scope.SelectManager= function (name) {

        alert("Clicked MAte!");
        selectedManager=name;

        $http({
            method:"GET",
            url:"http://localhost:7373/getVeh",
            params: {observer: name},
        }).then(function msuccess(response) {
            $scope.vehicles = response.data;
            vehicles=response.data;
            console.log(vehicles);
        });

    };


    var init = function () {

         setTimeout(function () {

             map = new Microsoft.Maps.Map(document.getElementById('myMap'), {});
         },1000)
    };

 //Deneme

    init();

});
























