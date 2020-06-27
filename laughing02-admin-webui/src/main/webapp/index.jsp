<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>
    <script type="text/javascript" src="js/jquery-2.1.1.min.js">
    </script>

</head>
<body>
<h2>bb ssa!</h2>

<a href="test/aaa.html">test</a>
<a href="/test/exc">bb</a>
<p>ggggg</p>
<%--<button onclick="ajax1()">ajax1</button>--%>
<button onclick="ajax2()">ajax2</button>
<button onclick="ajax3()">ajax3</button>
<button onclick="ajax4()">测试xml异常</button>
<button onclick="ajax5()">测试req</button>

</body>

<script>

    function ajax5(){
        $.ajax({
            url:"test/req",
            data:"json",
            success:function(result){
                alert(result)
            }

        })

    }

    function ajax4(){
        $.ajax({
            url:"test/exc",
            data:"json",
            success:function(result){
                alert("成功")
            }

        })

    }

    function ajax2(){
        var student ={
            stuId:1,
            stuName:"tom",
            stuAge:215,

        }
        var json = JSON.stringify(student)
        alert(json)
        $.ajax({
            url:"test/ajax.json",
            dataType:"json",
            data:json,
            contentType:"application/json;charset=UTF-8",
            type:"post",

            success:function(result){

                alert(result.stuName)
            },
            error:function (result) {
                alert(result)
            }


        })

    }


    function ajax3(){
        var student ={
            stuId:1,
            stuName:"tom",
            stuAge:215,
            address:{
                province:"shangdong",
                city:"heze",
                street:"hello"
            },
            schoolLists:[
                {
                    schoolName:"学校1",
                    schoolSize:3000
                }, {
                    schoolName:"学校2",
                    schoolSize:3000
                }

            ],
            scoreMap:{
                map1:"mmm",
                map2:"mmm2",
                map3:"mmm2",
            }
        }


        var json = JSON.stringify(student)
        alert(json)
        $.ajax({
            url:"test/ajaxObject.json",
            dataType:"json",
            data:json,
            contentType:"application/json;charset=UTF-8",
            type:"post",

            success:function(result){
                if(result.result="success"){
                    alert(result.data)
                }

            },
            error:function (result) {
                alert(result)
            }


        })

    }
</script>

</html>
