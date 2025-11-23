<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Flight</title>
</head>
<body>
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
            <td>${loop.index+1}</td>
            <td>${flight.flightId}</td>
            <td>${flight.flightName}</td>
            <td>${flight.startingPoint}</td>
            <td>${flight.destination}</td>
            <td>${flight.depatureDate}</td>
            <td>${flight.travelTime}</td>
            <td>${flight.timeUnit}</td>
            <td><img src="${flight.travelImage}" alt="${flight.flightName}" height="50px" width="50px"></td>
            <td>${flight.status}</td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>
</html>
