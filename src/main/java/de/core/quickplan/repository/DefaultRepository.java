package de.core.quickplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * the repository extended by all other repositories
 * 
 * @author Sebastian Kohler
 *
 * @param <T> the type of object
 * @param <I> the type of the ID
 */
@NoRepositoryBean
public interface DefaultRepository<T, I> extends JpaRepository<T, I> {

}
