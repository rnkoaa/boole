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
    .filter('genreLinkify', function () {

        return function (genres) {
            var genreString = '';
            _.each(genres, function (item) {
                genreString = genreString +
                    "<a href='/discover/genres/" + item.id + "?include=movies'>" + item.name + "</a>" + ", ";
            });
            //remove any trailing , and space
            if (genreString.length > 0) {
                genreString = genreString.substr(0, genreString.length - 2);
            }

            return genreString;
        };
    }).filter('genreLinkifyWithPipes', function () {

        return function (genres) {
            var genreString = '';
            _.each(genres, function (item) {
                genreString = genreString +
                    "<a href='/discover/genres/" + item.id + "?include=movies'>" + item.name + "</a>" + " | ";
            });
            //remove any trailing , and space
            if (genreString.length > 0) {
                genreString = genreString.substr(0, genreString.length - 2);
            }

            return genreString;
        };
    })
    .filter('personLinkify', function () {

        return function (persons) {
            var personString = '';
            _.each(persons, function (item) {
                personString = personString +
                    "<a href='/discover/person/" + item.id + "?include=movies'>" + item.name + "</a>" + ", ";
            });
            //remove any trailing , and space
            if (personString.length > 0) {
                personString = personString.substr(0, personString.length - 2);
            }

            return personString;
        };
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
    .filter('directorsToString', function () {
        return function (directors) {
            var genreString = '';
            _.each(directors, function (item) {
                genreString = genreString + item.name + ", ";
            });

            //remove any trailing , and space
            if (genreString.length > 0) {
                genreString = genreString.substr(0, genreString.length - 2);
            }

            return genreString;
        };
    })
    .filter('producersToString', function () {
        return function (producers) {
            var genreString = '';
            _.each(producers, function (item) {
                genreString = genreString + item.name + ", ";
            });

            //remove any trailing , and space
            if (genreString.length > 0) {
                genreString = genreString.substr(0, genreString.length - 2);
            }

            return genreString;
        };
    })
    .filter('genresToStringUrl', function () {
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
        var defaultChars = [',', '.', ':', ';'];

        var isWhiteSpace = function (character) {
            return character == ' ' || character == '\n' || character == '\t';
        };

        return function (title) {
            if (title) {
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
                    title = valueArray.substr(0, 220) + "...";
                    return title;
                }
                return title;
            }
        };
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
    })
    .filter('retrieveBody', function () {
        return function (key) {
            return key.substr(key.indexOf('|') + 1, key.length);
        };
    })
    .filter('retrieveKeyId', function () {
        return function (key) {
            return key.substr(0, key.indexOf('|'));
        }
    });