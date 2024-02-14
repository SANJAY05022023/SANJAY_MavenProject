package com.gl.controller;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.model.Employee;

@Controller
public class EmployeeController {

	@RequestMapping("/")
	public String home() {
		return"home";
	}
	//----------------------------Show all ------------------------//
	@RequestMapping("/show-all")
	public String showAll(Model data) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();

		Session session = factory.openSession();
		try {
			Query q1 = session.createQuery("from Employee");
			List<Employee> emp = q1.getResultList();
			data.addAttribute("emp",emp);
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
		}
		return"show-all-form";
	}
	
	@RequestMapping("/add-employee")
	public String addEmployee() {
		return"add-employee-form";
	}
	//------------------------ Insert ---------------------//
	@PostMapping("/employee-insert")
	public String employeeInsert(@RequestParam String employeeName,@RequestParam String employeeAddress,@RequestParam int employeePhone,@RequestParam int employeeSalary) {
		// session factory
		SessionFactory factory = new Configuration().configure().buildSessionFactory();

		Session session = factory.openSession();
		try {
			Transaction tx = session.beginTransaction();
			Employee E1 = new Employee(employeeName, employeeAddress, employeePhone, employeeSalary);
			session.save(E1);
			tx.commit();
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
		}
		return"home";
	}
	
	//-----------------------Update---------------------//
	@GetMapping("/update-employee-form")
	public String updateEmployeeForm(@RequestParam int employeeId,Model data) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();

		Session session = factory.openSession();
		try {
			Employee updateEmployee = session.get(Employee.class, employeeId);
			data.addAttribute("employee",updateEmployee);
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
		}
		return"update-employee";
	}
	
	@PostMapping("/update-save-employee")
	public String updateSaveEmployee(@RequestParam int employeeId,@RequestParam String employeeName,@RequestParam String employeeAddress,@RequestParam int employeePhone,@RequestParam int employeeSalary) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();

		Session session = factory.openSession();
		try {
			Transaction tx = session.beginTransaction();
			Employee e1 = new Employee(employeeId, employeeName, employeeAddress, employeePhone, employeeSalary);
			session.update(e1);
			tx.commit();
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
		}
		return"home";
	}
	
	@GetMapping("/delete-employee")
	public String deleteEmployee(@RequestParam int employeeId,Model data) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		try {
			Transaction tx = session.beginTransaction();
			Employee e1 = new Employee(employeeId, null, null, 0, 0);
			session.delete(e1);
			tx.commit();
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
		}
		return "home";
	}
}
