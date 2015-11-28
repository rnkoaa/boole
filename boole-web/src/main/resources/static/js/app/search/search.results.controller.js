'use strict';

//NavBarController
angular.module('booleApp')
    .controller('searchResultsController', ['$scope', '$log', 'searchService', '$state', '$stateParams',
        function ($scope, $log, searchService, $state, $stateParams) {
            $scope.totalPages = 0;
            $scope.hasSearched = false;
            $scope.searchResults = null;
            $scope.selectedPage = parseInt($stateParams.page, 10);
            $scope.itemsPerPage = parseInt($stateParams.limit, 10);
            $scope.searchTerms = $stateParams.q || '';

            if ($scope.selectedPage == undefined || isNaN($scope.selectedPage)) {
                $scope.selectedPage = 1;
            }
            if ($scope.itemsPerPage == undefined || isNaN($scope.itemsPerPage)) {
                $scope.itemsPerPage = 25;
            }
            if ($scope.selectedPage === 0) {
                $scope.selectedPage = $scope.selectedPage + 1;
            }

            //do a search based on the user's request
            search($scope);

            function search($scope) {
                $scope.hasSearched = true;

                searchService.search($scope.searchTerms, $scope.selectedPage, $scope.itemsPerPage)
                    .then(function (response) {
                        //return response;
                        $log.log(response);
                        $scope.searchResults = response;
                        $scope.totalPages = response.meta.totalPages;
                        $scope.totalItems = response.meta.size;
                    }, function (error) {
                        //return error;
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