'use strict';

angular.module('booleApp')
    .factory('movieService', ['$http', '$q', function ($http, $q) {
        var movieService = {};
        movieService.findMovies = function (currentPage, itemsPerPage) {
            var defer = $q.defer();

            /**
             * Find all movies with paging associated
             */
            $http.get("/boole/api/movies?page=" + currentPage + "&limit=" + itemsPerPage + "&include=genres")
                .success(function (response) {
                    defer.resolve(response);
                }).error(function (err) {
                    console.log(response);
                    defer.reject(err);
                });
            return defer.promise;
        };

        /**
         * Find a single movie using the movie id
         * @param movieId
         * @returns {*}
         */
        movieService.findOneMovieWithDetails = function (movieId) {
            var defer = $q.defer();

            $http.get("/boole/api/movies/" + movieId + "?include=details")
                .success(function (response) {
                    defer.resolve(response);
                }).error(function (err) {
                    console.log(response);
                    defer.reject(err);
                });
            return defer.promise;
        };

        return movieService;
    }]);