'use strict';

//NavBarController
angular.module('booleApp')
    .controller('homeController', ['$scope', '$log', 'movieService',
        function ($scope, $log, movieService) {
            $scope.currentPage = 0;
            $scope.itemsPerPage = 21;
            var defaultSort = 'name';

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
                movieService.findMovies($scope.currentPage, $scope.itemsPerPage)
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