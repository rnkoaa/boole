'use strict';

angular.module('booleApp', ['ui.bootstrap', // for modal dialogs
        'ui.router', //for state management,
        'ngSanitize',
        'datatables' //for dynamic tables
    ])
    .config(function ($httpProvider, $locationProvider, $urlRouterProvider, $stateProvider) {
        $httpProvider.interceptors.push('loadingInterceptor');

        $locationProvider.html5Mode(true);

        $urlRouterProvider.otherwise('/');

        $stateProvider.state('home', {
            url: '/',
            controller: 'homeController',
            templateUrl: '/js/app/home/home.html'
        });
        $stateProvider.state('discover', {
            url: '/discover?page&limit',
            controller: 'discoverController',
            controllerAs: 'discover',
            templateUrl: '/js/app/home/home.html',
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
        $stateProvider.state('discoverList', {
            url: '/discover/list?page&limit',
            controller: 'discoverController',
            controllerAs: 'discover',
            templateUrl: '/js/app/home/movie.list.html',
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
            url: '/discover/movies/:movieId',
            controller: 'movieDetailController',
            controllerAs: 'movieDetailController',
            templateUrl: '/js/app/movies/details.html'
        });
        $stateProvider.state('genreMovies', {
            url: '/discover/genres/:genreId?include',
            controller: 'movieDetailController',
            controllerAs: 'movieDetailController',
            templateUrl: '/js/app/movies/details.html',
            params: {
                include: {
                    value: 'movies',
                    squash: true
                }
            }
        });
        $stateProvider.state('personDetails', {
            url: '/discover/person/:personId?include',
            controller: 'movieDetailController',
            controllerAs: 'movieDetailController',
            templateUrl: '/js/app/movies/details.html',
            params: {
                include: {
                    value: 'movies',
                    squash: true
                }
            }
        });
    });