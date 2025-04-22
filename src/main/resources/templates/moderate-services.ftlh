<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Moderate Services & Users</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .btn-approve {
            background-color: #28a745;
            color: white;
        }
        .btn-remove {
            background-color: #dc3545;
            color: white;
        }
        .bg-black {
            background-color: #343a40;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Moderate Services & Users</h1>

    <!-- Pending Services Section -->
    <section class="mb-5">
        <h2>Pending Services for Approval</h2>
        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr><th>ID</th><th>Title</th><th>Description</th><th>Provider</th><th>Actions</th></tr>
            </thead>
            <tbody>
            <#if services?? && (services?size > 0)>
            <#list services as service>
            <tr>
                <td>${service.id}</td>
                <td>${service.title}</td>
                <td>${service.description}</td>
                <td>${service.provider.username}</td>
                <td>
                    <form action="/admin/services/approve/${service.id}" method="post" style="display:inline;">
                        <button type="submit" class="btn btn-approve btn-sm">Approve</button>
                    </form>
                    <form action="/admin/services/delete/${service.id}" method="post" style="display:inline;">
                        <button type="submit" class="btn btn-remove btn-sm">Delete</button>
                    </form>
                </td>
            </tr>
            </#list>
            <#else>
            <tr><td colspan="5" class="text-center">No pending services found.</td></tr>
        </#if>
        </tbody>
        </table>
    </section>

    <!-- Flagged Services Section -->
    <section class="mb-5">
        <div class="card">
            <div class="card-header text-white bg-black">
                <h3>Flagged Services</h3>
            </div>
            <div class="card-body">
                <ul class="list-group">
                    <#if flaggedServices?? && (flaggedServices?size > 0)>
                    <#list flaggedServices as service>
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        ${service.name} - Reported by ${service.reportCount} users
                        <div>
                            <form method="post" action="/admin/services/delete/${service.id}" style="display:inline;">
                                <button class="btn btn-remove btn-sm" type="submit">Remove</button>
                            </form>
                            <form method="post" action="/admin/services/approve/${service.id}" style="display:inline;">
                                <button class="btn btn-approve btn-sm" type="submit">Reinstate</button>
                            </form>
                        </div>
                    </li>
                </#list>
                <#else>
                <li class="list-group-item text-center">No flagged services found.</li>
            </#if>
            </ul>
        </div>

</div>
</section>

<!-- User Management Section -->
<section class="mb-5">
    <h2>User Management</h2>
    <table class="table table-striped table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>ID</th><th>Username</th><th>Email</th><th>Role</th><th>Status</th><th>Admin</th>
        </tr>
        </thead>
        <tbody>
        <#if users?? && (users?size > 0)>
        <#list users as user>
        <tr>
            <td>${user.userId}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
            <td>${user.status}</td>
            <td>${user.isAdmin?string("Yes", "No")}</td>
        </tr>
        </#list>
        <#else>
        <tr><td colspan="6" class="text-center">No users found.</td></tr>
    </#if>
    </tbody>
    </table>
</section>

<a href="/admin/dashboard" class="btn btn-secondary">Back to Dashboard</a>
</div>
</body>
</html>