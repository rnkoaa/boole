'use strict';

angular.module('booleApp')
    .directive('linkGenres', function() {
        return {
            restrict: 'A',
            transclude: true,
            templateUrl: '/js/app/movies/genres.list.html'
        };
    });