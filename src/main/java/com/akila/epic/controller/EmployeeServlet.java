package com.akila.epic.controller;

import com.akila.epic.dao.EmployeeDao;
import com.akila.epic.model.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;

@WebServlet(name = "EmployeeServlet", value = "/signup")
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID=1L;

    private EmployeeDao employeeDao=new EmployeeDao();

    public EmployeeServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
        RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/views/employeeRegister.jsp");
        dispatcher.forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String gender = request.getParameter("gender");
        String password = request.getParameter("pwd");
        String confirmPassword = request.getParameter("confirmPassword");

        Employee employee=new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setPhoneNumber(phoneNumber);
        employee.setGender(gender);
        employee.setPassword(password);
        employee.setConfirmPassword(confirmPassword);

        try {
            boolean feedback=employeeDao.registerEmployee(employee);
            System.out.println("______________");
            if (feedback){
                RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/views/registration_success.jsp");
                dispatcher.forward(request, response);
            }else {
                RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/views/employeeRegister.jsp");
                request.setAttribute("msg",employee);
                dispatcher.forward(request, response);
                System.out.println("Registration Failed...");
            }
        } catch (ClassNotFoundException | NoSuchAlgorithmException | SQLException e) {
            e.printStackTrace();
        }

//        RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/inde");
//        dispatcher.forward(request, response);

    }



}
