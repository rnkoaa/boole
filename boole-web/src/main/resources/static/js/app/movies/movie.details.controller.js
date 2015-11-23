'use strict';

//NavBarController
angular.module('booleApp')
    .controller('movieDetailController', ['$scope', '$log', '$state', '$stateParams',
        'movieService',
        function ($scope, $log, $state, $stateParams, movieService) {
            $scope.movieId = parseInt($stateParams.movieId, 10);

            fetchMovie($scope.movieId);

            function fetchMovie(movieId) {
                console.log("Fetching Movie with Id: ", $scope.movieId);
                movieService.findOneMovieWithDetails(movieId)
                    .then(function (response) {
                        $scope.movie = response.data;
                    }, function (error) {
                        console.log(error);
                    });
            }

        }]);