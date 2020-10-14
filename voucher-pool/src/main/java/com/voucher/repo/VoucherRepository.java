package com.voucher.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.voucher.entity.Voucher;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher,Integer> {
	
	@Modifying
    @Query("delete from Voucher u where u.id = :id")
   public void deleteVoucherById(@Param("id") int id);
}
