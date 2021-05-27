package de.freerider.repository;
import de.freerider.model.Customer;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
class CustomerRepository implements CrudRepository<Customer, String> {

	private CrudRepository crudrepository;
	private final IDGenerator idGen = new IDGenerator( "C", IDGenerator.IDTYPE.NUM, 6 );

	/*long count();*/
	@Override
	public long count() {
		long count = crudrepository.count();
		return count;
	}

	/*<S extends Customer> S save( S entity );*/
	@Override
	public <S extends Customer> S save(S entity) {
		if (entity.getId()=="" || entity.getId()==null ){
			entity.setId(idGen.nextId());
			crudrepository.save(entity);
			}
		if(crudrepository.existsById(entity.getId())==false){
			entity.setId(idGen.nextId());
			crudrepository.save(entity);
			}
		else{
		crudrepository.save(entity);
		}
		return entity;
	}

	/*<S extends Customer> Iterable<S> saveAll( Iterable<S> entities );*/
	@Override
	public <S extends Customer> Iterable<S> saveAll(Iterable<S> entities) {
		Iterable<S> response = crudrepository.saveAll(entities);
		return (Iterable<S>) response;
	}

	/*Optional<Customer> findById(String id );*/
	@Override
	public Optional<Customer> findById(String id) {
		Optional<Customer> customerResponse = crudrepository.findById(id);
		return customerResponse;

	}

	/*Iterable<Customer> findAllById(Iterable<String> ids );*/
	@Override
	public Iterable<Customer> findAllById(Iterable<String> ids) {
		Iterable<Customer> customerResponse = crudrepository.findAllById(ids);
		return customerResponse;
	}

	/*Iterable<Customer> findAll();*/
	@Override
	public Iterable<Customer> findAll() {
		Iterable<Customer> customerResponse = (Iterable<Customer>) crudrepository
				.findAll();
		return customerResponse;
	}

	/*boolean existsById(String id );*/
	@Override
	public boolean existsById(String id) {
		return crudrepository.existsById(id);
	}

	/*void deleteById(String id );*/
	@Override
	public void deleteById(String id) {
		crudrepository.deleteById(id);

	}

	/*void delete(Customer entity );*/
	@Override
	public void delete(Customer entity) {
		crudrepository.delete(entity);

	}

	/*void deleteAllById(Iterable<? extends String> ids );*/
	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		crudrepository.deleteAll(ids);

	}

	/*void deleteAll();*/
	@Override
	public void deleteAll() {
		crudrepository.deleteAll();
	}

	/*void deleteAll(Iterable<? extends Customer> entities );*/
	@Override
	public void deleteAll(Iterable<? extends Customer> entities) {
		crudrepository.deleteAll(entities);
		
	}

	

}
