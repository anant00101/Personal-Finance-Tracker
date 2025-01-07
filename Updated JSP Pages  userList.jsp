<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List - Personal Finance Tracker</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <header>
        <h1>User List</h1>
    </header>
    <main>
        <table border="1">
            <thead>
                <tr>
                    <th>Username</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="username" items="${userList}">
                    <tr>
                        <td>${username}</td>
                        <td><a href="/users?username=${username}">View Details</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </main>
    <footer>
        <p>&copy; 2025 Personal Finance Tracker</p>
    </footer>
</body>
</html>

