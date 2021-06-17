package de.freerider.model;
import org.junit.jupiter.api.AfterAll;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import de.freerider.model.Customer;
import de.freerider.model.Customer.Status;
import de.freerider.repository.IDGenerator;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;



@SpringBootTest(classes =de.freerider.model.CustomerTest.class)
public class CustomerTest {
	
	private static Customer mats;
	private static Customer thomas;
	private final IDGenerator idGen = new IDGenerator( "C", IDGenerator.IDTYPE.NUM, 6 );

	

	/* BeforeEach */
	@BeforeEach
	public void setUpEach() {
		mats = new Customer(idGen.nextId(), "Doe", "John", "Street 1");
		thomas = new Customer(idGen.nextId(), "Doe", "Sue", "Street 2");
	}

	/* ID-Tests */
	/* Id‐Attribut eines neu erzeugten Customer‐Objekts null*/
	@Test
	public void testIdNull() {
		mats = new Customer(null, "Doe", "John", "Street 1");
		thomas = new Customer(null, "Doe", "Sue", "Street 2");
		assertNull(mats.getId());
		assertNull(thomas.getId());

	}
	
	/*Id mit einem nicht‐Null Wert */
	@Test
	public void testSetId() {
		mats = new Customer(null,"Doe", "John", "Street 1");
		thomas = new Customer(null,"Doe", "Sue", "Street 2");
		System.out.println(mats.getId());
		
		String vergleich = null;
                assertEquals(mats.getId(), vergleich);
                assertEquals(thomas.getId(), vergleich);

	}

	/*nach (nicht‐Null) Id, Id nicht erneut mit einem neuen (nicht‐Null) Wert*/ 
	@Test
	public void testSetIdOnlyOnce() {
		mats = new Customer(idGen.nextId(),"Doe", "John", "Street 1");
		thomas = new Customer(idGen.nextId(),"Doe", "Sue", "Street 2");
		String id1=idGen.nextId();
		String id2=idGen.nextId();
		mats.setId(id1);
		thomas.setId(id2);
		String matsid=mats.getId();
		String thomasid=thomas.getId();
		mats.setId(idGen.nextId());
		thomas.setId(idGen.nextId());
		assertNotEquals(mats.getId(),matsid);
		assertNotEquals(thomas.getId(),thomasid);
		
	}

	/* prüft Id mit setId(null); zurückgesetzt werden kann */
	@Test
	public void testResetId() {
		mats = new Customer(idGen.nextId(),"Doe", "John", "Street 1");
		thomas = new Customer(idGen.nextId(),"Doe", "Sue", "Street 2");
		mats.setId(null);
		thomas.setId(null);
		String vergleich = null;
                assertEquals(mats.getId(), vergleich);
                assertEquals(thomas.getId(), vergleich);

	}

	/* Name‐Tests */
	/* Vor‐ und Nachname initial mit (nicht null)*/

	@Test
	public void testNamesInitial() {
		mats = new Customer(idGen.nextId(),"", "", "Street 1");
		thomas = new Customer(idGen.nextId(),"", "", "Street 2");
		String vergleich = null;
		assertNotEquals(mats.getFirstName(), vergleich);
		assertNotEquals(mats.getLastName(), vergleich);
		assertNotEquals(thomas.getFirstName(), vergleich);
		assertNotEquals(thomas.getLastName(), vergleich);
	}
	
