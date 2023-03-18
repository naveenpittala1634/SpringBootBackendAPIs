package com.example.jdbc.jdbc4.controllers;
import com.example.jdbc.jdbc4.DAOs.Person;
import com.example.jdbc.jdbc4.DBManager.DBOperations;
import com.example.jdbc.jdbc4.Requests.CreateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {
    //this will insert data by taking inputs as {name,age, address} from client
    @RequestMapping(value="/insertPerson",method= RequestMethod.POST)
    public   static void insertPerson(@RequestBody CreateRequest request) throws SQLException {
        DBOperations.insertperson(request);
    }


     //this will create table
    @RequestMapping(value="/createTable",method= RequestMethod.POST)
    public  void createTable(@RequestParam(value="name") String name) throws SQLException {

        //calling dao to get object form db (data object access)
        DBOperations.createTable(name);
    }

     //this will fetch data from db
    @RequestMapping(value="/getPersons",method= RequestMethod.GET)
    public  static List<Person> getPersons() throws SQLException {

        //calling dao to get object form db (data object access)
       return DBOperations.getPersons();
    }

    //this will delete data based on id in database, we will take id from the clietnt
    @RequestMapping(value="/deletePerson",method= RequestMethod.POST)
    public  static void  deletePerson(@RequestParam(value="id") int id) throws SQLException {

        DBOperations.deletePerson(id);
    }
}
