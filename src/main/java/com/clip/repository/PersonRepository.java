package com.clip.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository {
 
	private List<Person> persons = null;
	
	@PostConstruct
	public void getPersons(){
		populatePersons(4);
	}
	
	private void populatePersons(int numberOfPersons){
		Person person = null;
		this.persons = new ArrayList<Person>();
		for (int i=100000000; i < 100000000+numberOfPersons; i++){
			person = new Person();
			person.setSsn(String.valueOf(i));
			person.setCreditEligibiligyStatus(i % 2 == 0 ? "yes" : "no");
			System.out.println("\nPerson Added:" + person);
			persons.add(person);
		}
	}
	
	public Person getCreditLineEligibilityStatus(String ssn){
		Person foundPerson = null;
		for (Person person : persons){
			if (person.getSsn().equals(ssn)){
				foundPerson =  person;	
				System.out.println("\n\nFound Person:" + foundPerson);
				break;
			};
		}
		return foundPerson;
	}
}
