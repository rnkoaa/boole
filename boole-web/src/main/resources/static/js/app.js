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
        /*$stateProvider.state('stores', {
            url: '/boole/stores/:storeId',
            controller: 'storeController',
            templateUrl: '/boole/js/app/main/main.html'
        });
        $stateProvider.state('storeCustomers', {
            url: '/boole/stores/:storeId/customers',
            controller: 'storeCustomersController',
            templateUrl: '/boole/js/app/store/customers/list.html'
        });
        $stateProvider.state('storeInventory', {
            url: '/boole/stores/:storeId/inventory',
            controller: 'storeController',
            templateUrl: '/boole/js/app/main/inventory.html'
        });*/
    });