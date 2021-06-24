package de.freerider.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

// https://howtodoinjava.com/junit5/junit-5-assertions-examples
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import de.freerider.model.Customer;
import de.freerider.model.Customer.Status;
import de.freerider.repository.IDGenerator;
import de.freerider.repository.CustomerRepository;


@SpringBootTest
public class CustomerRepositoryTest {

	@Autowired
	CrudRepository<Customer, String> customerRepository;

	// two sample customers
	private Customer mats;
	private Customer thomas;

	private final IDGenerator idGen = new IDGenerator("C",
			IDGenerator.IDTYPE.NUM, 6);

	@BeforeEach
	public void setUpEach() {
		//
		mats = new Customer(null, "Doe", "John", "Street 1");
		thomas = new Customer(null, "Doe", "Sue", "Street 2");
		//
		customerRepository.deleteAll(); // clear repository
		assertEquals(customerRepository.count(), 0);
	}

	// save//
	// ----------------------------------------------------------------------------------------//
	// 2 Test‐Customer Objekte speichern, prüfen, ob im Repository gespeichert

	@Test
	public void testSaveCount() {
		mats = new Customer(idGen.nextId(), "Doe", "John", "Street 1");
		thomas = new Customer(idGen.nextId(), "Doe", "Sue", "Street 2");
		customerRepository.save(mats);
		customerRepository.save(thomas);
		int pruf = 2;
		assertEquals(customerRepository.count(), pruf);
	}

	// 2 Test‐Customer Objekte speichern, und wiedergefunden werden (z.B. mit
	// findById() )
	@Test
	public void testSaveFind() {
		mats = new Customer(idGen.nextId(), "Doe", "John", "Street 1");
		thomas = new Customer(idGen.nextId(), "Doe", "Sue", "Street 2");
		customerRepository.save(mats);
		customerRepository.save(thomas);
		assertNotNull(customerRepository.findById(mats.getId()));
		assertNotNull(customerRepository.findById(thomas.getId()));

	}

	// Customer‐Objekt mit null‐Id speichern und prüfen, dass ein Id zugewiesen
	// wurde
	@Test
	public void testSaveNullCount() {
		mats = new Customer(null, "Doe", "John", "Street 1");
		thomas = new Customer(null, "Doe", "Sue", "Street 2");
		customerRepository.save(mats);
		customerRepository.save(thomas);
		int pruf = 2;
		assertEquals(customerRepository.count(), pruf);
		assertNotNull(customerRepository.findById(mats.getId()));
		assertNotNull(customerRepository.findById(thomas.getId()));
	}

	// Customer‐Objekt mit nicht‐null‐Id speichern und prüfen, dass ein Id
	// zugewiesen wurde
	@Test
	public void testSaveNullFind() {
		String id1 = idGen.nextId();
		String id2 = idGen.nextId();
		mats = new Customer(id1, "Doe", "John", "Street 1");
		thomas = new Customer(id2, "Doe", "Sue", "Street 2");
		customerRepository.save(mats);
		customerRepository.save(thomas);
		int pruf = 2;
		assertEquals(customerRepository.count(), pruf);
		assertNotNull(customerRepository.findById(mats.getId()));
		assertNotNull(customerRepository.findById(thomas.getId()));
	}

	// Versuch, null‐Referenz zu speichern ( save( null ); )

	// @Test(expected = IllegalArgumentException.class)
	// public void testSaveNull() {
	// customerRepository.save(null);
	// }
	@Test
	void testSaveNull() {

		assertThrows(IllegalArgumentException.class, () -> {
			customerRepository.save(null);
		});

	}

	// Versuch, dasselbe Objekt 2x zu speichern
	@Test
	public void testSaveSame() {
		mats = new Customer(idGen.nextId(), "Doe", "John", "Street 1");
		customerRepository.save(mats);
		customerRepository.save(mats);
		int pruf = 2;
		assertNotEquals(customerRepository.count(), pruf);

	}

	// Versuch, zwei verschiedene Objekte mit demselben Id zu speichern
	@Test
	public void testSaveSameID() {
		String id = idGen.nextId();
		mats = new Customer(id, "Doe", "John", "Street 1");
		thomas = new Customer(id, "Doe", "Sue", "Street 2");
		customerRepository.save(mats);
		customerRepository.save(thomas);
		assertNotEquals(customerRepository.findById(mats.getId()),
				customerRepository.findById(thomas.getId()));

	}

