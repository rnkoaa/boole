'use strict';

//NavBarController
angular.module('booleApp')
    .controller('homeController', ['$scope', '$log', 'homeMovieService',
        function ($scope, $log, homeMovieService) {
            $scope.currentPage = 0;
            $scope.itemsPerPage = 21;
            var defaultSort = 'name';

            /*$scope.maxSize = 5;
            $scope.bigTotalItems = 175;
            $scope.bigCurrentPage = 1;*/

            $scope.movies = [];
            $scope.totalItems = 0;
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
                pendingTask = setTimeout(fetch, 10);
            };

            function fetch() {
                homeMovieService.findMovies($scope.currentPage, $scope.itemsPerPage)
                    .then(function (response) {
                        $scope.totalItems = response.meta.totalElements;
                        $scope.totalPages = response.meta.totalPages;

                        /* _.each(response.data, function (item) {
                         $scope.movies.push(item);
                         });*/
                        $scope.movies = response.data;
                        $scope.totalItems = response.meta.totalElements;
                    }, function (error) {
                        console.log(error);
                    });
            }

            $scope.pageChanged = function () {
                $log.log('Page changed to: ' + $scope.currentPage);
                $scope.change();
            };

        }]);