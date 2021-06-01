package de.freerider.repository;
import de.freerider.model.Customer;
import java.util.ArrayList;
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
	public static void main(String[] args){

		CustomerRepository zumTesten = new CustomerRepository();
		IDGenerator idGen = new IDGenerator( "C", IDGenerator.IDTYPE.NUM, 6 );
		
		

		Customer eins =new Customer(idGen.nextId(),"Doe","John","Street 1",null);
		Customer zwei =new Customer(idGen.nextId(),"Doe","Sue","Street 2",null);
		Customer drei =new Customer(idGen.nextId(),"Snow","John","Street 3",null);
		Customer vier =new Customer(idGen.nextId(),"John","John","Street 4", null);
		Customer fuenf =new Customer(idGen.nextId(),"Bravo","Johnny","Street 5",null);
		/*save*/
		zumTesten.save(eins);
		zumTesten.save(zwei);
		zumTesten.save(drei);
		zumTesten.save(vier);
		zumTesten.save(fuenf);

		/*----------------------------------------------------------------------------------------*/
		/*save*/
		zumTesten.save(eins);
		zumTesten.save(zwei);
		zumTesten.save(drei);
		zumTesten.save(vier);
		zumTesten.save(fuenf);
		
		/*----------------------------------------------------------------------------------------*/
		/*boolean existsById(String id );*/
		System.out.println(zumTesten.existsById(eins.getId()));
		System.out.println(zumTesten.existsById(zwei.getId()));
		System.out.println(zumTesten.existsById(drei.getId()));
		System.out.println(zumTesten.existsById(vier.getId()));
		System.out.println(zumTesten.existsById(fuenf.getId()));
		
		/*----------------------------------------------------------------------------------------*/
		/*Optional<Customer> findById(String id );*/
		System.out.println(zumTesten.findById(eins.getId()));
		System.out.println(zumTesten.findById(zwei.getId()));
		System.out.println(zumTesten.findById(drei.getId()));
		System.out.println(zumTesten.findById(vier.getId()));
		System.out.println(zumTesten.findById(fuenf.getId()));
		
		/*----------------------------------------------------------------------------------------*/
		/*Iterable<Customer> findAllById(Iterable<String> ids );*/
		List<String> data = new ArrayList<>();
        data.add(eins.getId());
        data.add(zwei.getId());
        data.add(drei.getId());
        data.add(vier.getId());
        data.add(fuenf.getId());
        System.out.println(zumTesten.findAllById(data));
        
        /*----------------------------------------------------------------------------------------*/
        /*findAll*/
		System.out.println(zumTesten.findAll());
		
		/*----------------------------------------------------------------------------------------*/
		/*count*/
		System.out.println(zumTesten.count());
		
		/*----------------------------------------------------------------------------------------*/
		/*void deleteById(String id );*/
        zumTesten.deleteById(eins.getId());
		zumTesten.deleteById(zwei.getId());
		zumTesten.deleteById(drei.getId());
		zumTesten.deleteById(vier.getId());
		zumTesten.deleteById(fuenf.getId());
		
		
		eins =new Customer(idGen.nextId(),"Doe","John","Street 1",null);
		zwei =new Customer(idGen.nextId(),"Doe","Sue","Street 2",null);
		drei =new Customer(idGen.nextId(),"Snow","John","Street 3",null);
		vier =new Customer(idGen.nextId(),"John","John","Street 4",null);
		fuenf =new Customer(idGen.nextId(),"Bravo","Johnny","Street 5",null);
		
		
		zumTesten.save(eins);
		zumTesten.save(zwei);
		zumTesten.save(drei);
		zumTesten.save(vier);
		zumTesten.save(fuenf);
				
		/*----------------------------------------------------------------------------------------*/       
        /*void delete(Customer entity );*/
        zumTesten.delete(eins);
        zumTesten.delete(zwei);
        zumTesten.delete(drei);
        zumTesten.delete(vier);
        zumTesten.delete(fuenf);
        
        eins =new Customer(idGen.nextId(),"Doe","John","Street 1",null);
		zwei =new Customer(idGen.nextId(),"Doe","Sue","Street 2",null);
		drei =new Customer(idGen.nextId(),"Snow","John","Street 3",null);
		vier =new Customer(idGen.nextId(),"John","John","Street 4",null);
		fuenf =new Customer(idGen.nextId(),"Bravo","Johnny","Street 5",null);
		
		
		zumTesten.save(eins);
		zumTesten.save(zwei);
		zumTesten.save(drei);
		zumTesten.save(vier);
		zumTesten.save(fuenf);
		
		
		List<Customer> data2 = new ArrayList<>();
        data2.add(eins);
        data2.add(zwei);
        data2.add(drei);
        data2.add(vier);
        data2.add(fuenf);
        
        /*----------------------------------------------------------------------------------------*/
        /*save All*/
        zumTesten.saveAll(data2);
        
        /*----------------------------------------------------------------------------------------*/
        /*delete All*/
        zumTesten.deleteAll(data2);
        
        eins =new Customer(idGen.nextId(),"Doe","John","Street 1",null);
		zwei =new Customer(idGen.nextId(),"Doe","Sue","Street 2",null);
		drei =new Customer(idGen.nextId(),"Snow","John","Street 3",null);
		vier =new Customer(idGen.nextId(),"John","John","Street 4",null);
		fuenf =new Customer(idGen.nextId(),"Bravo","Johnny","Street 5",null);
		
		
		zumTesten.save(eins);
		zumTesten.save(zwei);
		zumTesten.save(drei);
		zumTesten.save(vier);
		zumTesten.save(fuenf);
		
		data = new ArrayList<>();
        data.add(eins.getId());
        data.add(zwei.getId());
        data.add(drei.getId());
        data.add(vier.getId());
        data.add(fuenf.getId());
        
        /*----------------------------------------------------------------------------------------*/
        /*void deleteAllById(Iterable<? extends String> ids );*/
        zumTesten.deleteAllById(data);        


	}
	

}
