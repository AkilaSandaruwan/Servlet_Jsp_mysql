<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="common.css">

    <title>login</title>
</head>
<body>
<div class="container">
    <div class="row" id="titlebar">
        <h1>JSP_SERVLET_MYSQL</h1>
    </div>
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="card">
                <div class="card-header bg-dark"><h4 style="color: aliceblue">Sign-In</h4></div>
                <div class="card-body">
                    <form action="#" method="post">
                        <div class="form-group">
                            <label >Email: </label>
                            <input type="email" name="email" class="form-control" placeholder="akila@gmail.com">
                        </div>
                        <div class="form-group">
                            <label >Password: </label>
                            <input type="password" name="pwd" class="form-control" placeholder="Ucsc@123" >
                        </div>
                        <button type="submit" class="btn btn-dark">Login</button></br>
                        </br>

                    </form>
                </div>
                <div class="card-footer">
                    Don't you have an account? <a href="signup"><h6 style="color:black;text-decoration: none; font-weight: bold">Sign-Up</h6></a>
                </div>
            </div>
        </div>
    <div class="col-md-3"></div>
</div>
</div>
</body>
</html>