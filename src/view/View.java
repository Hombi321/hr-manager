package view;

import db.EmployeeDB;
import models.Employee;
import util.HRManagerUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Scanner;

public class View {

    private static final Scanner scanner = new Scanner(System.in);
    private static EmployeeDB db = new EmployeeDB();
    private static final String LINE = "--------------------------------------";


    /**
     * Show the interface to add a employee to the db
     */
    public void showAddEmployee() {
        String surname;
        String prename;
        String jobDescription;
        double salery = 0.0;
        Date birthday = null;
        Date employementDate = null;
        boolean realSalery = false;
        boolean realBirthday = false;
        boolean realEmploymentDate = false;

        Employee employee = new Employee();
        System.out.println(LINE);
        System.out.println("-------------" + " Add Employee " + "-------------");
        System.out.println(LINE);

        System.out.print("Enter Surname of the Employee:  ");
        surname = scanner.nextLine();
        System.out.print("Enter Prename of the Employee:  ");
        prename = scanner.nextLine();
        System.out.print("Enter Jobdescription of the Employee:  ");
        jobDescription = scanner.nextLine();
        System.out.print("Enter Salery of the Employee:  ");
        while(!scanner.hasNextDouble()) {
            scanner.next();
            System.out.println("Wrong salery try again");
        }
            salery = scanner.nextDouble();

        while(realBirthday == false){
            System.out.print("Enter Birthday of the Employee Format:(dd.MM.yyyy):  ");
            String userInput = scanner.next();
            try{
                birthday = HRManagerUtil.formatter.parse(userInput);
                realBirthday = true;
            } catch (ParseException e) {
                System.out.println("Invalid Dateformat pleas try again");
                realBirthday = false;
            }
        }

        while (realEmploymentDate == false){
            System.out.print("Enter Employement Date of the Employee. Format:(dd.MM.yyyy):  ");
            String userInput = scanner.next();

            try{
                employementDate = HRManagerUtil.formatter.parse(userInput);
                realEmploymentDate = true;
            } catch (ParseException e) {
                System.out.println("Invalid Dateformat pleas try again");
                realEmploymentDate = false;
            }
        }
        Employee e = new Employee(prename, surname, jobDescription, birthday, salery, employementDate);
        db.addEmployee(e);
        //TODO: implement
    }

    /**
     * Show the interface to edit a employee to the db
     */
    public void showEditEmployee() {
        System.out.println(LINE);
        System.out.println("-------------" + " Edit Employee " + "-------------");
        System.out.println(LINE);
        System.out.println("Enter the ID of the employee, which you would like to edit:  ");
        String editEmployeId = scanner.nextLine();
        List<Employee> employeeList = db.getEmployees();
        Employee editEmployee = null;
        int indexOfEmployee = 0;
        for (Employee singleEmployee : employeeList) {
            if (singleEmployee.getId().equals(editEmployeId)) {
                editEmployee = singleEmployee;
                System.out.println(editEmployee.getPrename());
            }
        }
        System.out.println("Prename: " + editEmployee.getPrename());
        System.out.println("Change? (Y/N): ");
        char decisino = scanner.next().charAt(0);
        switch (decisino) {
            case 'Y':
                System.out.print("New Prename:  ");
                String newPrename = scanner.nextLine();
                editEmployee.setPrename(newPrename);
                break;
            case 'N':
                System.out.println("Nothing to change");
                break;
        }
        String temp = scanner.nextLine();
        editEmployee.setPrename(temp);
        db.updateEmployee(editEmployee);
    }

    /**
     * Show the list of all employees from the db
     */
    public void showListEmployees() {
        List<Employee> employeeList = db.getEmployees();
        System.out.println("****************************************************************");
        for (Employee singleEmployee : employeeList) {
            System.out.println("Prename: " + singleEmployee.getPrename());
            System.out.println("Surname: " + singleEmployee.getSurname());
            System.out.println("Birthdate: " + singleEmployee.getBirthdate());
            System.out.println("Salery: " + singleEmployee.getSalary());
            System.out.println("Job Description: " + singleEmployee.getJobDescription());
            System.out.println("Employement Date: " + singleEmployee.getEmploymentDate());
            System.out.println("Employee ID: " + singleEmployee.getId());
            System.out.println("****************************************************************");

        }
    }

    /**
     * Show the interface to delete a employee
     */
    public void showDeleteEmployee() {
        System.out.println(LINE);
        System.out.println("-------------" + " Delete Employee " + "-------------");
        System.out.println(LINE);
        System.out.println("Enter ID from Employee which you want to delete: ");
        String id = scanner.nextLine();
        int index;
        List<Employee> employeeList = db.getEmployees();
        Employee employeeToDelete = null;
        for (Employee employee : employeeList) {
            System.out.println(employee.getId());

            if (employee.getId().equals(id)) {
                employeeToDelete = employee;
                index = employeeList.indexOf(employee);


            }
        }
        if (employeeToDelete != null) {
            db.deleteEmployee(employeeToDelete);
        }

        //TODO: implement
    }

    /**
     * Internal method to print out a employee
     *
     * @param employee to show
     */
    private void showEmployee(Employee employee) {
        //TODO: implement
    }

}
