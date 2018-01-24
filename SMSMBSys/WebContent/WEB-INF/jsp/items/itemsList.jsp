<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询商品列表</title>
</head>

<script type="text/javascript">
	function deleteItems(){
		document.itemsForms.action="${pageContext.request.contextPath }/items/deleteItems.action";
		document.itemsForms.submit();
	}
</script>



<body>

	<form action="${ pageContext.request.contextPath }/items/queryItems.action" 
			method="post"  name="itemsForms">
	查询条件：
		<table width="100%" border="1">
			<tr>
				<td>商品名称<input type="text" name="itemsCustom.name">
					<select name="itemType">
						<c:forEach items="${ itemsType }" var="itemType">
							<option value="${ itemType.key }">${ itemType.value }</option>
						</c:forEach>
					</select>
				</td>
				

				<td>商品价格
					<input type="text" name="itemsCustom.priceMin"/>
					-
					<input type="text" name="itemsCustom.priceMax"/>
				</td>

				<td>生产日期
					<input type="text" name="itemsCustom.createTimeMin"/>
					-
					<input type="text" name="itemsCustom.createTimeMax"/>
					
				</td>
			</tr>
			<tr> 
				<td>
					<input type="submit" value="查询">
					<input type="button" value="批量删除" onclick="deleteItems()">
					<input type="button" value="增加" onclick="window.location.href = '${pageContext.request.contextPath }/addItems.jsp'">
				</td>
			</tr>
		</table>
		
		商品列表
		<table width="100%" border="1">
			<tr>
				<th>选择</th>
				<th>商品名称</th>
				<th>商品价格</th>
				<th>生产日期</th>
				<th>商品描述</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${ itemsList }" var="item">
				<tr>
					<td>
						<input type="checkbox" name="items_id" value="${ item.id }">
					</td>
					<td>${ item.name }</td>
					<td>${ item.price }</td>
					<td>
						<fmt:formatDate value="${ item.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>${item.detail}</td>
					<td>
					${ item.id }
						<a href="${ pageContext.request.contextPath }/items/editItems.action?id=${ item.id }">修改</a>
						<a href="${ pageContext.request.contextPath }/items/deleteItem.action?id=${ item.id }">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>

</body>
</html>