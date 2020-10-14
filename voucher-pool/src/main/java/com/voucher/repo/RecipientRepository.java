package com.voucher.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.voucher.entity.Recipient;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient,Integer> {
   
	@Query("select u from Recipient u where u.email = :email")
	Recipient findByEmail(@Param("email") String email);
}
