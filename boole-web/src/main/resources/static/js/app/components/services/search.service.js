'use strict';

angular.module('booleApp')
    .factory('searchService', ['$http', '$q',
        function ($http, $q) {
            var searchService = {};
            searchService.search = function (queryStr, currentPage, itemsPerPage) {
                var defer = $q.defer();

                /**
                 * Find all movies with paging associated
                 */
                $http.get("/api/search?q=" + queryStr + "&page=" + currentPage + "&limit=" + itemsPerPage)
                    .success(function (response) {
                        defer.resolve(response);
                    }).error(function (err) {
                        console.log(response);
                        defer.reject(err);
                    });
                return defer.promise;
            };

            return searchService;
        }]);