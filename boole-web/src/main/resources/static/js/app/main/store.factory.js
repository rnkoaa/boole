'use strict';

angular.module('booleStoreApp')
    .factory('storeFactory', ['$http', '$q', function ($http, $q) {
        var storeFactory = {};
        storeFactory.findAll = function () {
            var defer = $q.defer();
            $http.get("/boole/api/stores")
                .success(function (response) {
                    defer.resolve(response);
                })
                .error(function (error) {
                    console.log(response);
                    defer.reject("An error occurred while retrieving stores");
                });
            return defer.promise;
        };

        storeFactory.findOne = function (storeId) {
            var defer = $q.defer();
            $http.get("/boole/api/stores/" + storeId)
                .success(function (response) {
                    console.log(response);
                    defer.resolve(response);
                })
                .error(function (error) {
                    console.log(response);
                    defer.reject("An error occurred while retrieving store with Id: " + storeId);
                });
            return defer.promise;
        };

        storeFactory.findCustomers = function (storeId) {
            var defer = $q.defer();
            $http.get("/boole/api/stores/" + storeId + "/customers")
                .success(function (response) {
                    console.log(response);
                    defer.resolve(response);
                })
                .error(function (error) {
                    console.log(response);
                    defer.reject("An error occurred while retrieving store with Id: " + storeId);
                });
            return defer.promise;
        };

        return storeFactory;

    }]);