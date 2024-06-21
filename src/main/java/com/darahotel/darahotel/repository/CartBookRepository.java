package com.darahotel.darahotel.repository;

import com.darahotel.darahotel.entity.CartBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartBookRepository extends JpaRepository<CartBook, Long> {
}
