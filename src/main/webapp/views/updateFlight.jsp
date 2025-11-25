<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Flight</title>
</head>
<body>
<style>
    .error {
        color: red;
    }
</style>
<h2>Update Flight</h2>

<form:form modelAttribute="flightUpdateDTO" action="${pageContext.request.contextPath}/flightController/update"
           method="post" enctype="multipart/form-data">

    <form:label path="flightId">Flight Id</form:label>
    <form:input path="flightId" readonly="true"/>
    <br/>

    <form:label path="flightName">Flight Name</form:label>
    <form:input path="flightName"/>
    <form:errors path="flightName" cssClass="error"/> <br/>

    <form:label path="startingPoint">Starting Point</form:label>
    <form:input path="startingPoint"/>
    <form:errors path="startingPoint" cssClass="error"/> <br/>

    <form:label path="destination">Destination</form:label>
    <form:input path="destination"/>
    <form:errors path="destination" cssClass="error"/> <br/>

    <form:label path="departureDate">Departure Date</form:label>
    <form:input path="departureDate" type="date"/>
    <form:errors path="departureDate" cssClass="error"/> <br/>

    <form:label path="travelTime">Travel Time</form:label>
    <form:input path="travelTime"/>
    <form:errors path="travelTime" cssClass="error"/> <br/>

    <form:label path="timeUnit">Time Unit</form:label>
    <form:input path="timeUnit"/>
    <form:errors path="timeUnit" cssClass="error"/> <br/>


    <form:label path="travelImageFile">Travel Image File</form:label>
    <form:input path="travelImageFile" type="file"/>
    <form:errors path="travelImageFile" cssClass="error"/> <br/>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                 

    <form:label path="status">Status</form:label>
    <form:select path="status">
        <form:option value="0">Hủy</form:option>
        <form:option value="1">Đang thực hiện</form:option>
        <form:option value="2">Hoàn thành</form:option>
    </form:select>
    <br/>
    <br/>
    <input type="submit" value="Update FLight"/>

</form:form>
</body>
</html>
