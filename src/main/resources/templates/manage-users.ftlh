<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Users</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>

<div class="container text-center mt-4">
    <a href="/stats/dashboard" class="btn btn-primary">Go to Dashboard</a>
</div>

<div class="container mt-5">
    <h3 class="text-center">Active Users</h3>
    <h5 class="text-center">Accounts</h5>

    <div class="table-responsive">
        <table class="table table-bordered table-hover text-center align-middle">
            <thead class="table-dark">
            <tr>
                <th>Name</th>
                <th>Role</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <#if users?? && users?size > 0>
            <#list users as user>
            <tr>
                <td>${user.firstName} ${user.lastName}</td>
                <td>${user.role}</td>
                <td>
                    <form action="/stats/users/delete/${user.username}" method="post" style="display:inline;">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                    </form>
                </td>
            </tr>
            </#list>
            <#else>
            <tr>
                <td colspan="3">No users found.</td>
            </tr>
        </#if>
        </tbody>
        </table>
    </div>
</div>

<!--<div class="container mt-5">-->
<!--    <h3 class="text-center">Admin Actions</h3>-->
<!--    <div class="list-group">-->
<!--        <a href="/stats/manage-user" class="list-group-item list-group-item-action">Manage User Access</a>-->
<!--        <a href="/stats/app-List" class="list-group-item list-group-item-action">View Applications List</a>-->
<!--    </div>-->
<!--</div>-->

</body>
</html>
