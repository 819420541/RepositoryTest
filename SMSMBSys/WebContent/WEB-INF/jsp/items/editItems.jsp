<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- 显示错误信息 -->
		<c:if test="${ allErrors!=null }">
			 <c:forEach items="${ allErrors }" var="error">
			 	${ error.defaultMessage }<br/>
			 </c:forEach>
		</c:if>
	
	<form action="${ pageContext.request.contextPath }/items/editItemsSubmit.action" 
		method="post"  enctype="multipart/form-data">
	
	<input type="hidden" name="id" value="${ items.id }">
		<table width="100%" border="1">
			<tr>
				<td>商品名称</td>
				<td>
					<input type="text" name="name" value="${ items.name }"/>
				</td>
			</tr>
			<tr>
				<td>商品价格</td>
				<td>
					<input type="text" name="price" value="${ items.price }"/>
				</td>
			</tr>
			
			<tr>
				<td>生产日期</td>
				<td>
					<input type="text" name="createtime" value=<fmt:formatDate value="${ item.createtime }" pattern="yyyy-MM-dd HH:mm:ss" />>
				</td>
			</tr>
			
			<tr>
				<td>上传图片</td>
				<td>
					<c:if test="${ items.pic!=null }">
						<img alt="" src="${ pageContext.request.contextPath }/uploadfile/${items.pic}" width="100" height="100">
					</c:if>
					<input type="file" name="pic" />
				</td>
			</tr>
			
			<tr>
				<td>
					<input type="submit" value="修改">
				</td>
			</tr>
							
		</table>		
	</form>
		
</body>
</html>