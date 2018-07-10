/**
 * @author ggf
 * user like this
	1、in controller 
		$scope.pageParm = {
			totalNumber:90,//总条数
			perPageNumber:10,//每页显示个数
			currentPage:1,//当前页
			pageTotal:9,//总页数
			changePage:function(pageNumber){
				$scope.refresh(pageNumber);//请求数据
			}
		};
		$scope.refresh = function(pageNumber){
			//http get data then set pageParm data
			$scope.pageParm.pageTotal = 4;
			$scope.pageParm.totalNumber = 40;
		}
	2、in pages
	 <pagination conf="pageParm"></pagination>
 */
angular.module('self.page', [])
.constant('ext', function(target,object){})
.directive("pagination",['$log','ext',function($log,ext){ 
	return{
		restrict: 'EA',
		templateUrl: 'pageTag.html',
		scope:{
			conf:'='
		},
		link: function(scope, element, attrs){
			/**
			 * 总页码长度 如果总页数大于它，需要用'.....'
			 */
			var pagesLength = 7;
			scope.changePage = function(number){
				scope.conf.changePage(number);
				scope.conf.currentPage = number;
			}
			
			function getPageList(){
				console.log("calc page list");
				var pageList = [];
				if(scope.conf.pageTotal <= pagesLength)
				{
					for(var i = 1; i<= scope.conf.pageTotal; i++ )
					{
						pageList.push(i);
					}
				}
				else
				{
					/**
					 * 计算偏移量
					 * 当前页或者剩余页数如果大于偏移量，那么需要省略中间部分显示
					 */
					var offset = (pagesLength - 1)/2;
					//当前页小于偏移量 -- 左侧不需要出现 '...'
					if(scope.conf.currentPage <= offset)
					{
						for(var i = 1; i<= offset + 1; i++ )
						{
							pageList.push(i);
						}
						pageList.push('...');
						pageList.push(scope.conf.pageTotal);
					}
					//总页数-当前页 如果小于偏移量 那么右边不需要出现 '...'
					else if(scope.conf.pageTotal - scope.conf.currentPage < offset)
					{
						pageList.push(1);
						pageList.push('...');
                        for(i = offset; i >= 1; i--){
                        	pageList.push(scope.conf.pageTotal - i);
                        }
                        pageList.push(scope.conf.pageTotal);
					}
					else
					{
						pageList.push(1);
                        pageList.push('...');
                        pageList.push(scope.conf.currentPage - 1);
                        pageList.push(scope.conf.currentPage);
                        pageList.push(scope.conf.currentPage + 1);
                        pageList.push('...');
                        pageList.push(scope.conf.pageTotal);
					}
				}
				scope.pageList = pageList;
			}
			
			scope.$watch(function(){
				return scope.conf.currentPage;
			}, getPageList);
		}
	}
}]);