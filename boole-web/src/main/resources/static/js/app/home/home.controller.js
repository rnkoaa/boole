'use strict';

//NavBarController
angular.module('booleApp')
    .controller('homeController', ['$scope', '$log', 'homeMovieService',
        function ($scope, $log, homeMovieService) {
            var currentPage = 0;
            var itemsPerPage = 42;
            var defaultSort = 'name';

            $scope.maxSize = 5;
            $scope.bigTotalItems = 175;
            $scope.bigCurrentPage = 1;

            $scope.movies = [];
            //$scope.totalItems = 64;
            $scope.currentPage = 1;

            $scope.setPage = function (pageNo) {
                $scope.currentPage = pageNo;
            };

            fetch();

            var pendingTask;

            $scope.change = function () {
                if (pendingTask) {
                    clearTimeout(pendingTask);
                }
                pendingTask = setTimeout(fetch, 800);
            };

            function fetch() {
                homeMovieService.findMovies(currentPage, itemsPerPage)
                    .then(function (response) {
                        //console.log(response);
                        _.each(response.data, function (item) {
                            console.log(item);
                            $scope.movies.push(item);
                        });
                        $scope.totalItems = response.meta.totalElements;
                        console.log($scope.movies.length);
                    }, function (error) {
                        console.log(error);
                    });
            }

            /*
             $scope.update = function(movie){
             $scope.search = movie.Title;
             $scope.change();
             };*/

            $scope.pageChanged = function () {
                $log.log('Page changed to: ' + $scope.currentPage);
            };


            // $scope.apply();
            // console.log($scope.movieData.length);

        }]);