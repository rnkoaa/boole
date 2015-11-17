'use strict';

//NavBarController
angular.module('booleApp')
    .factory('homeMovieService', ['$http', '$q', function ($http, $q) {
        var homeMovieService = {};
        homeMovieService.findMovies = function (currentPage, itemsPerPage) {
            var defer = $q.defer();

            $http.get("/boole/api/movies?page=" + currentPage + "&limit=" + itemsPerPage)
                .success(function (response) {
                    console.log(response);
                    defer.resolve(response);
                }).error(function (err) {
                    console.log(response);
                    defer.reject(err);
                });
            return defer.promise;
        };

        return homeMovieService;
    }]);