package com.example.jdbc.jdbc4.DBManager;

import com.example.jdbc.jdbc4.DAOs.Person;
import com.example.jdbc.jdbc4.Requests.CreateRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBOperations {
    private static volatile Connection connection;
    public static Connection getconnection() throws SQLException {
        if(connection==null) {
            synchronized (DBOperations.class) {
                if (connection == null) {
                    connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/person_4","root","admin");
                }
            }
        }
            return connection;

    }
    public static void closeconnection() throws SQLException {
        if(connection!=null)
        {
            synchronized (DBOperations.class)
            {
                if(connection!=null)
                {
                    connection = null;
                }
            }
        }

    }

    public static List<Person> getPersons() throws SQLException {
        getconnection();
        Statement statement= connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from person");
        List<Person> persons = new ArrayList<>();
        while(resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int age = resultSet.getInt(3);
            String address = resultSet.getString(4);
            Person person = new Person(id, name, age, address);
            persons.add(person);
        }
        closeconnection();
        return persons;



    }
    public static void createTable(String name) throws SQLException {
        System.out.println("started..........");
        connection = getconnection();
        Statement statement  = connection.createStatement();
       boolean isCreated= statement.execute("CREATE TABLE "+name+" ( id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), age INT, " +
                "address VARCHAR(50))");
         if(isCreated)
         {
             System.out.println("Table "+name+" sucessfully created");
         }
         closeconnection();

    }

    public static void insertperson(CreateRequest request) throws SQLException {
        getconnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO person VALUES(null,?,?,?)");
        preparedStatement.setString(1,request.getName());
        preparedStatement.setInt(2,request.getAge());
        preparedStatement.setString(3, request.getAddress());
        int rows_affected = preparedStatement.executeUpdate();
        if(rows_affected>0)
        {
            System.out.println("Data inserted ");
        }
        else
        {
            System.out.println("Data not inserted");
        }
        closeconnection();




    }

    public static void deletePerson(int id) throws SQLException {
        getconnection();
        Statement statement= connection.createStatement();
        statement.execute("delete from person where id ="+id);
        closeconnection();


    }


}
