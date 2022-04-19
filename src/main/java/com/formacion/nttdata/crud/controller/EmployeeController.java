package com.formacion.nttdata.crud.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.formacion.nttdata.crud.dao.EmployeeMapper;
import com.formacion.nttdata.crud.dto.Employee;
import com.formacion.nttdata.crud.validator.Validator;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	EmployeeMapper employeeMapper;
	Validator validator= new Validator();
	private static final String EMPLOYEE = "Employee";
	private static final String EMPLOYEELIST = "ListEmployees";

	@RequestMapping("/listOfEmployee")
	public String showListOfEmployees(Model model) {
		model.addAttribute("employeeList", employeeMapper.getAllEmployees());

		return EMPLOYEELIST;
	}

	@RequestMapping("/showFormForAdd")
	public String addEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		return EMPLOYEE;
	}

	@RequestMapping("/saveProcess")
	public String saveEmployee(@ModelAttribute("employee") Employee employee, Locale locale,Model model) {
			
		
		boolean bandera;

		
		bandera=validator.comprobacionDatos(employee,model);
		if(bandera==false) {
			return EMPLOYEE;
		}
		
		
		if (employee.getId() == null) {
			
			
			
			employee.setFecha(employee.fecha(locale));
			employeeMapper.saveEmployee(employee);

		} else {

			employee.setFecha(employee.fecha(locale));
			employeeMapper.updateEmployee(employee);
		}
		return "redirect:/employee/listOfEmployee";
	}

	@RequestMapping("/displayUpdateForm")
	public String showUpdateForm(@RequestParam("employeeId") int employeeId, Model model) {
		model.addAttribute("employee", employeeMapper.findById(employeeId));
		return EMPLOYEE;
	}

	@RequestMapping("/displayDeleteForm")
	public String deleteEmployee(@RequestParam("employeeId") int employeeId) {
		employeeMapper.deleteEmployee(employeeId);
		return "redirect:/employee/listOfEmployee";
	}
}