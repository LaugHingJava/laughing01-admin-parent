
//点击显示确认是否删除
function showConfirmModal(roleArray){

    $("#confirmModal").modal("show")
    // 清除旧的数据
    $("#roleNameDiv").empty();
    // 在全局变量范围创建数组用来存放角色id
    window.roleIdArray = [];

    for(var i=0;i<roleArray.length;i++){
        var role = roleArray[i];
        var roleName = role.roleName;
        $("#roleNameDiv").append(roleName+"<br/>")//删除提示框显示的名字
        var roleId = role.roleId
        // 调用数组对象的push()方法存入新元素  这个数组到时候传入给删除按钮然后提交时候使用
        window.roleIdArray.push(roleId);
    }

}

function generatePage() {
    var pageInfo = getPageInfoRemote(); //获取分页数据
    fillTableBody(pageInfo);//填充表格
}
//请求后端拿到json数据
function getPageInfoRemote() {

    var ajaxResult=$.ajax({   //返回数据
        url: "role/get/page/info.json",
        type: "post",
        data:{
            "pageNum":window.pageNum,
            "pageSize":window.pageSize,
            "keyword":window.keyword
        },
        async:false, //同步请求
        dataType:"json"
    })

    console.log(ajaxResult);
    // 判断当前响应状态码是否为200
    var statusCode = ajaxResult.status;
    // 如果当前响应状态码不是200，说明发生了错误或其他意外情况，显示提示消息，让当前函数停止执行

    if(statusCode!=200){
        layer.msg("失败！响应状态码="+statusCode+" 说明信息="+ajaxResult.statusText);
        return null;
    }
    // 从resultEntity 中获取result 属性
    var resultEntity = ajaxResult.responseJSON;
    var result = resultEntity.result;
    //判断返回json是否成功
    if(result=="FAILED"){
        layer.msg(resultEntity.message);
        return null;
    }
    var pageInfo = resultEntity.data; //返回的json数据
    return pageInfo;

}

//填充表格1
function fillTableBody(pageInfo) {
    //q清空表单的数据
    $("#rolePageBody").empty();
    //清空导航条
    $("#Pagination").empty();
    //翻页选择按钮取消打钩
    $("#summaryBox").prop('checked', false);
    // 判断pageInfo 对象是否有效
    if(pageInfo == null || pageInfo == undefined || pageInfo.list == null || pageInfo.list.length == 0) {

        $("#rolePageBody").append("<tr><td colspan='4' align='center'>抱歉！没有查询到您搜索的数据！</td></tr>");
        return ;
    }

    for(var i=0;i<pageInfo.list.length;i++){
        var role = pageInfo.list[i];
        var roleId = role.id;
        var roleName = role.name;

        var tdRoleId = "<td>"+roleId+"</td>";
        var tdCheckBox = "<td><input class='itemBox' id="+roleId+" type=\"checkbox\"></td>";
        var tdRoleName = "<td>"+roleName+"</td>";
        var tdButton="\t<td>\n" +
            "\t\t\t\t\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-success btn-xs\"><i class=\" glyphicon glyphicon-check\"></i></button>\n" +
            "\t\t\t\t\t\t\t\t\t\t<button type=\"button\" id="+roleId+" class=\"btn btn-primary btn-xs pencilBtn\"><i class=\" glyphicon glyphicon-pencil\"></i></button>\n" +
            "\t\t\t\t\t\t\t\t\t\t<button type=\"button\" id="+roleId+" class=\"btn btn-danger btn-xs removeBtn\"><i class=\" glyphicon glyphicon-remove\"></i></button>\n" +
            "\t\t\t\t\t\t\t\t\t</td>"
        var tr ="<tr>"+tdRoleId+tdCheckBox+tdRoleName+tdButton+"</tr>"
        $("#rolePageBody").append(tr)
    }

    // 生成分页导航条
    generateNavigator(pageInfo);

}

//生成分页导航
function generateNavigator(pageInfo) {
    // 获取总记录数
    var totalRecord = pageInfo.total;
    // 声明相关属性
    var properties = {
        "num_edge_entries": 3,
        "num_display_entries": 5,
        "callback": paginationCallBack,
        "items_per_page": pageInfo.pageSize,
        "current_page": pageInfo.pageNum - 1,
        "prev_text": "上一页",
        "next_text": "下一页"
    }
    // 调用pagination()函数
    $("#Pagination").pagination(totalRecord, properties);
}

//翻页时候回调的函数
function paginationCallBack(pageIndex, jQuery) {
    // 修改window 对象的pageNum 属性
    window.pageNum = pageIndex + 1;
    // 调用分页函数
    generatePage();
// 取消页码超链接的默认行为
    return false;


}


