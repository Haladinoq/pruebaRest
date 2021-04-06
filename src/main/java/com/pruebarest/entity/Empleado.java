package com.pruebarest.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.pruebarest.controller.EmpleadoController;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;


/**
 * The persistent class for the empleado 
 * 
 */
@Data
public class Empleado {
	private static final long serialVersionUID = 1L;
	private boolean validDate;
	private static final Logger log = LoggerFactory.getLogger(Empleado.class);
	
	private int idEmpleado;
	@NotBlank(message="Los nombres son obligatorios")
	private String nombres;
	@NotBlank(message="Los apellidos son obligatorios")
	private String apellidos;
	@NotBlank(message="El cargo es obligatorio")
	private String cargo;
	@NotNull(message="La fecha de nacimiento es obligatoria")
	private XMLGregorianCalendar fechaNacimiento;
	@NotNull(message="La fecha de inicio es obligatoria")
	private XMLGregorianCalendar fechaIniComp;
	@NotBlank(message="El numero de documento es obligatorio")
	private String numeroDoc;
	@DecimalMin(value="0.0", message="El salario es obligatorio")
	private double salario;
	@NotBlank(message="El tipo de documento es obligatorio")
	private String tipoDoc;
	
	

	public Empleado() {
	}
	
	public Empleado(String nombres,String apellidos,String tipoDoc,String numeroDoc,XMLGregorianCalendar fechaNacimiento,XMLGregorianCalendar fechaIniComp,String cargo,double salario) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.tipoDoc = tipoDoc;
		this.numeroDoc = numeroDoc;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaIniComp = fechaIniComp;
		this.cargo = cargo;
		this.salario = salario;
	} 
	
	  @AssertTrue(message = "Debe validar el formato de las fechas")
      private boolean isvalidDate() {  
		  boolean flag=true;
		  
		  try { 
			  
			  Date fecNac=fechaNacimiento.toGregorianCalendar().getTime();
			  Date fecIniComp=fechaIniComp.toGregorianCalendar().getTime();
			
		} catch (Exception e) {
			// TODO: handle exception
			flag=false;
		}
		
		  	  
  		// Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");		
		 log.info("Add fechaNacimiento = " + fechaNacimiento);
		 log.info("Add fecha Inicio Compania = " + fechaIniComp);
		  
		  return flag;
     }



	public int getIdEmpleado() {
		return this.idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public XMLGregorianCalendar getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(XMLGregorianCalendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public XMLGregorianCalendar getFechaIniComp() {
		return this.fechaIniComp;
	}

	public void setFechaIniComp(XMLGregorianCalendar feciniCompania) {
		this.fechaIniComp = feciniCompania;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getNumeroDoc() {
		return this.numeroDoc;
	}

	public void setNumeroDoc(String numeroDocumento) {
		this.numeroDoc = numeroDocumento;
	}

	public double getSalario() {
		return this.salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getTipoDoc() {
		return this.tipoDoc;
	}

	public void setTipoDoc(String tipoDocumento) {
		this.tipoDoc = tipoDocumento;
	}

}