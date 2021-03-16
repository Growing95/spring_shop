<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
*{ font-size: 12pt; }
table {
	margin: 10px auto;
	border-collapse: collapse;
}

table, th, td {
	border: 1px solid black;
}
h2{text-align: center;}
div{
		width: 600px;
		margin: 0px auto;
	}
</style>
<script type="text/javascript">
	function product_add(f) {
		for (var i = 0; i < f.elements.length; i++) {
			if(f.elements[i].value==""){
				alert(f.elements[i].name + "를 입력하세요");
				f.elements[i].focus();
				return;
			}
		}
		f.action="product_add.do";
		f.submit();
	}
</script>
</head>
<body>
	<jsp:include page="top.jsp" />
	<div>
	<h2> 상품 등록 </h2>
	<form method="post" enctype="multipart/form-data">
		<table>
			<tbody>
				<tr>
					<th> 분류 </th>
					<td>
						<input type="radio" name="category" value="com001">컴퓨터
						<input type="radio" name="category" value="ele002">가전제품
						<input type="radio" name="category" value="sp003">스포츠
					</td>
				</tr>
				<tr>
					<th> 제품번호 </th>
					<td><input type="text" name="p_num"   size="20"></td>
				</tr>
				<tr>
					<th> 제품명 </th>
					<td><input type="text" name="p_name"   size="20"></td>
				</tr>
				<tr>
					<th> 판매사 </th>
					<td><input type="text" name="p_company"  size="20"></td>
				</tr>
				<tr>
					<th> 상품가격 </th>
					<td><input type="text" name="p_price"   size="20"></td>
				</tr>
				<tr>
					<th> 할인가 </th>
					<td><input type="text" name="p_saleprice"   size="20"></td>
				</tr>
				<tr>
					<th> 상품이미지S </th>
					<td><input type="file" name="p_image_s" size="20"></td>
				</tr>
				<tr>
					<th> 상품이미지L </th>
					<td><input type="file" name="p_image_l" size="20"></td>
				</tr>
				<tr>
					<td colspan="2"><b>상품내용</b></td>
				</tr>
				<tr>
					<td colspan="2">
						<textarea rows="10" cols="50" name="p_content"></textarea>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<input type="button" value="상품등록" onclick="product_add(this.form)">
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
	</div>
</body>
</html>