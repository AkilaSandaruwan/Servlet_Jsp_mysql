<%--
  Created by IntelliJ IDEA.
  User: akila
  Date: 12/20/2020
  Time: 11:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js">console.log("Akila");</script>

<script>


    function password_match(){
        let pwd = document.getElementById('password').value;
        let c_pwd = document.getElementById('confirmPassword').value;

        if (pwd!=c_pwd){
            document.getElementById('pwd_warn').innerHTML='Password dose not match!';
            return false;
            console.log("Akila");
        }else{
            document.getElementById('pwd_warn').innerHTML='';
            return true;
        }
    }

    function email_validate(){
        let mFormat = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$";
        let inputEmail = document.getElementById('email').value;

        if (inputEmail.match(mFormat)){
            document.getElementById('email_warn').innerHTML='';
            console.log("akila");
            return (true);
        }else{
            document.getElementById('email_warn').innerHTML='Invalid Email!';
            return (false);
        }
    }

    function password_validate(){
        let mFormat = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";

        let inputPassword = document.getElementById('password').value;

        if (inputPassword.match(mFormat)){
            document.getElementById('password_warn').innerHTML='';
            console.log("akila");
            return (true);
        }else{
            document.getElementById('password_warn').innerHTML='Invalid Password! (Password should contail uppercase lowercase digit characters)';
            return (false);
        }
    }

    function phoneNumber_validate(){
        let mFormat = '^07[0,1,2,5,6,7,8][0-9]{7}';
        let phone_num = document.getElementById('phone').value;

        if (!phone_num.match(mFormat) || phone_num.length!=10){
            document.getElementById('phone_warn').innerHTML='Invalid Phone Number!';
            console.log("akila");
            return (false);
        }else{
            document.getElementById('phone_warn').innerHTML='';
            return (true);
        }
    }

</script>




<div class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">

            <div class="card">
                <div class="card-header bg-dark"><h4 style="color: aliceblue">Sign-Up</h4></div>
                <div class="card-body">
                    <form action="<%= request.getContextPath()%>/signup" method="post">

                        <div class="form-group">
                            <label for="firstName">First Name: </label>
                            <input required type="text" name="firstName" class="form-control" placeholder="akila">
                        </div>
                        <div class="form-group">
                            <label for="lastName">Last Name: </label>
                            <input required type="text" name="lastName" class="form-control" placeholder="sandaruwan">
                        </div>
                        <div class="form-group">
                            <label for="email">Email: </label>
                            <input required type="email" onkeyup="email_validate()" id="email" name="email"  class="form-control" placeholder="akila@gmail.com">
                            <p id="email_warn" style="color: red; font-size:12px" ></p>
                            <c:if test = "${msg.getErrors().get('emailIsExist')!=null}">
                                <p style="font-weight: normal;font-size:12px;color: red"><c:out value = '${msg.getErrors().get("emailIsExist")}'/></p>
                            </c:if>
                            <c:if test = "${msg.getErrors().get('invalidEmail')!=null}">
                                <p style="font-weight: normal;font-size:12px;color: red"><c:out value = '${msg.getErrors().get("invalidEmail")}'/></p>
                            </c:if>
                        </div>
                        <div class="form-group">
                            <label for="phoneNumber">Phone: </label>
                            <input required type="text" onkeyup="phoneNumber_validate()" id="phone" name="phoneNumber"  class="form-control" placeholder="0771234567">
                            <p id="phone_warn" style="color: red; font-size:12px" ></p>
                            <c:if test = "${msg.getErrors().get('phoneNumberIsExist')!=null}">
                                <p style="font-weight: normal;font-size:12px;color: red"><c:out value = '${msg.getErrors().get("phoneNumberIsExist")}'/></p>
                            </c:if>
                            <c:if test = "${msg.getErrors().get('invalidPhoneNumber')!=null}">
                                <p style="font-weight: normal;font-size:12px;color: red"><c:out value = '${msg.getErrors().get("invalidPhoneNumber")}'/></p>
                            </c:if>
                        </div>
                        <div class="form-group">
                            <label>Gender :</label>
                            <select name="gender" id="gen" class="form-control">
                                <option value="male">Male</option>
                                <option value="female">Female</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Password: </label>
                            <input required type="password" onkeyup="password_validate()" id="password" name="pwd" class="form-control" placeholder="Ucsc@123">
                            <p id="password_warn" style="color: red; font-size:12px" ></p>
                            <c:if test = "${msg.getErrors().get('invalidPassword')!=null}">
                                <p style="font-weight: normal;font-size:12px;color: red"><c:out value = '${msg.getErrors().get("invalidPassword")}'/></p>
                            </c:if>

                        </div>
                        <div class="form-group">
                            <label for="confirmPassword">Confirm Password: </label>
                            <input required type="password" onkeyup="password_match()" id="confirmPassword" name="confirmPassword" class="form-control" placeholder="Ucsc@123">
                            <p id="pwd_warn" style="color: red; font-size:12px" ></p>
                            <c:if test = "${msg.getErrors().get('unmatchedPassword')!=null}">
                                <p style="font-weight: normal;font-size:12px;color: red"><c:out value = '${msg.getErrors().get("unmatchedPassword")}'/></p>
                            </c:if>
                        </div>
                        <button type="submit" class="btn btn-dark">Register</button>
                    </form>



                </div>
                <%--                <div class="card-footer"><a href="logout.php" class="btn btn-dark btn-sm">--%>
                <%--                    <span class="glyphicon glyphicon-log-out"></span> Log-Out--%>
                <%--                </a></div>--%>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>





<%--    <link rel="stylesheet" href="common.css">--%>

<%--    <title>register</title>--%>
<%--</head>--%>
<%--<body>--%>


<%--</body>--%>
<%--</html>--%>



