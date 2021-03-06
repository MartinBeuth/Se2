package de.freerider.repository;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;



/**
 * Generic interface for CRUD operations on a repository for a specific type.
 *
 * Source:
 *   https://github.com/spring-projects/spring-data-commons/blob/main/src/main/java/org/springframework/data/repository/CrudRepository.java
 *
 * @param <T> type/class of objects stored in repository
 * @param <ID> ID type, e.g. String
 *
 * @author Oliver Gierke
 * @author Eberhard Wolff
 * @author Jens Schauder
 */

/*@SuppressWarnings("hiding")*/

@Repository
public interface CrudRepository<Customer, String>{
        /**
         * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
         * entity instance completely.
         *
         * @param entity must not be {@literal null}.
         * @return the saved entity; will never be {@literal null}.
         * @throws IllegalArgumentException in case the given {@literal entity} is {@literal null}.
         */
        <S extends Customer> S save( S entity );

        /**
         * Saves all given entities.
         *
         * @param entities must not be {@literal null} nor must it contain {@literal null}.
         * @return the saved entities; will never be {@literal null}. The returned {@literal Iterable} will have the same size
         *         as the {@literal Iterable} passed as an argument.
         * @throws IllegalArgumentException in case the given {@link Iterable entities} or one of its entities is
         *           {@literal null}.
         */
        <S extends Customer> Iterable<S> saveAll( Iterable<S> entities );

        /**
         * Retrieves an entity by its id.
         *
         * @param id must not be {@literal null}.
         * @return the entity with the given id or {@literal Optional#empty()} if none found.
         * @throws IllegalArgumentException if {@literal id} is {@literal null}.
         */
        Optional<Customer> findById(String id );
       


        /**
         * Returns whether an entity with the given id exists.
         *
         * @param id must not be {@literal null}.
         * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
         * @throws IllegalArgumentException if {@literal id} is {@literal null}.
         */
        boolean existsById(String id );
        
        
        
        
        Iterable<Customer> findAll();

        /**
         * Returns all instances of the type {@code T} with the given IDs.
         * <p>
         * If some or all ids are not found, no entities are returned for these IDs.
         * <p>
         * Note that the order of elements in the result is not guaranteed.
         *
         * @param ids must not be {@literal null} nor contain any {@literal null} values.
         * @return guaranteed to be not {@literal null}. The size can be equal or less than the number of given
         *         {@literal ids}.
         * @throws IllegalArgumentException in case the given {@link Iterable ids} or one of its items is {@literal null}.
         */
        Iterable<Customer> findAllById(Iterable<String> ids );

        /**
         * Returns the number of entities available.
         *
         * @return the number of entities.
         */
        long count();

        /**
         * Deletes the entity with the given id.
         *
         * @param id must not be {@literal null}.
         * @throws IllegalArgumentException in case the given {@literal id} is {@literal null}
         */
        void deleteById(String id );

        /**
         * Deletes a given entity.
         *
         * @param entity must not be {@literal null}.
         * @throws IllegalArgumentException in case the given entity is {@literal null}.
         */
        void delete(Customer entity );

        /**
         * Deletes all instances of the type {@code T} with the given IDs.
         *
         * @param ids must not be {@literal null}. Must not contain {@literal null} elements.
         * @throws IllegalArgumentException in case the given {@literal ids} or one of its elements is {@literal null}.
         * @since 2.5
         */
        void deleteAllById(Iterable<? extends String> ids );

        /**
         * Deletes the given entities.
         *
         * @param entities must not be {@literal null}. Must not contain {@literal null} elements.
         * @throws IllegalArgumentException in case the given {@literal entities} or one of its entities is {@literal null}.
         */
        void deleteAll(Iterable<? extends Customer> entities );

        void deleteAll();

		

		
		

		

		
}


