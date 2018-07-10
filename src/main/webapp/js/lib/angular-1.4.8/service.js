angular.module('app.service',[]).factory('Case',function($http){
	return {
		list: function(parm,callback){
			$http.get('js/data' + parm.pageNumber + '.json', {msg:''}).success(function(data) {
				callback(data);
			}).error(function(error){
				
			});
		},
		detail: function(parm,callback){
			$http.get('js/detail.json').success(function(data) {
				callback(data);
			}).error(function(error){
				
			});
		}
	};
});