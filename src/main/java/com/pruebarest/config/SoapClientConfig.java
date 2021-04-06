package com.pruebarest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.pruebarest.client.EmpleadoClient;

@Configuration
public class SoapClientConfig { 
	
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the  specified in
		// pom.xml
		marshaller.setContextPath("com.pruebarest.empleado.wsdl");
		return marshaller;
	}

	@Bean
	public EmpleadoClient empleadoClient(Jaxb2Marshaller marshaller) {
		EmpleadoClient client = new EmpleadoClient();
		client.setDefaultUri("http://localhost:8080/ws/empleados");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}
