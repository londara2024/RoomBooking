package com.darahotel.darahotel.repository;

import com.darahotel.darahotel.entity.BookingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingListRepository extends JpaRepository<BookingList, Long> {
    List<BookingList> findByBookingId(Long id);

}
