'use strict';

//NavBarController
angular.module('booleApp')
    .controller('discoverController',
    ['$scope', '$log', '$state', '$stateParams', '$location', 'movieService',
        function ($scope, $log, $state, $stateParams, $location, movieService) {
            $scope.totalPages = 0;
            $scope.cachedMovies = [];
            $scope.selectedPage = parseInt($stateParams.page, 10);
            $scope.itemsPerPage = parseInt($stateParams.limit, 10);
            $scope.ngDisabled = false;

            if ($scope.selectedPage == undefined || isNaN($scope.selectedPage)) {
                $scope.selectedPage = 1;
            }
            if ($scope.itemsPerPage == undefined || isNaN($scope.itemsPerPage)) {
                $scope.itemsPerPage = 21;
            }
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
                movieService.findMovies($scope.selectedPage, $scope.itemsPerPage)
                    .then(function (response) {
                        $scope.totalItems = response.meta.totalElements;
                        $scope.totalPages = response.meta.totalPages;
                        $scope.movies = response.data;
                    }, function (error) {
                        console.log(error);
                    });
            }

            $scope.previousPage = function () {
                $state.go('.', {page: prevPage});
            };

            $scope.nextPage = function () {
                $state.go('.', {page: $scope.selectedPage + 1});
            };

            $scope.noPrevious = function () {
                return $scope.selectedPage <= 1;
            };

            $scope.noNext = function () {
                return $scope.selectedPage >= $scope.totalPages;
            };
        }]);