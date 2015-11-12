'use strict';

angular.module('sakilaStoreApp')
    .controller('storeController', ['$scope', '$state', '$stateParams', '$filter', 'storeFactory', 'DTOptionsBuilder', 'DTColumnBuilder',
        function ($scope, $state, $stateParams, $filter, storeFactory, DTOptionsBuilder, DTColumnBuilder) {
            var storeId = $stateParams.storeId;
            $scope.storeId = storeId;
            if (!storeId) {
                $state.go('stores', {
                    storeId: 1
                });
            } else {
                storeFactory.findOne(storeId)
                    .then(function (response) {
                        $scope.currentStore = response.data;
                        console.log($scope.currentStore);
                    }, function (error) {
                        console.log("Received Error", error);
                    });

            }

            /*$scope
                .dtOptions = DTOptionsBuilder
                .fromSource('/sakila/api/stores/' + storeId + '/customers')
                .withPaginationType('full_numbers')
                .withLanguage({
                    "sLengthMenu":     "Display _MENU_ items per page",
                    "sLoadingRecords": "Loading...",
                    "sProcessing":     "Processing...",
                    "sSearch":         "filter customers:",
                    "oPaginate": {
                        "sNext":     "»",
                        "sPrevious": "«"
                    }
                })
                .withDataProp('data');


            $scope.dtColumns = [
                DTColumnBuilder.newColumn('id').withTitle('Id'),
                DTColumnBuilder.newColumn('firstName')
                    .withTitle('Name')
                    .renderWith(function (data, type, full) {
                        return normalizeName(full.firstName, full.lastName);
                    }),

                DTColumnBuilder.newColumn('lastName').withTitle('Last name').notVisible(),

                DTColumnBuilder.newColumn('email')
                    .withTitle('Email')
                    .renderWith(function (data, type, full) {
                        return $filter('lowercase')(full.email)
                    }).notSortable()
            ];

            var normalizeName = function (firstName, lastName) {
                return $filter('capitalize')(firstName) + ' ' + $filter('capitalize')(lastName);
            };*/

        }]);