'use strict';

//NavBarController
angular.module('booleApp')
    .controller('searchResultsController', ['$log', 'searchService', '$state', '$stateParams',
        'aggsService', '$filter',
        function ($log, searchService, $state, $stateParams, aggsService, $filter) {
            var searchResultsCtrl = this;
            searchResultsCtrl.oneAtATime = false;
            searchResultsCtrl.aggregations = [];
            searchResultsCtrl.selectedAggregations = [];
            searchResultsCtrl.requestParams = {};
            searchResultsCtrl.requestParams.directors = $stateParams.directors || '';
            searchResultsCtrl.requestParams.producers = $stateParams.producers || '';
            searchResultsCtrl.requestParams.writers = $stateParams.writers || '';
            searchResultsCtrl.requestParams.genres = $stateParams.genres || '';
            searchResultsCtrl.requestParams.actors = $stateParams.actors || '';

            //capture selected filters
            var selectedFilters = [];
            _.each(searchResultsCtrl.requestParams, function (val, objKey) {
                if (val && val !== '') {
                    var splitStr = val.split(',');
                    var obj = {};
                    obj.key = objKey;
                    obj.value = splitStr;
                    selectedFilters.push(obj);
                }
            });

            searchResultsCtrl.status = {
                isOpen: new Array(searchResultsCtrl.aggregations.length)
            };

            for (var i = 0; i < searchResultsCtrl.status.isOpen.length; i++) {
                searchResultsCtrl.status.isOpen[i] = (i === 0);
            }

            searchResultsCtrl.totalPages = 0;
            searchResultsCtrl.hasSearched = false;
            searchResultsCtrl.searchResults = null;
            searchResultsCtrl.selectedPage = parseInt($stateParams.page, 10);
            searchResultsCtrl.itemsPerPage = parseInt($stateParams.limit, 10);
            searchResultsCtrl.searchTerms = $stateParams.q || '';

            if (searchResultsCtrl.selectedPage == undefined || isNaN(searchResultsCtrl.selectedPage)) {
                searchResultsCtrl.selectedPage = 1;
            }
            if (searchResultsCtrl.itemsPerPage == undefined || isNaN(searchResultsCtrl.itemsPerPage)) {
                searchResultsCtrl.itemsPerPage = 25;
            }
            if (searchResultsCtrl.selectedPage === 0) {
                searchResultsCtrl.selectedPage = searchResultsCtrl.selectedPage + 1;
            }

            //do a search based on the user's request
            search(searchResultsCtrl);

            //checks to see if the item has already been selected.
            searchResultsCtrl.isSelected = function (aggregationTitle, aggregationItemKey) {
                var found = _.findWhere(selectedFilters, {key: aggregationTitle});
                if (found) {
                    var aggregationItemName = $filter('retrieveBody')(aggregationItemKey);
                    return found.value.indexOf(aggregationItemName) > -1;
                }
                return false;
            };

            searchResultsCtrl.selectItem = function (availableAggs, selectedAgg) {
                 selectedFilters = aggsService.selectItem(selectedFilters, selectedAgg, availableAggs);

                var requestParams = aggsService.prepareRequestParams(selectedFilters);
                requestParams.q = searchResultsCtrl.searchTerms;
                $state.transitionTo('searchResults', requestParams, { notify: false });
            };

            searchResultsCtrl.previousPage = function () {
                $state.go('.', {page: prevPage});
            };

            searchResultsCtrl.nextPage = function () {
                $state.go('.', {page: searchResultsCtrl.selectedPage + 1});
            };

            searchResultsCtrl.noPrevious = function () {
                return searchResultsCtrl.selectedPage <= 1;
            };

            searchResultsCtrl.noNext = function () {
                return searchResultsCtrl.selectedPage >= searchResultsCtrl.totalPages;
            };

            function search(searchResultsCtrl) {
                searchResultsCtrl.hasSearched = true;
                searchResultsCtrl.selectedAggregations = [];

                searchService.search(searchResultsCtrl.searchTerms, searchResultsCtrl.selectedPage, searchResultsCtrl.itemsPerPage)
                    .then(function (response) {

                        searchResultsCtrl.searchResults = response;
                        searchResultsCtrl.totalPages = response.meta.totalPages;
                        searchResultsCtrl.totalItems = response.meta.size;

                        searchResultsCtrl.aggregations = aggsService.availableAggs(response);

                    }, function (error) {
                        //return error;
                    });
            }

        }]);