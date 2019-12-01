var AWS = require('aws-sdk');
AWS.config.update({region: 'eu-central-1'});
var dataset = require("./dataset");
console.log("Total number of entries:" + dataset.length);

var documentClient = new AWS.DynamoDB.DocumentClient(/*{convertEmptyValues:true}*/);

dataset.forEach(entry => {
	var params =  {
  		TableName: "DeutscheDeklinationInflection",
		Item: entry
	};
	documentClient.put(params, function(err, data) {
		if (err) {
			console.log("Error", err);
		} else {
			console.log("Processed: " + entry.word);
		}
	});
});
