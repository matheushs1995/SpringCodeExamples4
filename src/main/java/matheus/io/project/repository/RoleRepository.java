package matheus.io.project.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import matheus.io.project.StatusRole;
import matheus.io.project.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	List<Role> findByStatus(StatusRole status);
	
	Page<Role> findAll(Pageable pageable);
	
}
