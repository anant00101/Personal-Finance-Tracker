<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Details - Personal Finance Tracker</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <header>
        <h1>User Details</h1>
    </header>
    <main>
        <c:if test="${userDetails != null}">
            <p><strong>Name:</strong> ${userDetails[0]}</p>
            <p><strong>Email:</strong> ${userDetails[1]}</p>
            <p><strong>Address:</strong> ${userDetails[2]}</p>
        </c:if>
        <c:if test="${userDetails == null}">
            <p>User details not found.</p>
        </c:if>
        <a href="/users">Back to User List</a>
    </main>
    <footer>
        <p>&copy; 2025 Personal Finance Tracker</p>
    </footer>
</body>
</html>
