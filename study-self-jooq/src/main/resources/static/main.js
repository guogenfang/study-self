function post(url, param, success, error){
	$.ajax({
		url : url,
		type : "GET",
		data : param,
		dataType : "json",
		beforeSend: function(request) {
            //request.setRequestHeader("account", "Chenxizhang");
        },
		success : function(res) {
			success(res);
		}
	});
}

function isLogin(){
	if($.cookie('name') == null || $.cookie('name') == ''){
		layer.open({
			type : 1,
			area : [ '400px', '300px' ],
			title : '登录',
			closeBtn: 0,
			shade : 0.6, //遮罩透明度
			maxmin : false,//允许全屏最小化
			anim : 1, //0-6的动画形式，-1不开启
			content : $("#login_body").html()
		});
	}
}

function login(){
	var param = {
			"name": $("#user",".layui-layer-content").val(),
			"pwd": $("#pwd",".layui-layer-content").val()
	};
	post("/login", param, function(res){
		if(res.code == 200){
			$.cookie('name', param.name);
			console.log($.cookie('name'));
		}
		else{
			layer.msg(res.data);
		}
	});
}
