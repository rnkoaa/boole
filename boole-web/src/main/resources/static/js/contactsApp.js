'use strict';

/**
 * @ngdoc overview
 * @name uiroutesApp
 * @description
 * # uiroutesApp
 *
 * Main module of the application.
 */
angular.module('uiroutesApp', ['ui.router']);

angular.module('uiroutesApp')
    .config(function ($locationProvider, $urlRouterProvider, $stateProvider) {
        $locationProvider.html5Mode(true);

        $urlRouterProvider.otherwise('/home');

        $stateProvider.state('home', {
            url: '/home',
            templateUrl: '/boole/js/contacts/partials/home.html'
        });
        $stateProvider.state('about', {
            url: '/about',
            templateUrl: '/boole/js/contacts/partials/about.html'
        });

        $stateProvider.state('contacts', {
            url: '/contacts',
            controller: 'ListContactsController',
            templateUrl: '/boole/js/contacts/partials/contacts.html'
        });
        $stateProvider.state('contacts.contactCard', {
            url: '/card/:contactId',
            controller: 'ContactCardController',
            templateUrl: '/boole/js/contacts/partials/contact-card.html'
        });
        $stateProvider.state('contactCard', {
            url: '/card/:contactId',
            controller: 'ContactCardController',
            templateUrl: '/boole/js/contacts/partials/contact-card.html'
        });
    });


/**
 * Controllers
 */
angular.module('uiroutesApp')
    .controller('RootController', function ($scope, $state, $stateParams) {
        $scope.$state = $state;
        $scope.$stateParams = $stateParams;
    });
angular.module('uiroutesApp')
    .controller('ListContactsController', function ($scope, ContactService) {
        $scope.contacts = ContactService.getContacts();
    });
angular.module('uiroutesApp')
    .controller('ContactCardController', function ($scope, $state, $stateParams, ContactService) {
        var contactId = $stateParams.contactId;
        if (!contactId) {
            $state.go('contacts.contactCard', {
                contactId: 1
            });
        } else {
            $scope.currentContact = ContactService.getContactById(contactId);
        }
    });


/**
 * Services
 */
angular.module('uiroutesApp')
    .factory('ContactService', function () {
        var service = {
            getContacts: function () {
                return [{
                    contactId: 1,
                    name: 'John',
                    fullName: 'John Jenson',
                    role: 'Developer',
                    employer: 'Cengage'
                }, {
                    contactId: 2,
                    name: 'Paul',
                    fullName: 'Paul Barry',
                    role: 'Developer',
                    employer: 'Cengage'
                }, {
                    contactId: 3,
                    name: 'Manoj',
                    fullName: 'Manoj Singh',
                    role: 'Developer',
                    employer: 'Cengage'
                }, {
                    contactId: 4,
                    name: 'Andrea',
                    fullName: 'Andrea Jenkins',
                    role: 'Developer',
                    employer: 'Cengage'
                }, {
                    contactId: 5,
                    name: 'Ben',
                    fullName: 'Ben Babics',
                    role: 'Developer',
                    employer: 'Cengage'
                }];
            },
            getContactById: function (contactId) {
                return _.findWhere(service.getContacts(), {
                    contactId: parseInt(contactId)
                });
            }
        };
        return service;
    });
