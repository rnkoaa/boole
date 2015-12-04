'use strict';

//NavBarController
angular.module('booleApp')
    .controller('searchResultsController', ['$scope', '$log', 'searchService', '$state', '$stateParams',
        function ($scope, $log, searchService, $state, $stateParams) {
            $scope.oneAtATime = false;

            $scope.groups = [
                {
                    title: 'Dynamic Group Header - 1',
                    content: 'Dynamic Group Body - 1'
                },
                {
                    title: 'Dynamic Group Header - 2',
                    content: 'Dynamic Group Body - 2'
                }
            ];

            $scope.items = ['Item 1', 'Item 2', 'Item 3'];

            $scope.addItem = function () {
                var newItemNo = $scope.items.length + 1;
                $scope.items.push('Item ' + newItemNo);
            };

            $scope.status = {
                isFirstOpen: true,
                isFirstDisabled: false
            };


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

                        $log.log($scope.searchResults);
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