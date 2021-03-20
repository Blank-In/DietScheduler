package domain;

import javafx.beans.property.SimpleStringProperty;

public class Row {
	private SimpleStringProperty  mon;
	private SimpleStringProperty  tue;
	private SimpleStringProperty  wed;
	private SimpleStringProperty  thu;
	private SimpleStringProperty  sat;
	private SimpleStringProperty  fri;
	private SimpleStringProperty  sun;
	
	public Row(String mon, String tue, String wed, String thu, String fri, String sat, String sun) {
		this.mon = new SimpleStringProperty (mon);
		this.tue = new SimpleStringProperty (tue);
		this.wed = new SimpleStringProperty (wed);
		this.thu = new SimpleStringProperty (thu);
		this.fri = new SimpleStringProperty (fri);
		this.sat = new SimpleStringProperty (sat);
		this.sun = new SimpleStringProperty (sun);
	}

	public String getMon() {
		return mon.get();
	}

	public void setMon(SimpleStringProperty mon) {
		this.mon = mon;
	}

	public String getTue() {
		return tue.get();
	}

	public void setTue(SimpleStringProperty tue) {
		this.tue = tue;
	}

	public String getWed() {
		return wed.get();
	}

	public void setWed(SimpleStringProperty wed) {
		this.wed = wed;
	}

	public String getThu() {
		return thu.get();
	}

	public void setThu(SimpleStringProperty thu) {
		this.thu = thu;
	}

	public String getSat() {
		return sat.get();
	}

	public void setSat(SimpleStringProperty sat) {
		this.sat = sat;
	}

	public String getFri() {
		return fri.get();
	}

	public void setFri(SimpleStringProperty fri) {
		this.fri = fri;
	}

	public String getSun() {
		return sun.get();
	}

	public void setSun(SimpleStringProperty sun) {
		this.sun = sun;
	}
}
