var $route = {};
var baseUrl = 'http://localhost:8080/?';
$route.IMenuService = {
	'getMenuList' : function(success, error) {
		var _url = 'i=com.sifayun.platform.service.api.IMenuService&m=getMenuList';
		post(_url, {
			'p' : JSON.stringify([])
		}, success, error);
	},
	'_interface' : 'com.sifayun.platform.service.api.IMenuService'
}

$route.IUserService = {
	'all' : function(success, error) {
		var _url = 'i=com.sifayun.platform.service.api.IUserService&m=all';
		post(_url, {
			'p' : JSON.stringify([])
		}, success, error);
	},
	'delete' : function(id, success, error) {
		var _url = 'i=com.sifayun.platform.service.api.IUserService&m=delete';
		post(_url, {
			'p' : JSON.stringify([ id ])
		}, success, error);
	},
	'save' : function(user, success, error) {
		var _url = 'i=com.sifayun.platform.service.api.IUserService&m=save';
		post(_url, {
			'p' : JSON.stringify([ user ])
		}, success, error);
	},
	'findById' : function(id, success, error) {
		var _url = 'i=com.sifayun.platform.service.api.IUserService&m=findById';
		post(_url, {
			'p' : JSON.stringify([ id ])
		}, success, error);
	},
	'login' : function(username, password, success, error) {
		var _url = 'i=com.sifayun.platform.service.api.IUserService&m=login';
		post(_url, {
			'p' : JSON.stringify([ username, password ])
		}, success, error);
	},
	'_interface' : 'com.sifayun.platform.service.api.IUserService'
}
