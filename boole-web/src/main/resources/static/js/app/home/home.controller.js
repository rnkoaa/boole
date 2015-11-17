'use strict';

//NavBarController
angular.module('booleApp')
    .controller('homeController', ['$scope', '$log', 'homeMovieService',
        function ($scope, $log, homeMovieService) {
            var currentPage = 0;
            var itemsPerPage = 20;
            var defaultSort = 'name';

            /*homeMovieService.findMovies(currentPage, itemsPerPage)
                .then(function (response) {
                    console.log(response);
                }, function (error) {
                    console.log(error);
                });*/

            $scope.totalItems = 64;
            $scope.currentPage = 4;

            $scope.setPage = function (pageNo) {
                $scope.currentPage = pageNo;
            };

            $scope.pageChanged = function () {
                $log.log('Page changed to: ' + $scope.currentPage);
            };

            $scope.maxSize = 5;
            $scope.bigTotalItems = 175;
            $scope.bigCurrentPage = 1;
        }]);