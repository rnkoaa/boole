'use strict';

//NavBarController
angular.module('booleApp')
    .controller('discoverController', ['$scope', '$log', '$state', '$stateParams', '$location',
        'movieService',
        function ($scope, $log, $state, $stateParams, $location, movieService) {
            $scope.totalPages = 0;
            $scope.cachedMovies = [];
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

                movieService.findMovies($scope.selectedPage, $scope.itemsPerPage)
                    .then(function (response) {
                        $scope.totalItems = response.meta.totalElements;
                        $scope.totalPages = response.meta.totalPages;
                        $scope.movies = response.data;

                        //put it into the cache
                      /*  _.each(response.data, function (item) {
                            $scope.cachedMovies.push(item);
                        });*/
                    }, function (error) {
                        console.log(error);
                    });
            }

            $scope.previousPage = function () {
                $log.log("go to previous page");
                /*//if there is data for already cached for the previous page
                //use it, go back then
                var prevPage = $scope.selectedPage - 1;
                console.log("We have to go back to the previous page", prevPage);
                var offset = ($scope.itemsPerPage) * ($scope.selectedPage - 1);

                console.log("Offset Per Page = ", offset);
                 var offset = ($scope.pageSize) * ($scope.currentPage - 1);

                 $scope.getPage = function (currentPage) {
                 // getting correct offset value based on page number
                 var offset = ($scope.pageSize) * ($scope.currentPage - 1);

                 // slicing the main data set to get the required subset
                 $scope.currentPageItems = $scope.movies.slice(offset, $scope.pageSize + offset);
                 }
                */
                $state.go('.', {page: prevPage});
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