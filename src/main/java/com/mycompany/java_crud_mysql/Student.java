/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_crud_mysql;

import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class Student {

    int id;
    String identificationNumber;
    String name;
    String lastNames;

    public Student(String identificationNumber, String name, String lastNames) {
        this.identificationNumber = identificationNumber;
        this.name = name;
        this.lastNames = lastNames;
    }

    public Student(String id, String identificationNumber, String name, String lastNames) {
        this.id = Integer.parseInt(id);
        this.identificationNumber = identificationNumber;
        this.name = name;
        this.lastNames = lastNames;
    }

    @Override
    public String toString() {
        return "Identificación: " + identificationNumber + "\nNombre: " + name + "\nApellidos: " + lastNames;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public boolean addStudent() {

        Conection objConecction = new Conection();
        String query = "insert into students (names,identification_number,last_names)"
                + "values (?,?,?);";
        try {

            CallableStatement cs = objConecction.getConnection().prepareCall(query);
            cs.setString(1, getName());
            cs.setString(2, getIdentificationNumber());
            cs.setString(3, getLastNames());

            cs.execute();

        } catch (SQLException e) {

            if (e.getMessage().contains("Duplicate")) {
                JOptionPane.showMessageDialog(null, "Ya existe una persona con este número de identificación: " + getIdentificationNumber());
            }
            return false;
        }
        return true;
    }

    public boolean updateStudent() {

        Conection objConecction = new Conection();
        String query = "update students set students.names = ?, students.last_names = ?,"
                + " students.identification_number = ? where id = ?;";
        try {

            CallableStatement cs = objConecction.getConnection().prepareCall(query);
            cs.setString(1, getName());
            cs.setString(2, getLastNames());
            cs.setString(3, getIdentificationNumber());
            cs.setInt(4, getId());

            cs.execute();

        } catch (SQLException e) {

            if (e.getMessage().contains("Duplicate")) {
                JOptionPane.showMessageDialog(null, "Ya existe una persona con este número de identificación: " + getIdentificationNumber());
            }
            return false;
        }
        return true;
    }

    static public ArrayList<Student> getStudents() {

        Conection objConecction = new Conection();
        String query = "SELECT id, identification_number, names, last_names FROM students;";
        ArrayList<Student> students = new ArrayList<>();

        try {

            Statement st = objConecction.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                students.add(new Student(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                ));

            }

        } catch (SQLException e) {
            return null;
        }

        return students;
    }

    static public boolean deleteStudent(int id) {
        Conection objConecction = new Conection();
        String query = "DELETE FROM students where id = ?;";

        try {
            CallableStatement cs = objConecction.getConnection().prepareCall(query);
             cs.setInt(1, id);
             
             cs.execute();
             
        } catch (SQLException e) {
            return false;
        }
        
        return true;
    }

}
