'use strict';

angular.module('sakilaStoreApp', ['ui.bootstrap', // for modal dialogs
    'ui.router', //for state management
    'datatables' //for dynamic tables
])
    .config(function ($locationProvider, $urlRouterProvider, $stateProvider) {
        $locationProvider.html5Mode(true);

        $urlRouterProvider.otherwise('/sakila');

        $stateProvider.state('home', {
            url: '/sakila',
            controller: 'storeController',
            templateUrl: '/sakila/js/app/main/main.html'
        });
        $stateProvider.state('stores', {
            url: '/sakila/stores/:storeId',
            controller: 'storeController',
            templateUrl: '/sakila/js/app/main/main.html'
        });
        $stateProvider.state('storeCustomers', {
            url: '/sakila/stores/:storeId/customers',
            controller: 'storeCustomersController',
            templateUrl: '/sakila/js/app/store/customers/list.html'
        });
        $stateProvider.state('storeInventory', {
            url: '/sakila/stores/:storeId/inventory',
            controller: 'storeController',
            templateUrl: '/sakila/js/app/main/inventory.html'
        });
    });