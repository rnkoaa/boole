"use strict";
angular.module('booleApp')
    .filter('capitalize', function () {
        return function (input) {
            return input.charAt(0).toUpperCase() + input.slice(1).toLowerCase();
        };
    })
    .filter('capitalizeEachWord', function () {
        return function (input) {
            return input.charAt(0).toUpperCase() + input.slice(1).toLowerCase();
        };
    })
    .filter('normalize', function (firstName, lastName) {
        return $filter('capitalize')(firstName) + ' ' + $filter('capitalize')(lastName);
    })
    .filter('genresToString', function () {
        return function (genres) {
            var genreString = '';
            _.each(genres, function (item) {
                genreString = genreString + item.name + ", ";
            });

            //remove any trailing , and space
            if (genreString.length > 0) {
                genreString = genreString.substr(0, genreString.length - 2);
            }

            return genreString;
        };
    })
    .filter("cropWords", function () {
        /*var defaultChars = [',', '.', ':', ';'];

         var isWhiteSpace = function (character) {
         return character == ' ' || character == '\n' || character == '\t';
         };

         return function (sentence, length) {
         if (!sentence)
         throw "Cannot crop a null or empty string";

         if (sentence.length < length) {
         return sentence;
         }

         var end = length;
         var valueArray = sentence;
         var i = end;
         for (i; i > 0; i--) {
         if (isWhiteSpace(valueArray[i])) {
         break;
         }

         if (_.contains(defaultChars, valueArray[i]) && (valueArray.length == i + 1 || valueArray[i + 1] == ' ')) {
         //Removing a character that isn't whitespace but not part
         //of the word either (ie ".") given that the character is
         //followed by whitespace or the end of the string makes it
         //possible to include the word, so we do that.
         break;
         }
         end--;
         }

         if (end == 0) {
         //If the first word is longer than the length we favor
         //returning it as cropped over returning nothing at all.
         end = length;
         }
         return valueArray.substr(0, end);*/
        //};
    })
    .filter("sanitizeTitle", function () {
        var defaultChars = [',', '.', ':', ';'];

        var isWhiteSpace = function (character) {
            return character == ' ' || character == '\n' || character == '\t';
        };

        return function (title) {
            if (title.length > 19) {
                if (!title)
                    throw "Cannot crop a null or empty string";

                if (title.length < length) {
                    return title;
                }

                var end = length;
                var valueArray = title;
                var i = end;
                for (i; i > 0; i--) {
                    if (isWhiteSpace(valueArray[i])) {
                        break;
                    }

                    if (_.contains(defaultChars, valueArray[i]) && (valueArray.length == i + 1 || valueArray[i + 1] == ' ')) {
                        //Removing a character that isn't whitespace but not part
                        //of the word either (ie ".") given that the character is
                        //followed by whitespace or the end of the string makes it
                        //possible to include the word, so we do that.
                        break;
                    }
                    end--;
                }

                if (end == 0) {
                    //If the first word is longer than the length we favor
                    //returning it as cropped over returning nothing at all.
                    end = length;
                }
                title = valueArray.substr(0, 21) + "...";
                return title;
            }
            return title;
        };
    });