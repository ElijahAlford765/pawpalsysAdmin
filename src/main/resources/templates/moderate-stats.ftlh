<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>

<div class="container mt-4">
    <div class="row mb-3">
        <div class="col d-flex justify-content-between align-items-center">
            <h2>Statistics</h2>
            <a href="/admin-dashboard.ftlh" class="btn btn-primary">Back to Dashboard</a>
        </div>
    </div>

    <#if statsList?size == 0>
    <div class="alert alert-info">No usage statistics available.</div>
    <#else>
    <div class="mb-3">
        <h5>Overall Average Rating: ${overallAvg?string("0.0")}</h5>
    </div>

    <div class="row">
        <#list statsList as stat>
        <div class="col-md-6 col-lg-4">
            <div class="card mb-4 shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Provider ID: ${stat.providerId}</h5>
                    <p><strong>Total Customers:</strong> ${stat.totalCustomers}</p>
                    <p><strong>Average Rating:</strong> ${stat.avgRating}</p>
                    <p><strong>Review ID:</strong> ${stat.reviewId}</p>
                    <p><strong>Service ID:</strong> ${stat.serviceId}</p>
                    <p><strong>Pet ID:</strong> ${stat.petId}</p>
                    <p><strong>Booking ID:</strong> ${stat.bookingId}</p>
                    <div class="d-flex justify-content-end mt-3">
                        <a href="/stats/update/${stat.id}" class="btn btn-sm btn-warning me-2">Edit</a>
                        <a href="/stats/delete/${stat.id}" class="btn btn-sm btn-danger">Delete</a>
                    </div>
                </div>
            </div>
        </div>
    </#list>
</div>
</#if>

<!-- Active User Accounts Chart -->
<div class="card mt-4 p-3 shadow-sm">
    <h6>Active User Accounts - Monthly Overview</h6>
    <canvas id="activeUserChart" width="400" height="150"></canvas>
</div>

</div>

<script>
    // Data for the active user accounts chart
    const ctx = document.getElementById('activeUserChart').getContext('2d');
    new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['Jan', 'Feb', 'Mar', 'Apr'], // Labels representing months
            datasets: [{
                label: 'Active User Accounts',
                data: [${activeUsersJan}, ${activeUsersFeb}, ${activeUsersMar}, ${activeUsersApr}], // Dynamic data passed from backend
                borderColor: 'green',
                backgroundColor: 'rgba(0, 128, 0, 0.1)', // Semi-transparent background
                fill: true
            }]
        },
        options: {
            responsive: true
        }
    });
</script>

</body>
</html>