'use strict';

//NavBarController
angular.module('booleApp')
    .controller('discoverController', ['$scope', '$log', '$state', '$stateParams', '$location',
        'homeMovieService',
        function ($scope, $log, $state, $stateParams, $location, homeMovieService) {
            $scope.totalPages = 0;
            $scope.selectedPage = parseInt($stateParams.page, 10);
            $scope.itemsPerPage = parseInt($stateParams.limit, 10);
            $scope.ngDisabled = false;

            if ($scope.selectedPage === 0) {
                $scope.selectedPage = $scope.selectedPage + 1;
            }
            var defaultSort = 'name';


            $scope.movies = [];
            $scope.totalItems = 0;

            fetch();

            var pendingTask;

            $scope.change = function () {
                if (pendingTask) {
                    clearTimeout(pendingTask);
                }
                pendingTask = setTimeout(fetch, 10);
            };

            function fetch() {

                homeMovieService.findMovies($scope.selectedPage, $scope.itemsPerPage)
                    .then(function (response) {
                        $scope.totalItems = response.meta.totalElements;
                        $scope.totalPages = response.meta.totalPages;
                        $scope.movies = response.data;
                    }, function (error) {
                        console.log(error);
                    });
            }

            $scope.previousPage = function () {
                $log.log("go to previous page");
                $state.go('.', {page: $scope.selectedPage - 1});
            };

            $scope.nextPage = function () {
                $log.log("go to NextPage page");
                $state.go('.', {page: $scope.selectedPage + 1});
            };

            $scope.noPrevious = function () {
                return $scope.selectedPage <= 1;
            };

            $scope.noNext = function () {
                return $scope.selectedPage >= $scope.totalPages;
            };
        }]);