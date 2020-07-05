<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/public/admin-head.jsp" %>

<link rel="stylesheet" href="statics/css/pagination.css" />
<script type="text/javascript" src="statics/jquery/pagination.js"></script>
<script type="text/javascript" src="statics/js/role-page.js"></script>

<script type="text/javascript">
	$(function(){

	    //初始化数据
	    window.pageNum=1;
	    window.pageSize=5;
	    window.keyword="";
	    //执行分页的函数
        generatePage();


		//查询点击的方法
        $("#keywordBut").click(function(){
            window.keyword=$("#keyword").val()

            generatePage()

        })


		//点击弹出新增按钮
        $("#addRoleButton").click(function(){
            $("#addModal").modal("show");

        })

		//新增异步请求
        $("#saveRoleBtn").click(function(){
            var roleName = $(".form-signin [name='roleName']").val()
            $.ajax({
                url:"role/addRole.json",
                data:{"name":roleName},
                dataType:"JSON",
                type:"post",
                success:function(result){
                    if(result.result=="SUCCESS"){
                        layer.msg(result.message);
                        window.pageNum=999999;
                        generatePage();//增加成功跳转到最后一页
                    }else{
                        layer.msg(result.message);
                    }
                }
            })
            $(".form-signin [name='roleName']").val("")
            $("#addModal").modal("hide");
        })

        // 6.给页面上的“铅笔”按钮绑定单击响应函数，目的是打开模态框1
        // 传统的事件绑定方式只能在第一个页面有效，翻页后失效了
        // $(".pencilBtn").click(function(){
        // 	alert("aaaa...");
        // });

        // 使用jQuery对象的on()函数可以解决上面问题1
        // ①首先找到所有“动态生成”的元素所附着的“静态”元素
        // ②on()函数的第一个参数是事件类型
        // ③on()函数的第二个参数是找到真正要绑定事件的元素的选择器
        // ③on()函数的第三个参数是事件的响应函数
        $("#rolePageBody").on("click",".pencilBtn",function(){
            // 打开模态框
            $("#editModal").modal("show");

            // 获取表格中当前行中的角色名称
            var roleName = $(this).parent().prev().text();

            // 获取当前角色的id
            // 依据是：var pencilBtn = "<button id='"+roleId+"' ……这段代码中我们把roleId设置到id属性了
            // 为了让执行更新的按钮能够获取到roleId的值，把它放在全局变量上
            window.roleId = this.id;

            // 使用roleName的值设置模态框中的文本框
            $("#editModal [name=roleName]").val(roleName);
        });

        //点击修改把用户输入的内容提交的后台
        $("#updateRoleBtn").click(function(){

            var roleName = $("#modifyInput").val();
            $.ajax({
				url:"role/modifyRole.json",
				data:{"id":window.roleId,"name":roleName},
				dataType:"json",
				type:"post",
				success:function(result){
                    if(result.result=="SUCCESS"){
                        layer.msg(result.message);
                        generatePage();//增加成功跳转到最后一页1
                    }else{
                        layer.msg(result.message);
                    }
				}
			})
            $("#editModal").modal("hide");
		})

		//给单跳删除按钮添加点击事件1
        $("#rolePageBody").on("click",".removeBtn",function(){
            // 从当前按钮出发获取角色名称
            var roleName = $(this).parent().prev().text();
            var roleArray=[{
                roleId:this.id,
				roleName:roleName
			}]
			//调用删除显示框并且把删除id和name的数组传进去显示
            showConfirmModal(roleArray)
		})

        //点击确认删除把id数组参数发送后端url删除数据
        $("#removeRoleBtn").click(function(){
            // 从全局变量范围获取roleIdArray，转换为JSON字符串
            var requestBody = JSON.stringify(window.roleIdArray);
            $.ajax({
                url:"role/delRole.json",
                data:requestBody,
                dataType:"json",
                type:"post",
                contentType:"application/json;charset=UTF-8",
                success:function(result){
                    if(result.result=="SUCCESS"){
                        layer.msg(result.message);
                        generatePage();//增加成功跳转到最后一页1
                    }else{
                        layer.msg(result.message);
                    }
                }
            })
            // 关闭模态框
            $("#confirmModal").modal("hide");
        })

        //给全选按钮点击事件一点要么全选要么全部取消1
        $("#summaryBox").click(function(){
            // ①获取当前多选框自身的状态
            var currentStatus = this.checked;
            // ②用当前多选框的状态设置其他多选框
            $(".itemBox").prop("checked", currentStatus);
        })

        //如果选择5个后则全选按钮也打钩
        $("#rolePageBody").on("click",".itemBox",function(){
            // 获取当前已经选中的.itemBox的数量
            var checkedBoxCount = $(".itemBox:checked").length;
            // 获取全部.itemBox的数量
            var totalBoxCount = $(".itemBox").length;
            // 使用二者的比较结果设置总的checkbox
            $("#summaryBox").prop("checked", checkedBoxCount == totalBoxCount);
        })

        //给删除按钮绑定事件 获取遍历选中的按钮 获取id和name然后存入数组 传给开是否删除窗口1
        $("#batchRemoveBtn").click(function(){
            // 创建一个数组对象用来存放后面获取到的角色对象
            var roleArray = [];
            $(".itemBox:checked").each(function(){
                var roleId = this.id;
                var roleName = $(this).parent().next().text();
                roleArray.push({
                    "roleId":roleId,
                    "roleName":roleName
                });
            })

            // 检查roleArray的长度是否为0
            if(roleArray.length == 0) {
                layer.msg("请至少选择一个执行删除");
                return ;
            }
            // 调用专门的函数打开模态框
            showConfirmModal(roleArray);

        })

	})
</script>


<body>

	<%@ include file="/WEB-INF/public/admin-nav.jsp" %>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/public/admin-sidebar.jsp" %>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
					</div>
					<div class="panel-body">
						<form class="form-inline" role="form" style="float:left;">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">查询条件</div>
									<input id="keyword" class="form-control has-success" type="text" value="" placeholder="请输入查询条件">
								</div>
							</div>
							<button type="button" id="keywordBut" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
						</form>
						<button type="button" id="batchRemoveBtn" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
						<button type="button" id="addRoleButton" class="btn btn-primary" style="float:right;" ><i class="glyphicon glyphicon-plus"></i> 新增</button>
						<br>
						<hr style="clear:both;">
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
								<tr>
									<th width="30">#</th>
									<th width="30"><input id="summaryBox" type="checkbox"></th>
									<th>名称</th>
									<th width="100">操作</th>
								</tr>
								</thead>
								<tbody  id="rolePageBody">
								<%--<tr>--%>
									<%--<td>1</td>--%>
									<%--<td><input type="checkbox"></td>--%>
									<%--<td>PM - 项目经理</td>--%>
									<%--<td>--%>
										<%--<button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>--%>
										<%--<button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>--%>
										<%--<button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>--%>
									<%--</td>--%>
								<%--</tr>--%>

								</tbody>
								<tfoot>
								<tr>
									<td colspan="6" align="center">
										<div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
									</td>
								</tr>

								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<%@include file="modal-role-add.jsp"%>
<%@include file="modal-role-edit.jsp"%>
<%@include file="modal-role-confirm.jsp"%>
</body>


</html>