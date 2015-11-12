'use strict';

angular.module('booleStoreApp')
    .controller('menuController', ['$scope', 'storeFactory', function ($scope, storeFactory) {
        $scope.stores = [];

        var getStores = function () {
            storeFactory.findAll()
                .then(function (response) {
                    if (response.meta && response.meta.size > 0) {
                        _.each(response.data, function (item) {
                            $scope.stores.push(item);
                        });
                    }
                }, function (error) {
                    console.log(error)
                });
        };

        getStores();
    }]).directive('sideMenu', [function () {
        return {
            link: function(scope, element, attrs){
                // $timeout(function(){
                setTimeout(function(){
                    $(element).metisMenu();
                },10);

                scope.$watch(scope.stores, function(){
                    setTimeout(function(){
                        $(element).metisMenu();
                    },10);
                });

                scope.$on('test', function(){
                    setTimeout(function(){
                        //scope.$apply(function(){
                        //$(element).metisMenu();
                        //});
                        scope.$apply();
                    },10);
                });
            }
        }
    }]);