	/*Vor‐ oder Nachnamen diese auf Null zu setzen, die getter‐Methoden ““ zurückgeben.*/
	@Test
	public void testNamesSetNull() {
		mats = new Customer(idGen.nextId(),"Doe", "John", "Street 1");
		thomas = new Customer(idGen.nextId(),"Doe", "Sue", "Street 2");
		String vergleich = "";
		mats.setFirstName(null);
		mats.setLastName(null);
		thomas.setFirstName(null);
		thomas.setLastName(null);
		assertNotEquals(mats.getFirstName(), vergleich);
		assertNotEquals(mats.getLastName(), vergleich);
		assertNotEquals(thomas.getFirstName(), vergleich);
		assertNotEquals(thomas.getLastName(), vergleich);

	}
//
	/*Vor‐ und Nachnamen auf reguläre Werte. Die getter‐Methoden geben Werte zurück.*/
	@Test
	public void testSetNames() {
		mats = new Customer(idGen.nextId(),null, null, "Street 1");
		thomas = new Customer(idGen.nextId(),null, null, "Street 2");
		String vergleich = "";
		String vergleich2 = null;
		mats.setFirstName("John");
		mats.setLastName("Doe");
		thomas.setFirstName("Sue");
		thomas.setLastName("Doe");
		assertNotEquals(mats.getFirstName(), vergleich);
		assertNotEquals(mats.getLastName(), vergleich);
		assertNotEquals(thomas.getFirstName(), vergleich);
		assertNotEquals(thomas.getLastName(), vergleich);
		assertNotEquals(mats.getFirstName(), vergleich2);
		assertNotEquals(mats.getLastName(), vergleich2);
		assertNotEquals(thomas.getFirstName(), vergleich2);
		assertNotEquals(thomas.getLastName(), vergleich2);

	}
//
	/* Contact‐Tests */
	/*Adresse initial mit (nicht null)*/
	@Test
	public void testContactsInitial() {
		mats = new Customer(idGen.nextId(),"Doe", "John", " ");
		thomas = new Customer(idGen.nextId(),"Doe", "Sue", " ");
		String vergleich = null;
		assertNotEquals(mats.getContact(), vergleich);
		assertNotEquals(thomas.getContact(), vergleich);
	}
	
	/*Adresse auf null gesetzt */
	@Test
	public void testContactsSetNull() {
		mats = new Customer(idGen.nextId(),"Doe", "John", "");
		thomas = new Customer(idGen.nextId(),"Doe", "Sue", "");
		String vergleich = "";
		mats.setContact(null);
		thomas.setContact(null);
		assertNotEquals(mats.getContact(), vergleich);
		assertNotEquals(thomas.getContact(), vergleich);

	}
//
	/*auf reguläre Werte. Die getter‐Methoden geben Werte zurück.*/
	@Test
	public void testSetContact() {

		mats = new Customer(idGen.nextId(),"Doe", "John", "Street 1");
		thomas = new Customer(idGen.nextId(),"Doe", "Sue", "Street 2");
		String vergleich = "";
		String vergleich2 = null;
		mats.setContact("Street 1");
		thomas.setContact("Street 2");
		assertNotEquals(mats.getContact(), vergleich);
		assertNotEquals(thomas.getContact(), vergleich);
		assertNotEquals(mats.getContact(), vergleich2);
		assertNotEquals(thomas.getContact(), vergleich2);
	}
//
	/*Status eines Kunden New*/
	@Test
	public void testStatusInitial() {
		mats = new Customer(idGen.nextId(),"Doe", "John", "Street 1");
		thomas = new Customer(idGen.nextId(),"Doe", "Sue", "Street 2");
		mats.setStatus(Status.New);
                thomas.setStatus(Status.New);
		assertEquals(Status.New, mats.getStatus());
		assertEquals(Status.New, thomas.getStatus());
	}
	
	/*nach dem Setzen Status, dieser mit der getter‐Methode zurück*/
	@Test
	public void testSetStatus() {
		mats = new Customer(idGen.nextId(),"Doe", "John", "Street 1");
		thomas = new Customer(idGen.nextId(),"Doe", "Sue", "Street 2");
		mats.setStatus(Status.New);
		thomas.setStatus(Status.New);		
		assertEquals(Status.New, mats.getStatus());
		assertEquals(Status.New, thomas.getStatus());
		mats.setStatus(Status.InRegistration);
		thomas.setStatus(Status.InRegistration);
		assertEquals(Status.InRegistration, mats.getStatus());
		assertEquals(Status.InRegistration, thomas.getStatus());
		mats.setStatus(Status.Active);
		thomas.setStatus(Status.Active);
		assertEquals(Status.Active, mats.getStatus());
		assertEquals(Status.Active, thomas.getStatus());
		mats.setStatus(Status.Suspended);
		thomas.setStatus(Status.Suspended);
		assertEquals(Status.Suspended, mats.getStatus());
		assertEquals(Status.Suspended, thomas.getStatus());
		mats.setStatus(Status.Deleted);
		thomas.setStatus(Status.Deleted);
		assertEquals(Status.Deleted, mats.getStatus());
		assertEquals(Status.Deleted, thomas.getStatus());

	}

}
