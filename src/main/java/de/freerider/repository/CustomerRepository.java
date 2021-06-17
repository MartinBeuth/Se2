
package de.freerider.repository;
import de.freerider.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;



import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;




@Component
public class CustomerRepository implements CrudRepository<Customer, String> {

	
	private Map<String, Customer> so = new HashMap<String, Customer>();
	private final IDGenerator idGen = new IDGenerator("C",
			IDGenerator.IDTYPE.NUM, 6);
	

	/* long count(); */
	@Override
	public long count() {
		long count = so.size();
		return count;
	}

	/* <S extends Customer> S save( S entity ); */
	@Override
	public <S extends Customer> S save(S entity) {
		Customer customer = entity;
		if (entity != null) {
			String id = entity.getId();
			if (id == null || id.length() == 0 || id.equals("")) {
				do {
					id = idGen.nextId();
				} while (so.containsKey(id));
				entity.setId(id);
			}
			customer = so.put(id, entity);
			return customer == null ? entity : (S) customer;
		} else {
			throw new IllegalArgumentException("argument: entitity is null");
		}
	}

	/* <S extends Customer> Iterable<S> saveAll( Iterable<S> entities ); */
	@Override
	public <S extends Customer> Iterable<S> saveAll(Iterable<S> entities) {
		if (entities != null) {
			List<S> saved = new ArrayList<S>();
			for (S e : entities) {
				S savedEntity = save(e);
				saved.add(savedEntity);
			}
			return saved;
		} else {
			throw new IllegalArgumentException("entities are null");
		}
	}

	/* Optional<Customer> findById(String id ); */
	@Override
	public Optional<Customer> findById(String id) {
		if (id != null) {
			if (id.length() == 0 || id.equals("")){
				System.out.println("Not a correct Id");			
			}
			return Optional.of(so.get(id));
		} else {
			throw new IllegalArgumentException("enity is null");
		}
	}

	/* Iterable<Customer> findAllById(Iterable<String> ids ); */
	@Override
	public Iterable<Customer> findAllById(Iterable<String> ids) {

		Iterable<Customer> customers = new ArrayList<>();
		if (ids != null) {
			for (String id : ids) {
				((ArrayList<Customer>) customers).add(so.get(id));
			}
			return customers;
		} else {
			throw new IllegalArgumentException("enity is null");
		}
	}

	/* Iterable<Customer> findAll(); */
	@Override
	public Iterable<Customer> findAll() {

		Set set = so.entrySet();

		if (set.isEmpty() == true) {
			return null;
		} else {
			return set;
		}

	}

	/* boolean existsById(String id ); */
	@Override
	public boolean existsById(String id) {

		if (id == null){
				throw new IllegalArgumentException("argument: entitity is null");			
			}
		if (id != null  || id.length() != 0) {
			return true;
		} else {
			return false;
		}
	}

	/* void deleteById(String id ); */
	@Override
	public void deleteById(String id) {
		if (id != null) {
			so.remove(id);
		}
		else {
			throw new IllegalArgumentException("enity is null");
		}

	}

	/* void delete(Customer entity ); */
	@Override
	public void delete(Customer entity) {
		if (entity != null) {
			deleteById(entity.getId());

		} else {
			throw new IllegalArgumentException("enity is null");
		}
	}

	/* void deleteAllById(Iterable<? extends String> ids ); */
	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		
		
		if (ids != null) {
			ids.forEach(this::deleteById);
		} else {
			throw new IllegalArgumentException("enity is null");
		}
	}

	/* void deleteAll() */

	@Override
	public void deleteAll() {
		so.clear();
	}

	@Override
	public void deleteAll(Iterable<? extends Customer> entities) {
		if (entities != null) {
			for (Customer e : entities) {
				delete(e);
			}

		} else {
			throw new IllegalArgumentException("entities are null");
		}
	}



	

	

}
