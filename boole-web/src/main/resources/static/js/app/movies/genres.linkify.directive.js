'use strict';

angular.module('booleApp')
    .directive('linkifyGenres', function () {
        return {
            restrict: 'A',
            scope: {
                myAttribute: '='
            },
            template: '<div style="font-weight:bold">{{ myAttribute | number:2 }}</div>'
        };
    });