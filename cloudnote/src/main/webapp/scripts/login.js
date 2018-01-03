/*login.js 封装登录和注册功能*/
/*log_in.html主处理*/
$(function(){//页面载入完毕
	//给登录按钮绑定单机处理
	$("#login").click(checkLogin);
	//给注册按钮绑定单机处理
	$("#regist_button").click(registUser);
});
/*注册处理*/
function registUser(){
		//获取请求参数
		var name = $("#regist_username").val().trim();
		var nick = $("#nickname").val().trim();
		var password = $("#regist_password").val().trim();
		var f_password = $("#final_password").val().trim();
		//格式检查
		//清空消息
		$("#warning_1 span").html("");
		$("#warning_2 span").html("");
		$("#warning_3 span").html("");
		var ok = true;//是否验证通过
		if(name == ""){
			ok = false;
			$("#warning_1").show();
			$("#warning_1 span").html("用户名为空");
		}
		if(password == ""){
			ok = false;
			$("#warning_2").show();
			$("#warning_2 span").html("密码为空");
		}else if(password.length<6){
			ok = false;
			$("#warning_2").show();
			$("#warning_2 span").html("密码长度大于6");
		}
		if(f_password == ""){
			ok = false;
			$("#warning_3").show();
			$("#warning_3 span").html("确认密码为空");
		}else if(f_password!=password){
			ok = false;
			$("#warning_3").show();
			$("#warning_3 span").html("与密码不一致");
		}
		//发送Ajax请求
		if(ok){
		$.ajax({
			url:base_path+"/user/add.do",
			type:"post",
			data:{"name":name,"password":password,"nick":nick},
			dataType:"json",
			success:function(result){
				if(result.status == 0){//成功
					alert(result.msg);//提示注册成功
					$("#back").click();//转向登录界面
				}else if(result.status == 1){//用户名被占用
					$("#warning_1").show();
					$("#warning_1 span").html(result.msg);
				}
			},
			error:function(){
				alert("注册异常");
			}
		});
		}
	}
function checkLogin(){
		//获取请求参数
		var name = $("#count").val().trim();
		var password = $("#password").val().trim();
		//检查参数格式
		$("#count_span").html("");
		$("#password_span").html("");
		var ok = true;
		if(name==""){
			ok = false;
			$("#count_span").html("用户名为空");
		}
		if(password==""){
			ok = false;
			$("#password_span").html("密码为空");
		}
		if(ok){
			//发送ajax请求
			$.ajax({
				url:base_path+"/user/login.do",
				type:"post",
			    data:{"name":name,"password":password},
			    dataType:"json",
			    success:function(result){
			    	//result是服务器返回的JSON结果
			    	//{"status":xx,"msg":xx,"data":xx}
			    	if(result.status==0){//成功
			    		var user = result.data;//获取返回的user
			    		//写入Cookie
			    		addCookie("uid",user.cn_user_id,2);
			    		addCookie("uname",user.cn_user_name,2);
			    		//将用户信息存入Cookie
			    		window.location.href="edit.html";
			    	}else if(result.status==1){//用户名错误
			    		$("#count_span").html(result.msg);
			    	}else if(result.status==2){//密码错误
			    		$("#password_span").html(result.msg);
			    	}
			    },
			    error:function(){
			    	alert("登录异常");
			    }
			});
		}
	}


