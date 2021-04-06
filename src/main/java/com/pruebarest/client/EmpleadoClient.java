package com.pruebarest.client;

import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.pruebarest.empleado.wsdl.AddEmpleadoRequest;
import com.pruebarest.empleado.wsdl.AddEmpleadoResponse;

import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;

public class EmpleadoClient extends WebServiceGatewaySupport { 
	
	private static final Logger log = LoggerFactory.getLogger(EmpleadoClient.class); 
	
	public AddEmpleadoResponse addEmpleado(String nombres,String apellidos,String tipoDoc,String numeroDoc,XMLGregorianCalendar fechaNacimiento,XMLGregorianCalendar fechaIniComp,String cargo,double salario) { 
	
		AddEmpleadoRequest request= new AddEmpleadoRequest();
		request.setNombres(nombres);
		request.setApellidos(apellidos);
		request.setTipoDoc(tipoDoc);
		request.setNumeroDoc(numeroDoc);
		request.setFechaNacimiento(fechaNacimiento);
		request.setFechaIniComp(fechaIniComp);
		request.setCargo(cargo);
		request.setSalario(salario);
		
		log.info("Add employe = " + numeroDoc);
		log.info("Add fechaNacimiento = " + fechaNacimiento);
		log.info("Add fechaIniComp = " + fechaIniComp);
		
		return (AddEmpleadoResponse) getWebServiceTemplate().marshalSendAndReceive(request);
		
	}


}
