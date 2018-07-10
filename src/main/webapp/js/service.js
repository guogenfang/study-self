angular.module('app.service',[])
.factory('Case',function($http){
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
})
.factory('Login',function($http){
	return {
		login: function(parm,callback){
			$http.jsonp('http://192.168.60.224:8080/mediator/login?callback=JSON_CALLBACK',parm).success(function(data) {
				callback(data);
			}).error(function(error){
			});
		}
	};
});