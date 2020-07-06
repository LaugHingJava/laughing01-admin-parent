<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/public/admin-head.jsp"%>
<link href="statics/ztree/zTreeStyle.css" rel="stylesheet">
<script type="text/javascript" src="statics/ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="statics/js/menu-page.js"></script>
<script type="text/javascript">
    $(function(){
		// 调用专门封装好的函数初始化树形结构
        generateTree();
        //给#treeDemo节点下所有.addBtn属性的添加点击绑定事件
		$("#treeDemo").on("click",".addBtn",function(){
		    //帮当前属性id 赋值给全局变量
		    window.pid=this.id
			//打开新增页
			$("#menuAddModal").modal("show")
			return false;//因为是超链接要取消
		})

		//增加按钮发送异步请求并且重新刷新节点
		$("#menuSaveBtn").click(function(){
		    var pid = window.pid;
		    var name = $("#menuAddModal input[name=name]").val()
			var url =  $("#menuAddModal input[name=url]").val()
			var icon =$("#menuAddModal [name=icon]:checked").val()
			//拿到表单的值和pid
			//发送异步请求
			$.ajax({
				url:"menu/saveMenu.json",
				data:{
                    pid:pid,
                    name:name,
                    url:url,
                    icon:icon
				},
				dataType:"json",
				type:"post",
				success:function(response){
				    var result = response.result;
				    if(result=="SUCCESS"){
                        layer.msg("操作成功！");
						// 重新加载树形结构，注意：要在确认服务器端完成保存操作后再刷新
						// 否则有可能刷新不到最新的数据，因为这里是异步的1
                        generateTree();
					}
                    if(result == "FAILED") {
                        layer.msg("操作失败！"+response.message);
                    }

				},
                "error":function(response){
                    layer.msg(response.status+" "+response.statusText);
                }
			})
            // 关闭模态框
            $("#menuAddModal").modal("hide");
			// 清空表单
			// jQuery 对象调用click()函数，里面不传任何参数，相当于用户点击了一下1
            $("#menuResetBtn").click();

		})

		//打开修改窗口
        $("#treeDemo").on("click",".editBtn",function(){
            $("#menuEditModal").modal("show")
            //帮当前属性id 赋值给全局变量
            window.id=this.id
            // 获取zTreeObj对象
            var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");

            // 根据id属性查询节点对象
            // 用来搜索节点的属性名
            var key = "id";
            // 用来搜索节点的属性值
            var value = window.id;
            var currentNode = zTreeObj.getNodeByParam(key, value);
            // 回显表单数据
            $("#menuEditModal [name=name]").val(currentNode.name);
            $("#menuEditModal [name=url]").val(currentNode.url);

            // 回显radio可以这样理解：被选中的radio的value属性可以组成一个数组，
            // 然后再用这个数组设置回radio，就能够把对应的值选中
            $("#menuEditModal [name=icon]").val([currentNode.icon]);
            //打开修改页
            return false;//因为是超链接要取消
        })

		//给确认修改绑定事件然后发送请求
		$("#menuEditBtn").click(function(){
		    var id = window.id;
		    var name =    $("#menuEditModal [name=name]").val();
            var url=$("#menuEditModal [name=url]").val();
            var icon = $("#menuEditModal [name=icon]:checked").val();
            $.ajax({
				url:"menu/updateMenu.json",
				data:{
				    id:id,
					name:name,
					url:url,
					icon:icon
				},
				dataType:"json",
				type:"post",
				success:function(response){
				    if(response.result=="SUCCESS"){
                        layer.msg("操作成功！");

                        // 重新加载树形结构，注意：要在确认服务器端完成保存操作后再刷新
                        // 否则有可能刷新不到最新的数据，因为这里是异步的
                        generateTree();
					}

                    if(result == "FAILED") {
                        layer.msg("操作失败！"+response.message);
                    }

				},
				error:function(response){
                    layer.msg(response.status+" "+response.statusText);
				}

			})
            // 关闭模态框
            $("#menuEditModal").modal("hide");

		})


        //打开删除窗口11
        $("#treeDemo").on("click",".removeBtn",function(){
            $("#menuConfirmModal").modal("show")
            // 打开模态框
            window.id = this.id
            // 根据id属性查询节点对象
            // 用来搜索节点的属性名
            var key = "id";
            // 用来搜索节点的属性值
            var value = window.id;
            // 获取zTreeObj对象
            var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
            var currentNode = zTreeObj.getNodeByParam(key, value);
            $("#removeNodeSpan").html("【<i class='"+currentNode.icon+"'></i>"+currentNode.name+"】");
            return false;
		})
		//给确认删除添加点击事件发送删除异步请求
		$("#confirmBtn").click(function(){
			$.ajax({
				url:"menu/removeMenu.json",
				data:{
				    id:window.id
				},
				dataType:"json",
				type:"post",
				success:function(response){
				    if(response.result=="SUCCESS"){
                        layer.msg("删除成功")
						generatePage()
					}else{
                        layer.msg("删除失败"+response.message)
					}

				},
				error:function(response){

                    layer.msg(response.status+" "+response.statusText);
				}
			})
            // 关闭模态框
            $("#menuConfirmModal").modal("hide");
		})

	})
</script>

<body>

	<%@ include file="/WEB-INF/public/admin-nav.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/public/admin-sidebar.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<div class="panel panel-default">
					<div class="panel-heading">
						<i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
						<div style="float: right; cursor: pointer;" data-toggle="modal"
							data-target="#myModal">
							<i class="glyphicon glyphicon-question-sign"></i>
						</div>
					</div>
					<div class="panel-body">
						<!-- 这个ul标签是zTree动态生成的节点所依附的静态节点 -->
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	
<%@include file="modal-menu-add.jsp"%>
	<%@include file="modal-menu-confirm.jsp"%>
	<%@include file="modal-menu-edit.jsp"%>
</body>
</html>