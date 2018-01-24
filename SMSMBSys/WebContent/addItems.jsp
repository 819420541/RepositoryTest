<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加商品信息</title>
</head>
<body>
	
	<!-- 显示错误信息 -->
		<c:if test="${ allErrors!=null }">
			 <c:forEach items="${ allErrors }" var="error">
			 	${ error.defaultMessage }<br/>
			 </c:forEach>
		</c:if>
	
	<form action="${ pageContext.request.contextPath }/items/addItemsSubmit.action" 
		method="post"  enctype="multipart/form-data">
	
		<table width="100%" border="1">
			<tr>
				<td>商品编号</td>
				<td>
					<input type="text" name="id"/>
				</td>
			</tr>
		
			<tr>
				<td>商品名称</td>
				<td>
					<input type="text" name="name"/>
				</td>
			</tr>
			<tr>
				<td>商品价格</td>
				<td>
					<input type="text" name="price"/>
				</td>
			</tr>
			
			<tr>
				<td>生产日期</td>
				<td>
					<input type="text" name="createtime"/>
				</td>
			</tr>
			
			<tr>
				<td>上传图片</td>
				<td>
					<input type="file" name="pic" />
				</td>
			</tr>
			
			<tr>
				<td>
					<input type="submit" value="保存">
				</td>
			</tr>
							
		</table>		
	</form>
		
</body>
</html>