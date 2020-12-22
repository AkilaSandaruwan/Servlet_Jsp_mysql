package com.akila.epic.dao;

import com.akila.epic.model.Employee;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeDao {
    private static Connection connection = DbConnection.getDB();

    public boolean registerEmployee(Employee employee) throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {

        boolean emailValidated = false;
        boolean phoneNumberValidated = false;
        boolean passwordValidated = false;
        boolean existingEmail = false;
        boolean existingPhoneNumber = false;
        boolean insert = false;


        if (validateEmail(employee)) {
            emailValidated = true;
        }
        if (validatePhoneNumber(employee)) {
            phoneNumberValidated = true;
        }
        if (validatePassword(employee)) {
            passwordValidated = true;
        }
        if (isExistingEmail(employee)) {
            existingEmail = true;
//            System.out.println("existing email..............");
        }
        if (isExistingPhoneNumber(employee)) {
            existingPhoneNumber = true;
//            System.out.println("existing email..............");
        }
        if (phoneNumberValidated && passwordValidated && emailValidated && !existingEmail && !existingPhoneNumber) {
            insert = register(employee);
//            connection.close();
        }

        return insert;


    }

    private boolean register(Employee employee) {
        String INSERT_SQL_QUERY = "INSERT INTO employee(firstName, lastName, email, phoneNumber, gender, password) VALUES(?,?,?,?,?,?);";

        System.out.println(connection);
        boolean inserted = false;

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_SQL_QUERY);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setString(5, employee.getGender());
            preparedStatement.setString(6, getHashedPassword(employee.getPassword()));
            int rows = preparedStatement.executeUpdate();


            if (rows > 0) {
                System.out.println("Registration Success...");
                inserted = rows > 0;
            }
//            preparedStatement.setString(1, employee.getConfirmPassword());


        } catch (SQLException | NoSuchAlgorithmException throwables) {
            throwables.printStackTrace();
        }
        return inserted;
    }

    private boolean isExistingEmail(Employee employee) {
        String INSERT_SQL_QUERY = "SELECT email FROM employee WHERE email= ?";

//        System.out.println(connection);
        boolean isExist = false;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL_QUERY);
            preparedStatement.setString(1, employee.getEmail());

            ResultSet rs = preparedStatement.executeQuery();
//            System.out.println(rs);

            if (rs.next()) {
                System.out.println("The Email is an existing one...");
                employee.setErrors("emailIsExist","Email is already exist.");
                isExist = true;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isExist;

    }

    private boolean isExistingPhoneNumber(Employee employee) {
        String INSERT_SQL_QUERY = "SELECT phoneNumber FROM employee WHERE phoneNumber= ?";

//        System.out.println(connection);
        boolean isExist = false;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL_QUERY);
            preparedStatement.setString(1, employee.getPhoneNumber());

            ResultSet rs = preparedStatement.executeQuery();
//            System.out.println(rs);

            if (rs.next()) {
                System.out.println("The Phone Number is an existing one...");
                employee.setErrors("phoneNumberIsExist","Phone Number is already exist.");
                System.out.println(employee.getErrors().get("phoneNumberIsExist"));
                isExist = true;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isExist;

    }

    private String getHashedPassword(String password) throws NoSuchAlgorithmException {
//        SecureRandom random = new SecureRandom();
//        byte[] salt = new byte[16];

        MessageDigest md = MessageDigest.getInstance("SHA-512");

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
//        System.out.println(new String(hashedPassword));
        String pwd = new String(hashedPassword);


        return pwd;

    }

    private boolean validatePassword(Employee employee) {

        boolean status = false;

        if (employee.getPassword().equals(employee.getConfirmPassword()) && !employee.getPassword().equals(null) && !employee.getConfirmPassword().equals(null)) {
            String regex = "^(?=.*[0-9])"
                    + "(?=.*[a-z])(?=.*[A-Z])"
                    + "(?=.*[@#$%^&+=])"
                    + "(?=\\S+$).{8,20}$";

            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(employee.getPassword());
            status = m.matches();
            if (!status) {
                System.out.println("Password cannot be Validated...");
                employee.setErrors("invalidPassword","Invalid Password! (Password should contail uppercase lowercase digit charactors.)");
            }
        } else if (!employee.getPassword().equals(employee.getConfirmPassword())) {
            String passwordError = "Confirm Password doesn't match...";
            employee.setErrors("unmatchedPassword","Password does not match!");
            System.out.println(passwordError);
            status = false;
        }

        return status;
    }

    private boolean validatePhoneNumber(Employee employee) {

        boolean status = false;

        String regex = "^07[0,1,2,5,6,7,8][0-9]{7}";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(employee.getPhoneNumber());
        status = m.matches();
        if (!status) {
            System.out.println("Phone Number cannot be Validated...");
            employee.setErrors("invalidPhoneNumber","Invalid Phone Number!");
        }
        return status;
    }

    private boolean validateEmail(Employee employee) {

        boolean status = false;

        String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(employee.getEmail());
        status = m.matches();
        if (!status) {
            System.out.println("Email cannot be Validated...");
            employee.setErrors("invalidEmail","Invalid Email!");
        }
        return status;
    }
}
