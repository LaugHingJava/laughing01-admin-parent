<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/public/admin-head.jsp" %>

<body>

	<%@ include file="/WEB-INF/public/admin-nav.jsp" %>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/public/admin-sidebar.jsp" %>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
					<li><a href="admin/to/main.html">首页</a></li>
					<li><a href="admin/page.html">数据列表</a></li>
					<li class="active">修改</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
					<div class="panel-body">
						<form role="form" method="post" action="admin/modifyAdmin.html">
							<h3>${requestScope.exception.message}</h3>
							<input hidden name="id" value=${admin.id}>
							<input hidden name="pageNum" value=${param.pageNum}>
							<input hidden name="keyword" value=${param.keyword}>
							<div class="form-group">
								<label for="exampleInputPassword1">登陆账号</label>
								<input type="text" value="${admin.loginAcct}" name="loginAcct" class="form-control" id="exampleInputPassword1" placeholder="请输入登陆账号">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">用户名称</label>
								<input type="text"value="${admin.userName}" name="userName" class="form-control" id="exampleInputPassword1" placeholder="请输入用户名称">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">邮箱地址</label>
								<input type="email" value="${admin.email}"name="email" class="form-control" id="exampleInputEmail1" placeholder="请输入邮箱地址">
								<p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
							</div>
							<button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 修改</button>
							<button type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>