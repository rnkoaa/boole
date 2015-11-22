'use strict';

angular.module('booleApp', ['ui.bootstrap', // for modal dialogs
    'ui.router', //for state management
    'datatables' //for dynamic tables
])
    .config(function ($locationProvider, $urlRouterProvider, $stateProvider) {
        $locationProvider.html5Mode(true);

        $urlRouterProvider.otherwise('/boole');

        $stateProvider.state('home', {
            url: '/boole',
            controller: 'homeController',
            templateUrl: '/boole/js/app/home/home.html'
        });
        $stateProvider.state('discover', {
            url: '/boole/discover?page&limit',
            controller: 'discoverController',
            controllerAs: 'discover',
            templateUrl: '/boole/js/app/home/home.html',
            params: {
                page: {
                    value: '0',
                    squash: true
                },
                limit: {
                    value: '21',
                    squash: true
                }
            }
        });
        $stateProvider.state('movieDetail', {
            url: '/boole/discover/movies/:movieId',
            controller: 'movieDetailController',
            controllerAs: 'movieDetailController',
            templateUrl: '/boole/js/app/movies/details.html'
        });
    });