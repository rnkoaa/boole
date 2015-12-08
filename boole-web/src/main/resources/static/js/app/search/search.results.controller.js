'use strict';

//NavBarController
angular.module('booleApp')
    .controller('searchResultsController', ['$scope', '$log', 'searchService', '$state', '$stateParams',
        function ($scope, $log, searchService, $state, $stateParams) {
            $scope.oneAtATime = false;
            $scope.aggregations = [];

            $scope.groups = [{
                groupTitle: "Genres",
                templateUrl: "file1.html"
            }, {
                groupTitle: "Directors",
                templateUrl: "file1.html"
            }, {
                groupTitle: "Actors",
                templateUrl: "file2.html"
            }, {
                groupTitle: "Writers",
                templateUrl: "file3.html"
            }, {
                groupTitle: "Producers",
                templateUrl: "file3.html"
            }];

            $scope.status = {
                isOpen: new Array($scope.groups.length)
            };

            for (var i = 0; i < $scope.status.isOpen.length; i++) {
                $scope.status.isOpen[i] = (i === 0);
            }


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

                        //var keys = [];

                        _.each(response.data.aggregations, function (val, key) {
                            if (val) {
                                $scope.aggregations.push({
                                    title: getGroupTitle(key),
                                    items: val
                                });
                            }
                        });

                       // $log.log(keys);
                        console.log($scope.aggregations);
                        //$log.log($scope.searchResults);
                    }, function (error) {
                        //return error;
                    });
            }

            //retrieve the group name from the aggregate.
            function getGroupTitle(item) {
                var _pos = item.indexOf('_');
                return item.substr(0, _pos);
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