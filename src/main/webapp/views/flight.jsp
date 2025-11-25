<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Flight</title>
</head>
<body>
<h1>Danh sách chuyến bay</h1>
<%--============================ Tìm kiếm ============================--%>
<%--Tìm theo tên--%>
<form method="get" action="${pageContext.request.contextPath}/flightController/findByName">
    <input type="text" name="flightName" placeholder="Nhập tên chuyến bay"/>
    <button type="submit">Tìm theo tên</button>
</form>

<%--Tìm theo điểm xuất phát , điểm đến--%>
<form method="get" action="${pageContext.request.contextPath}/flightController/findByRoute">
    <input type="text" name="from" placeholder="Điểm xuất phát">
    <input type="text" name="to" placeholder="Điểm đến">
    <button type="submit">Tìm theo tuyến</button>
</form>

<%--============================ Hiển thị danh sách ============================--%>
<table border="1">
    <thead>
    <tr>
        <th>No</th>
        <th>Flight Id</th>
        <th>Name</th>
        <th>Starting Point</th>
        <th>Destination</th>
        <th>Departure Date</th>
        <th>Travel Time</th>
        <th>Time Unit</th>
        <th>Travel Image</th>
        <th>Status</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${listFlight}" var="flight" varStatus="loop">
        <tr>
            <td>${(pageNumber - 1) * pageSize + loop.index + 1}</td>
            <td>${flight.flightId}</td>
            <td>${flight.flightName}</td>
            <td>${flight.startingPoint}</td>
            <td>${flight.destination}</td>
            <td>${flight.depatureDate}</td>
            <td>${flight.travelTime}</td>
            <td>${flight.timeUnit}</td>
            <td>
                <img src="${flight.travelImage}" alt="${flight.flightName}" height="50px" width="50px"></td>
                <%--============================ Trạng thái ============================--%>
            <td>
                <c:choose>
                    <c:when test="${flight.status == 0}">Đã hủy bỏ</c:when>
                    <c:when test="${flight.status == 1}">Đang thực hiện</c:when>
                    <c:when test="${flight.status == 2}">Đã hoàn thành</c:when>
                </c:choose>

                    <%-- Nếu chưa hoàn thành thì status !=2 thì cho phép đổi trạng thái   --%>
                <c:if test="${flight.status !=2}">
                    <form method="POST"
                          action="${pageContext.request.contextPath}/flightController/updateStatus">

                        <input type="hidden" name="flightId" value="${flight.flightId}"/>

                        <select name="status">
                            <option value="0">Đã hủy bỏ</option>
                            <option value="1">Đang thực hiện</option>
                            <option value="2">Đã hoàn thành</option>
                        </select>

                        <button type="submit">Cập nhật trạng thái</button>
                    </form>
                </c:if>
            </td>

            <td>
                    <%--============================ update ============================--%>
                <a href="${pageContext.request.contextPath}/flightController/initUpdate?flightId=${flight.flightId}">Update</a>
                    <%--============================ Xóa ============================--%>
                <a href="${pageContext.request.contextPath}/flightController/delete?flightId=${flight.flightId}">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>
<%--============================ Phân trang ============================--%>
<div>
    <c:forEach var="i" begin="1" end="${totalPage}">
        <a href="${pageContext.request.contextPath}/flightController/findAll?pageNumber=${i}&pageSize=${pageSize}">${i}</a>
    </c:forEach>
</div>
<%--============================ Thêm mới ============================--%>
<div>
    <a href="${pageContext.request.contextPath}/flightController/initCreate">Create new Product</a>
</div>

<a href="${pageContext.request.contextPath}/flightController/findAll">Back</a>
</body>
</html>

