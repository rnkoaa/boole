'use strict';

//NavBarController
angular.module('booleApp')
    .controller('searchResultsController', ['$log', 'searchService', '$state', '$stateParams',
        'aggsService',
        function ($log, searchService, $state, $stateParams, aggsService) {
            var searchResultsCtrl = this;
            searchResultsCtrl.oneAtATime = false;
            searchResultsCtrl.aggregations = [];
            searchResultsCtrl.selectedAggregations = [];
            searchResultsCtrl.requestParams = {};

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

            searchResultsCtrl.selectItem = function (aggregations, aggregationItem) {
                searchResultsCtrl.selectedAggregations =
                    aggsService.selectItem(searchResultsCtrl.selectedAggregations, aggregationItem, aggregations);

                console.log(searchResultsCtrl.selectedAggregations);

                var requestParams = aggsService.prepareRequestParams(searchResultsCtrl.selectedAggregations, {});
                console.log(requestParams);

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

                        searchResultsCtrl.aggregations =  aggsService.availableAggs(response);

                    }, function (error) {
                        //return error;
                    });
            }

        }]);