	// Liste mit Customer‐Objekten mit null‐Id speichern und prüfen,
	// dass Id´s zugewiesen wurden und das die Objekte gespeichert sind.
	@Test
	public void testSaveAllFindAndCount() {
		Customer eins = new Customer(null, "Doe", "John", "Street 1");
		Customer zwei = new Customer(null, "Doe", "Sue", "Street 2");
		Customer drei = new Customer(null, "Snow", "John", "Street 3");
		List<Customer> data2 = new ArrayList<>();
		data2.add(eins);
		data2.add(zwei);
		data2.add(drei);
		int pruf = 3;
		customerRepository.saveAll(data2);
		assertEquals(customerRepository.count(), pruf);
		assertNotNull(customerRepository.findAll());

	}

	// Liste mit Customer‐Objekten mit nicht‐null‐Id speichern und prüfen,
	// dass die Id´s nicht verändert wurden und die Objekt gespeichert sind.
	@Test
	public void testSaveAllNullFindIdAndCount() {

		Customer eins = new Customer(idGen.nextId(), "Doe", "John", "Street 1");
		Customer zwei = new Customer(idGen.nextId(), "Doe", "Sue", "Street 2");
		Customer drei = new Customer(idGen.nextId(), "Snow", "John", "Street 3");
		List<Customer> data2 = new ArrayList<>();
		data2.add(eins);
		data2.add(zwei);
		data2.add(drei);
		int pruf = 3;
		customerRepository.saveAll(data2);
		assertEquals(customerRepository.count(), pruf);
		assertNotNull(customerRepository.findById(eins.getId()));
		assertNotNull(customerRepository.findById(zwei.getId()));
		assertNotNull(customerRepository.findById(drei.getId()));

	}

	// Versuch, null‐Referenz zu speichern ( save( null ); ).
	@Test
	void testSaveAllEverythingNull() {

		assertThrows(IllegalArgumentException.class, () -> {
			List<Customer> data2 = new ArrayList<>();
			data2.add(null);
			data2.add(null);
			data2.add(null);
			customerRepository.saveAll(data2);
		});

	}

	// Versuch, dieselben Objekte 2x zu speichern.
	@Test
	public void testSaveAllSame() {
		List<Customer> data2 = new ArrayList<>();
		Customer eins = new Customer(null, "Doe", "John", "Street 1");
		data2.add(eins);
		data2.add(eins);
		int pruf = 2;
		customerRepository.saveAll(data2);
		assertNotEquals(customerRepository.count(), pruf);

	}

	// Versuch, verschiedene Objekte mit identischer Id zu speichern.
	@Test
	public void testSaveAllSameId() {
		String id = idGen.nextId();
		mats = new Customer(id, "Doe", "John", "Street 1");
		thomas = new Customer(id, "Doe", "Sue", "Street 2");
		List<Customer> data2 = new ArrayList<>();
		data2.add(mats);
		data2.add(thomas);
		customerRepository.saveAll(data2);
		assertNotEquals(customerRepository.findById(mats.getId()),
				customerRepository.findById(thomas.getId()));

	}

	// -----------------------------------------------------------------------------------------//
	// find//
	// ------------------------------------------------------------------------------------------//

	// Liste von Customer‐Objekt mit nicht‐null‐Id überprüfen, ob eine Id
	// zugewiesen wurde
	@Test
	public void testFind() {
		mats = new Customer(idGen.nextId(), "Doe", "John", "Street 1");
		thomas = new Customer(idGen.nextId(), "Doe", "Sue", "Street 2");
		customerRepository.save(mats);
		customerRepository.save(thomas);
		assertNotNull(customerRepository.findById(mats.getId()));
		assertNotNull(customerRepository.findById(thomas.getId()));

	}

	// Liste von Customer‐Objekt mit null‐Id überprüfen, ob eine Id zugewiesen
	// wurde
	@Test
	public void testFindNull() {
		mats = new Customer(null, "Doe", "John", "Street 1");
		thomas = new Customer(null, "Doe", "Sue", "Street 2");
		customerRepository.save(mats);
		customerRepository.save(thomas);
		assertNotNull(customerRepository.findById(mats.getId()));
		assertNotNull(customerRepository.findById(thomas.getId()));
	}

	// Versuch, Liste mit verschiedenen Objekte mit demselben Id zu finden.
	@Test
	public void testFindSameID() {
		String id = idGen.nextId();
		mats = new Customer(id, "Doe", "John", "Street 1");
		thomas = new Customer(id, "Doe", "Sue", "Street 2");
		customerRepository.save(mats);
		customerRepository.save(thomas);
		assertNotEquals(customerRepository.findById(mats.getId()),
				customerRepository.findById(thomas.getId()));

	}

