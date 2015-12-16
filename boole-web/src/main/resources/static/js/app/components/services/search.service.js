'use strict';

angular.module('booleApp')
    .factory('searchService', ['$http', '$q',
        function ($http, $q) {
            var baseSearchUrl = "/api/search";
            var searchService = {};

            /**
             * Search Movies with only the page,
             * @param queryStr
             * @param currentPage
             * @param itemsPerPage
             * @returns {*}
             */
            searchService.search = function (queryStr, currentPage, itemsPerPage) {
                var defer = $q.defer();

                /**
                 * Find all movies with paging associated
                 */
                $http.get(baseSearchUrl + "?q=" + queryStr + "&page=" + currentPage + "&limit=" + itemsPerPage)
                    .success(function (response) {
                        defer.resolve(response);
                    }).error(function (err) {
                    console.log(response);
                    defer.reject(err);
                });
                return defer.promise;
            };

            /**
             * Do a search request using filtering.
             * @param requestParams
             * @returns {*}
             */
            searchService.searchFilter = function (requestParams) {
                var defer = $q.defer();

                var searchRequestUrl = baseSearchUrl + '?';
                _.each(requestParams, function (val, objKey) {
                    if (val && val !== '') {
                        searchRequestUrl += objKey + "=" + val + "&";
                    }
                });

                searchRequestUrl = searchRequestUrl.substr(0, searchRequestUrl.length - 1);
                console.log(searchRequestUrl);

                $http.get(searchRequestUrl)
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