<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wyx11
  Date: 2017-5-20
  Time: 上午 1:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>出租表单</title>
</head>
<body onload="minDate()">


<script>
    function minDate() {

        clock = nowDate();
        document.getElementById("end").setAttribute("min", clock);

    }

    function nowDate() {//可选的最小时间

        var now = new Date();
        var year = now.getFullYear();       //年
        var month = now.getMonth() + 1;     //月
        var day = now.getDate();            //日
        var hh = now.getHours();            //时
        var mm = now.getMinutes();          //分


        var clock = year + "-";
        if (month < 10)
            clock += "0";
        clock += month + "-";
        if (day < 10)
            clock += "0";
        clock += day + "T";

        if (hh < 10)
            clock += "0";
        clock += hh + ":";

        if (mm < 10)
            clock += '0';
        clock += mm;

        return clock;
    }
</script>
<p>
    <a href="menu.jsp">返回到主页</a>&nbsp;
    <a href="login.jsp">退出登录</a>
</p>

<form action="lease" method="post">
    <table align="center" border="1" cellspacing="1" bgcolor="#fff8dc">
        <tr>
            <td>
                选择游戏:
                <select name="gameId">
                    <c:forEach items="${gameList}" var="game">
                        <option value="${game.id}">${game.name}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                价格(元/小时):<input type="number" name="price" value="0" min="0" required>
            </td>
        </tr>
        <tr>
            <td>
                账号:<input type="text" name="account" required>
            </td>
            <td>
                密码:<input type="password" name="password" required>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                请填写账号下架收回时间:<input type="datetime-local" name="end" min=""
                                   id="end" required/>
            </td>
        </tr>
        <br>
        <tr>
            <td colspan="2">请添加描述信息</td>
        </tr>
        <tr>
            <td colspan="2"><textarea name="description" style="background-color: #98b6de" rows="11"
                                      cols="71"></textarea></td>
        </tr>
        <tr>
            <td colspan="2">
                <p style="margin: auto;text-align: center">
                    <input type="submit" align="center" value="提交" style="width: 50px;height: 30px">
                    <input type="reset" align="center" value="重置" style="width: 50px;height: 30px">
                </p>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
