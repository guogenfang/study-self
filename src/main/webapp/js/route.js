angular.module('app',['ui.router','app.controller','self.page'])
.config(["$stateProvider","$urlRouterProvider",
     function($stateProvider, $urlRouterProvider) {
		$stateProvider
	    .state('dir',{
	    	url:'/dir',
	    	templateUrl: 'dir.html',
	    	controller:'dirController'
	    })
	    .state('test',{
	    	url:'/test',
	    	templateUrl: 'test.html',
	    	controller:'testController'
	    });
		$urlRouterProvider.otherwise("/dir");
	}
]);
angular.module('app.controller',[]);