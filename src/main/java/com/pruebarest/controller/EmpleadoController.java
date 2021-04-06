package com.pruebarest.controller;

import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.pruebarest.client.EmpleadoClient;
import com.pruebarest.config.SoapClientConfig;
import com.pruebarest.empleado.wsdl.AddEmpleadoRequest;
import com.pruebarest.empleado.wsdl.AddEmpleadoResponse;
import com.pruebarest.entity.Empleado;

@RestController
@RequestMapping("/restws")
public class EmpleadoController { 
	

    private AnnotationConfigApplicationContext context;// = new AnnotationConfigApplicationContext(SoapClientConfig.class);
    private EmpleadoClient client;// = context.getBean(EmpleadoClient.class);
	private static final Logger log = LoggerFactory.getLogger(EmpleadoController.class);

	public EmpleadoController() {

	}

	

	
	// URI - http://localhost:8080/restws/empleados
	// RequestMapping name at the top of this file is "/restws"
	// GetMapping below is "/empleados"
	@GetMapping("/empleados")
	public ResponseEntity<Object> createEmpleado(@Valid @RequestBody Empleado empleado) {
	
		  AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(SoapClientConfig.class);
		  EmpleadoClient client = context.getBean(EmpleadoClient.class);
		  AddEmpleadoResponse response =client.addEmpleado(empleado.getNombres(), empleado.getApellidos(), empleado.getTipoDoc(), empleado.getNumeroDoc(), empleado.getFechaNacimiento(), empleado.getFechaIniComp(), empleado.getCargo(), empleado.getSalario());		  
	
		  System.out.println("response: respuesta id="+ response.getServiceStatus().getMessage()+", title=" + response.getServiceStatus().getStatusCode()  );
		  return ResponseEntity.ok(response); // return 200, with json body
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}

}
