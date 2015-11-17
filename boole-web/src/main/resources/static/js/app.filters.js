"use strict";
angular.module('booleApp')
    .filter('capitalize', function () {
        return function (input) {
            return input.charAt(0).toUpperCase() + input.slice(1).toLowerCase();
        };
    })
    .filter('normalize', function (firstName, lastName) {
        return $filter('capitalize')(firstName) + ' ' + $filter('capitalize')(lastName);
    });