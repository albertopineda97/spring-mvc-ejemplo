package com.formacion.nttdata.crud.validator;

import org.springframework.ui.Model;

import com.formacion.nttdata.crud.dto.Employee;

public class Validator {
  String exNom="^[A-Za-z\\s]{3,}[\\.]{0,1}[A-Za-z\\s]{3,}+$";
  //String exEmail="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\\\.[a-zA-Z0-9-.]+$"; no funciona
	public boolean comprobacionDatos (Employee employee, Model model ) {
		
		boolean bandera =true;
		if(!employee.getFullname().matches(exNom)) {
			model.addAttribute("errorNombre","error de nombre");
			
			bandera=false;
		}
		if(employee.getEmail().isEmpty()) {
			model.addAttribute("errorEmail","error de Email");
			//return EMPLOYEE;
			bandera=false;
		}
		
		if(employee.getGender() == null) {
            model.addAttribute("errorGenero","error de gender");
            
            bandera=false;
        }
		
		if(employee.getHobbies() == null) {
            model.addAttribute("errorHobbies","error de hobbies");
           
            bandera=false;
        }
		
		  if(employee.getAddress().isEmpty()) {
	            model.addAttribute("errorDir","error de direccion");
	            
	            bandera=false;
	        }
		
		if(bandera == false) {
			return false;
		}
		
		return true;
		
	}
}
