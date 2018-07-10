angular.module('app.controller')
.controller("dirController",['$scope',function($scope){
    	$scope.pageParm = {
    			totalNumber:80,//总条数
    			perPageNumber:10,//每页显示个数
    			currentPage:1,//当前页
    			pageTotal:8,//总页数
    			changePage:function(pageNumber){
    				$scope.refresh(pageNumber);
    			}
		};
    	$scope.refresh = function(pageNumber){
    		$scope.pageParm.currentPage = 2;
    		$scope.pageParm.pageTotal = 9;
    		$scope.pageParm.totalNumber = 90;
    	}
}])
.controller("testController",['$scope',function($scope){
    	$scope.user = {"birth":"1451917198000"};
    	$scope.con = function(){
    		console.log($scope.user);
    	}
}]);
