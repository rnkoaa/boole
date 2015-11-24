'use strict';

angular.module('booleApp')
    .factory('movieService', ['$http', '$q', function ($http, $q) {
        var movieService = {};
        movieService.findMovies = function (currentPage, itemsPerPage) {
            var defer = $q.defer();

            /**
             * Find all movies with paging associated
             */
            $http.get("/api/movies?page=" + currentPage + "&limit=" + itemsPerPage + "&include=genres")
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

            $http.get("/api/movies/" + movieId + "?include=details")
                .success(function (response) {
                    defer.resolve(response);
                }).error(function (err) {
                console.log(response);
                defer.reject(err);
            });
            return defer.promise;
        };

        return movieService;
    }]).factory('LoadingInterceptor', ['$q', '$rootScope', '$log', function ($q, $rootScope, $log) {
    var xhrCreations = 0;
    var xhrResolutions = 0;

    function isLoading() {
        return xhrResolutions < xhrCreations;
    }

    function updateStatus() {
        $rootScope.loading = isLoading();
    }

    return {
        request: function (config) {
            xhrCreations++;
            updateStatus();
            return config;
        },
        requestError: function (rejection) {
            xhrResolutions++;
            updateStatus();
            $log.error('Request error:', rejection);
            return $q.reject(rejection);
        },
        response: function (response) {
            xhrResolutions++;
            updateStatus();
            return response;
        },
        responseError: function (rejection) {
            xhrResolutions++;
            updateStatus();
            $log.error('Response error:', rejection);
            return $q.reject(rejection);
        }
    };
}]);