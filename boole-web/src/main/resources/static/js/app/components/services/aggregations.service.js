'use strict';

angular.module('booleApp')
    .service('aggsService', ['_', function (_) {
        var aggsService = {};

        //used to parse raw elasticsearch results
        aggsService.rawAvailableAggs = function (result) {
            var aggs = result.data.aggregations;
            var groups = [];
            _.each(aggs, function (val, key) {
                if (val) {
                    var obj = {};
                    obj.groupName = sanitizeGroupName(key);
                    var bucketObjKey = obj.groupName + '_by_name_and_id';
                    var bucketObj = val[bucketObjKey];
                    obj.buckets = bucketObj.buckets;
                    groups.push(obj);
                }
            });
            return groups;
        };

        aggsService.availableAggs = function (response) {
            var aggregations = [];
            _.each(response.data.aggregations, function (val, key) {
                if (val) {
                    aggregations.push({
                        title: sanitizeGroupName(key),
                        items: val
                    });
                }
            });

            return aggregations;
        };

        /**
         when an item is checked, process add the bucket if the
         main aggregation is already selected.
         selectedFilters, selectedAgg, availableAggs
         */
        aggsService.selectItem = function (selectedFilters, selectedAggregation, aggregations) {
            var selectedAgg = {
                key: aggregations.title
            };
            var existingAgg = _.findWhere(selectedFilters, selectedAgg);

            if (_.isUndefined(existingAgg) || existingAgg === null) {
                existingAgg = selectedAgg;
                existingAgg.value = [];
                selectedFilters.push(existingAgg);
            } else {
                if (_.isUndefined(existingAgg.value)) {
                    existingAgg.value = [];
                }
            }

            var existingBuckets = existingAgg.value;
            var isBucketSelected = isAlreadySelected(existingBuckets, stripBucketName(selectedAggregation.key));
            if (isBucketSelected) {
                //remove this item from the selected lists
                var keyIndex = existingBuckets.indexOf(stripBucketName(selectedAggregation.key));
                existingBuckets.splice(keyIndex, 1);
            } else {
                existingBuckets.push(stripBucketName(selectedAggregation.key));
            }

            existingAgg.value = existingBuckets;
            return selectedFilters;
        };


        aggsService.prepareRequestParams = function (selectedParams) {
           var requestParams = {};

            _.each(selectedParams, function (item) {
                var buckets = item.value;
                if (!_.isUndefined(buckets) && buckets.length > 0) {
                    requestParams[item.key] = buckets.join(',');
                }
            });
            return requestParams;
        };

        /**
         * Check to see if a bucket has already been selected.
         */
        function isAlreadySelected(buckets, bucketKey) {
            //return _.indexOf(buckets, bucketKey) > -1;
            return buckets.indexOf(bucketKey) > -1;
        }

        /**
         * Get the aggregate name from bucket name. bucket name looks like
         * bucket_aggs
         */
        function sanitizeGroupName(groupName) {
            var _pos = groupName.indexOf('_');
            return groupName.substr(0, _pos);
        }

        /**
         * Remove the id from the bucket
         */
        function stripBucketName(val) {
            var _pos = val.indexOf('|');
            return val.substr(_pos + 1, val.length);
        }

        return aggsService;
    }]);
