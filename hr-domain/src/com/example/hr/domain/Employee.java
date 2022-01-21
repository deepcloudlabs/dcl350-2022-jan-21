package com.example.hr.domain;

import java.util.List;

// Entity -> Needs to persist
//  i) Has an identity -> tcKimlikNo
// ii) Mutable 
public class Employee {
	private TcKimlikNo tcKimlikNo;
	private FullName fullname;
	private Iban iban;
	private Money salary;
	private BirthYear birthYear;
	private Photo photo;
	private JobStyle jobStyle;
	private List<Department> departments;

}
