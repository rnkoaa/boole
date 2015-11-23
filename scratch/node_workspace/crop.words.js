var _ = require('underscore');

(function(){
	var self = this;
	self.defaultChars = [',', '.', ':', ';'];

	self.isWhiteSpace = function(character){
		return character == ' ' || character == '\n' || character == '\t';
	};

	self.cropWholeWords = function(value, length){
		if(!value)
			throw "Cannot crop a null or empty string";

		if  (value.length < length) {
			return value;
		}

		var end = length;
		var valueArray = value;
		var i = end;
		for (i; i > 0; i--) {
			if (isWhiteSpace(valueArray[i])) {
                break;
            }

            if(_.contains(defaultChars, valueArray[i]) && (valueArray.length == i + 1 || valueArray[i + 1] == ' ')) {
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
       return valueArray.substr(0, end);
	}

	var a = 1;
	console.log(_.isUndefined(a));

	console.log(self.cropWholeWords("hello, World", 25));

})();