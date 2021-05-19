package com.example.fleetapplicationprojectlast;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long>{
	Admin findByEmail(String email);

//	Admin findByEmailIdIgnoreCase(String email);
}
