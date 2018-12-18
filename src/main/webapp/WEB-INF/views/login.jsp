<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/model/login/login.css">
</head>
<body>
	<!-- 定义容器 -->
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-sm-3"></div>

			<!-- 这一列为登陆表单 -->
			<div class="col-md-4 col-sm-6">
				<div class="panel panel-default">

					<!-- 登陆面板的标题 -->
					<div class="panel-title" style="text-align: center">
						<h2>登录</h2>
					</div>

					<!-- 登陆面板的主体 -->
					<div class="panel-body">

						<!-- 表单 -->
						<form id="login_form" class="form-horizontal" style="">

							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4">用户ID：</label>
								<div class="col-md-7 col-sm-7">
									<input type="text" id="userID" class="form-control"
										placeholder="用户ID" name="userID" />
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4"> <!-- <span class="glyphicon glyphicon-lock"></span> -->
									密码：
								</label>
								<div class="col-md-7 col-sm-7">
									<input type="password" id="password" class="form-control"
										placeholder="密码" name="password">
								</div>
							</div>
							<div>
								<div class="col-md-4 col-sm-4"></div>
								<div class="col-md-4 col-sm-4">
									<button id="submit" type="submit" class="btn btn-primary">
										&nbsp;&nbsp;&nbsp;&nbsp;登陆&nbsp;&nbsp;&nbsp;&nbsp;</button>
								</div>
								<div class="col-md-4 col-sm-4"></div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="col-md-4 col-sm-3"></div>
		</div>
	</div>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrapValidator.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery.md5.js"></script>

	<script>
		$(function() {
			validatorInit();
		});


		// 登陆信息加密模块
		function infoEncrypt(userID, password) {
			var str1 = $.md5(password);
            return $.md5(str1 + userID);
		}
		//bootstrapValidator插件对表单进行封装，表单不用加action
		function validatorInit() {
			$('#login_form').bootstrapValidator({
				message : 'This value is not valid',
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					userID : {
						validators : {
							notEmpty : {
								message : '用户名不能为空'
							},regexp: {
		                        regexp: '[0-9]+',
		                        message: '只允许输入数字'
		                    },
							callback : {}
						}
					},
					password : {
						validators : {
							notEmpty : {
								message : '密码不能为空'
							},
							callback : {}
						}
					}
				}
			})
			.on('success.form.bv', function(e) {
				// 禁用默认表单提交
				e.preventDefault();

				// 获取 form 实例
				var $form = $(e.target);
				// 获取 bootstrapValidator 实例
				var bv = $form.data('bootstrapValidator');

				// 发送数据到后端 进行验证
				var userID = $('#userID').val();
				var password = $('#password').val();

				// 加密
				password = infoEncrypt(userID, password)

				var data = {
					"id" : userID,
					"password" : password
				};
				//JSON.stringify(data)序列化
				$.ajax({
					type:"POST",
					url:"account/signIn",
					dataType:"json",
					contentType:"application/json",
					data:JSON.stringify(data),
					success:function(response){
						// 接收到后端响应
						
						// 分析返回的 JSON 数据
						if(response.result == 'error'){
							var errorMessage;
							var field;
							if(response.msg == "unknownAccount"){
								errorMessage = "用户名错误";
								field = "userID";
							}
							else if(response.msg == "incorrectPassword"){
								errorMessage = "密码错误";
								field = "password";
								$('#password').val("");
							}else{
							    errorMessage = "服务器错误";
                                field = "password";
                                $('#password').val("");
							}
								
							// 更新 callback 错误信息，以及为错误对应的字段添加 错误信息
							bv.updateMessage(field,'callback',errorMessage);
							bv.updateStatus(field,'INVALID','callback');
							bv.updateStatus("checkCode",'INVALID','callback');
						}else{
							// 页面跳转
							window.location.href = "/mainPage";
						}
					},
					error:function(data){
						// 处理错误
					}
				});
			});
		}
	</script>
</body>
</html>