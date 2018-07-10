angular.module('app.config', ['ngDialog'])
    .run(function($rootScope,ngDialog) {
        $rootScope.$on('$routeChangeStart', function(evt, next, current) {
        });
        $rootScope.alert = function(message, callback){
        	$rootScope.msg = message;
            ngDialog.open({
            	scope:$rootScope,
    			template: 'alert.html',
    			className: 'ngdialog-theme-default ngdialog-theme-custom'
            });
        }
    });