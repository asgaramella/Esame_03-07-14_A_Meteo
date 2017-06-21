package it.polito.tdp.meteo.model;

import java.time.LocalDate;

public class Stat implements Comparable<Stat>{
	
	private LocalDate dataTemperatura;
	private int temp;
	private LocalDate dataPrec;
	private int tempPrec;
	
	
	
	
	public Stat(LocalDate dataTemperatura, int temp, LocalDate dataPrec, int tempPrec) {
		super();
		this.dataTemperatura = dataTemperatura;
		this.temp = temp;
		this.dataPrec = dataPrec;
		this.tempPrec = tempPrec;
	}
	
	
	public Stat(LocalDate dataTemperatura, int temp) {
		super();
		this.dataTemperatura = dataTemperatura;
		this.temp = temp;
	}


	public LocalDate getDataTemperatura() {
		return dataTemperatura;
	}
	public void setDataTemperatura(LocalDate dataTemperatura) {
		this.dataTemperatura = dataTemperatura;
	}
	public int getTemp() {
		return temp;
	}
	public void setTemp(int temp) {
		this.temp = temp;
	}
	public LocalDate getDataPrec() {
		return dataPrec;
	}
	public void setDataPrec(LocalDate dataPrec) {
		this.dataPrec = dataPrec;
	}
	public int getTempPrec() {
		return tempPrec;
	}
	public void setTempPrec(int tempPrec) {
		this.tempPrec = tempPrec;
	}
	@Override
	public int compareTo(Stat other) {
		
		return this.dataTemperatura.compareTo(other.getDataTemperatura());
	}
	
	
	

}
