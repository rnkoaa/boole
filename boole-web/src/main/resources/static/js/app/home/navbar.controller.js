'use strict';

//navBarController
angular.module('booleApp')
    .controller('navBarController', ['$scope', '$log', 'searchService', '$state',
        function ($scope, $log, searchService, $state) {

            $scope.search = function (queryStr) {
                console.log("search called: ", queryStr);
                $scope.searchTerms = queryStr;
                $state.go('searchResults',
                    {
                        q: queryStr,
                        page: 0,
                        limit: 25
                    });
            };
        }])
;