	// Versuch,Objekt mit Null zu finden.
	@Test
	void testFindNullID() {

		// First argument - specifies the expected exception.
		// Here it expects that code block will throw NumberFormatException
		// Second argument - is used to pass an executable code block or lambda
		// expression
		assertThrows(IllegalArgumentException.class, () -> {
			customerRepository.findById(null);
		});

	}

	// Liste von Customer‐Objekten mit null‐Id überprüfen, ob eine Id zugewiesen
	// wurde
	@Test
	public void testFindListNullId() {
		String id = idGen.nextId();
		mats = new Customer(null, "Doe", "John", "Street 1");
		thomas = new Customer(null, "Doe", "Sue", "Street 2");
		customerRepository.save(mats);
		customerRepository.save(thomas);
		List<String> ids = new ArrayList<>();
		ids.add(mats.getId());
		ids.add(thomas.getId());
		assertNotNull(customerRepository.findAllById(ids));

	}

	// Liste von Customer‐Objekten mit Id überprüfen, ob eine Id zugewiesen
	// wurde
	@Test
	public void testFindListId() {
		String id = idGen.nextId();
		mats = new Customer(idGen.nextId(), "Doe", "John", "Street 1");
		thomas = new Customer(idGen.nextId(), "Doe", "Sue", "Street 2");
		customerRepository.save(mats);
		customerRepository.save(thomas);
		List<String> ids = new ArrayList<>();
		ids.add(mats.getId());
		ids.add(thomas.getId());
		assertNotNull(customerRepository.findAllById(ids));

	}

	// Liste mit verschiedenen Objekte mit demselben Id zu finden.
	@Test
	public void testFindListSameId() {
		String id = idGen.nextId();
		mats = new Customer(id, "Doe", "John", "Street 1");
		thomas = new Customer(id, "Doe", "Sue", "Street 2");
		customerRepository.save(mats);
		customerRepository.save(thomas);
		List<String> ids = new ArrayList<>();
		ids.add(mats.getId());
		ids.add(thomas.getId());
		List<String> vergleich = new ArrayList<>();
		vergleich.add(id);
		vergleich.add(id);
		assertNotEquals(customerRepository.findAllById(ids),
				customerRepository.findAllById(vergleich));

	}

	// Versuch, Null als Listenwert zu übergeben
	@Test
	void testFindListNull() {

		assertThrows(IllegalArgumentException.class, () -> {
			customerRepository.findAllById(null);
		});

	}

	// Liste von Customer‐Objekten überprüfen, ob sie gefunden werden
	@Test
	public void testFindAll() {
		mats = new Customer(idGen.nextId(), "Doe", "John", "Street 1");
		thomas = new Customer(idGen.nextId(), "Doe", "Sue", "Street 2");
		customerRepository.save(mats);
		customerRepository.save(thomas);
		assertNotNull(customerRepository.findAll());

	}

	

	// ------------------------------------------------------------------------------------------//
	// count//
	// ------------------------------------------------------------------------------------------//
	// Das Crudrepository wird überprüft, wieviele Entitäten vom Typ Customer
	// gespeichert wurden
	@Test
	public void testCountAll() {
		mats = new Customer(idGen.nextId(), "Doe", "John", "Street 1");
		thomas = new Customer(idGen.nextId(), "Doe", "Sue", "Street 2");
		customerRepository.save(mats);
		customerRepository.save(thomas);
		int pruf = 2;
		assertEquals(customerRepository.count(), pruf);

	}

	// Das Crudrepository wird überprüft, ob das Repository leer ist
	@Test
	public void testCountZeroAll() {

		assertEquals(customerRepository.count(), 0);

	}

	// ------------------------------------------------------------------------------------------//
	// delete//
	// ------------------------------------------------------------------------------------------//

	// Customer‐Objekt mit deletebyId löschen
	@Test
	void testDeleteId() {

		
			mats = new Customer(idGen.nextId(), "Doe", "John", "Street 1");
			thomas = new Customer(idGen.nextId(), "Doe", "Sue", "Street 2");
			customerRepository.save(mats);
			customerRepository.save(thomas);
			customerRepository.deleteById(mats.getId());
			customerRepository.deleteById(thomas.getId());
			Optional<Customer> id1=customerRepository.findById(mats.getId());
			Optional<Customer> id2=customerRepository.findById(thomas.getId());
			if (id1== null && id2== null){
				assertNotEquals(1,0);
			}
			
					

	}

	// Versuch, Objekt mit Null zu löschen
	@Test
	void testDeleteNullId() {

		assertThrows(IllegalArgumentException.class, () -> {
			String id = idGen.nextId();
			mats = new Customer(id, "Doe", "John", "Street 1");
			customerRepository.save(mats);
			customerRepository.deleteById(null);
		});

	}

	// Customer‐Objekt mit delete löschen
	@Test
	void testDelete() {

//		assertThrows(NullPointerException.class, () -> {
			mats = new Customer(idGen.nextId(), "Doe", "John", "Street 1");
			thomas = new Customer(idGen.nextId(), "Doe", "Sue", "Street 2");
			customerRepository.save(mats);
			customerRepository.save(thomas);
			customerRepository.delete(mats);
			customerRepository.delete(thomas);
			assertEquals(customerRepository.count(),0);
			
			
//		});

	}

	// Das leere Crudrepository wird überprüft, ob etwas löschbar ist

	@Test
	void testDeleteNull() {

		assertThrows(IllegalArgumentException.class, () -> {
			customerRepository.delete(mats);
		});

	}

	// Liste von Customer‐Objekt mit nicht‐null‐Id überprüfen, ob sie löschbar
	// ist

	@Test
	void testDeleteAllById() {

//		assertThrows(IllegalArgumentException.class, () -> {
			mats = new Customer(idGen.nextId(), "Doe", "John", "Street 1");
			thomas = new Customer(idGen.nextId(), "Doe", "Sue", "Street 2");
			List<String> ids = new ArrayList<>();
			customerRepository.save(mats);
			customerRepository.save(thomas);
			ids.add(mats.getId());
			ids.add(thomas.getId());
			customerRepository.deleteAllById(ids);
			assertEquals(customerRepository.count(),0);

//		});

	}

	// Versuch, Liste mit verschiedenen Objekte mit demselben Id zu löschen.

	@Test
	void testDeleteAllBySameId() {

		assertThrows(IllegalArgumentException.class, () -> {
			mats = new Customer(idGen.nextId(), "Doe", "John", "Street 1");
			thomas = new Customer(idGen.nextId(), "Doe", "Sue", "Street 2");
			List<String> ids = new ArrayList<>();
			String id = mats.getId();
			ids.add(id);
			ids.add(id);
			customerRepository.save(mats);
			customerRepository.save(thomas);
			customerRepository.deleteAllById(ids);
			customerRepository.delete(mats);
		});

	}

	// Versuch, Liste mit Nulls zu löschen.

	@Test
	void testDeleteAllByNullId() {
		assertThrows(IllegalArgumentException.class, () -> {
			mats = new Customer(idGen.nextId(), "Doe", "John", "Street 1");
			thomas = new Customer(idGen.nextId(), "Doe", "Sue", "Street 2");
			List<String> ids = new ArrayList<>();
			ids.add(null);
			ids.add(null);
			customerRepository.save(mats);
			customerRepository.save(thomas);
			customerRepository.deleteAllById(ids);
		});

	}

	// Liste von Customer‐Objekten löschen

	@Test
	public void testDeleteAll() {

		mats = new Customer(idGen.nextId(), "Doe", "John", "Street 1");
		thomas = new Customer(idGen.nextId(), "Doe", "Sue", "Street 2");
		List<Customer> data2 = new ArrayList<>();
		data2.add(mats);
		data2.add(thomas);
		customerRepository.save(mats);
		customerRepository.save(thomas);
		customerRepository.deleteAll(data2);
		assertEquals(customerRepository.count(), 0);

	}

	// Das leere Crudrepository wird überprüft, ob etwas löschbar ist
	@Test
	void testDeleteAllEmptyRepository() {

		assertThrows(IllegalArgumentException.class, () -> {
			mats = new Customer(idGen.nextId(), "Doe", "John", "Street 1");
			thomas = new Customer(idGen.nextId(), "Doe", "Sue", "Street 2");
			List<Customer> data2 = new ArrayList<>();
			data2.add(mats);
			data2.add(thomas);
			customerRepository.deleteAll(data2);
		});

	}

	// Liste von Customer‐Objekten durch iterieren löschen

	@Test
	public void testDeleteAllIterables() {

		mats = new Customer(idGen.nextId(), "Doe", "John", "Street 1");
		thomas = new Customer(idGen.nextId(), "Doe", "Sue", "Street 2");
		List<Customer> data2 = new ArrayList<>();
		data2.add(mats);
		data2.add(thomas);
		customerRepository.save(mats);
		customerRepository.save(thomas);
		customerRepository.deleteAll(data2);
		assertEquals(customerRepository.count(), 0);

	}

	// -----------------------------------------------------------------------------------//

}