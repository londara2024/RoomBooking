package com.darahotel.darahotel.service.serviceImpl;

import com.darahotel.darahotel.dto.request.BookingDTO;
import com.darahotel.darahotel.dto.request.BookingListRequestDTO;
import com.darahotel.darahotel.dto.request.RoomDTO;
import com.darahotel.darahotel.dto.response.BookingResponse;
import com.darahotel.darahotel.entity.Booking;
import com.darahotel.darahotel.entity.BookingList;
import com.darahotel.darahotel.entity.Room;
import com.darahotel.darahotel.repository.BookingListRepository;
import com.darahotel.darahotel.service.BookingListService;
import com.darahotel.darahotel.service.BookingService;
import com.darahotel.darahotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class BookingListServiceImpl implements BookingListService {

    private final BookingListRepository bookingListRepository;

    private final BookingService bookingService;

    private final RoomService roomService;

    @Override
    public BookingResponse bookingList(BookingListRequestDTO booking) {

        BookingList bookingList;
        Double totalBookPrice = 0.0;
        List<Room> lstRoom = new ArrayList<Room>();

        Booking book = bookingService.getBookingById(booking.getBookingId()); // TODO: find book with id


        for (int i = 0; i < booking.getRoomId().size(); i++) {
            bookingList = new BookingList();
            bookingList.setBookingId(book.getBookingId());

            Room room = roomService.getRoomById(booking.getRoomId().get(i)); // TODO: find room by id
            if (room.getStatus() != 2) {
                updateStatusOfRoom(room); // TODO: update status of room
                bookingList.setRoomId(room.getRoomId());

                assert false;
                totalBookPrice += room.getRoomType().getPrice().doubleValue();
                lstRoom.add(room);

                bookingList.setStatus(1);
            } else {
                // show user all room has been booking all already

            }
            bookingListRepository.save(bookingList);
        }

        if (totalBookPrice> 0) {
            updatePriceOfBook(totalBookPrice, book); // TODO: update price of book
        }

        return new BookingResponse(book, lstRoom);
    }

    @Override
    public BookingResponse getBookingById(Long bookingId) {

        List<Room> lstRoom = new ArrayList<Room>();
        List<BookingList> bookingList = bookingListRepository.findByBookingId(bookingId);

        Booking booking = bookingService.getBookingById(bookingList.get(0).getBookingId());

        for (int idx = 0; idx < bookingList.size(); idx++) {
            if (bookingList.get(idx).getStatus() != 0) {
                Room room = roomService.getRoomById(bookingList.get(idx).getRoomId());
                lstRoom.add(room);
            }
        }

        return new BookingResponse(booking, lstRoom);
    }

    @Override
    public BookingResponse updateBooking(Long bookingId, BookingListRequestDTO booking) {

        /*
        * status 0 : delete room from booking
        * status 1 : add new booking
        * status 2 : update booking
        * */

        BookingList updateBookingList = new BookingList();

        List<BookingList> bookingLists = bookingListRepository.findByBookingId(bookingId);

        boolean isNumber = false;

        for (int index = 0; index < booking.getRoomId().size() ; index++) {
            isNumber = false;
            for (BookingList bookingList : bookingLists) { // TODO: find new room not book.
                if (booking.getRoomId().get(index) == bookingList.getRoomId()) {
                    System.out.println( "updateBooking :: " + bookingList);
                    isNumber = true;
                }
            }
            if (!isNumber) { // TODO: add room in list of booking lists

                // TODO: Update status of room
                Room room = roomService.getRoomById(booking.getRoomId().get(index));

                if (room.getStatus() != 2) {

                    Double updateBookingPrice = 0.0;

                    updateBookingList.setBookingId(bookingId);
                    updateBookingList.setRoomId(booking.getRoomId().get(index));
                    updateBookingList.setStatus(1);

                    // TODO : update status in room table to 2
                    RoomDTO roomDTO = new RoomDTO(room);
                    roomDTO.setStatus(2);  // TODO : update status [2] it means that room has been booking.
                    roomService.updateRoom(booking.getRoomId().get(index), roomDTO); // TODO: using service of room service.


                    // TODO : update new price of booking
                    Booking updBooking = bookingService.getBookingById(bookingId);
                    updateBookingPrice = updBooking.getBookingPrice().doubleValue() + (room.getRoomType().getPrice().doubleValue() / 2);
                    updBooking.setBookingPrice(BigDecimal.valueOf(updateBookingPrice));

                    BookingDTO udBooking = new BookingDTO(updBooking);
                    bookingService.updateBooking(bookingId, udBooking); // TODO: using service of booking service.

                    bookingListRepository.save(updateBookingList); // TODO: Add new room in booking list
                }

            }
        }

        return getBookingById(bookingId);
    }

    @Override
    public BookingResponse deleteBooking(Long bookingId, BookingListRequestDTO booking) {

        Booking updateBooking = bookingService.getBookingById(bookingId); // TODO : Find Booking List

        List <Room> roomList = new ArrayList<Room>(); // TODO: List all rooms booing.
        List<BookingList> bookingLists = bookingListRepository.findByBookingId(bookingId); // TODO: Get all Booking Rooms
        Double updateBookingPrice = 0.0; // TODO : Update booking price

        for (BookingList bookingList : bookingLists) {
            for (Long idx : booking.getRoomId()) {
                if (Objects.equals(idx, bookingList.getRoomId())) {
                    bookingListRepository.delete(bookingList); // TODO: Delete Data for Table BookingList.
                    roomList.add(roomService.getRoomById(idx)); // TODO : Find All Rooms have been booking.
                }
            }
        }

        for (Room room : roomList) {
            RoomDTO roomDTO = new RoomDTO(room);
            updateBookingPrice = updateBookingPrice + (room.getRoomType().getPrice().doubleValue() / 2);
            roomDTO.setStatus(1); // TODO: Update Status in Room [1 = available, 2 = unavailable]
            roomService.updateRoom(room.getRoomId(), roomDTO); // TODO : Status in rooms table.
        }

        BigDecimal originalPrice = updateBooking.getBookingPrice(); // TODO: gert original price for table booking.
        updateBooking.setBookingPrice(BigDecimal.valueOf( originalPrice.doubleValue() - updateBookingPrice)); // TODO : calculator new price for booking room.
        BookingDTO bookingDTO = new BookingDTO(updateBooking);
        bookingService.updateBooking(bookingId, bookingDTO); // TODO : Update new booking price in table Booking.


        return getBookingById(bookingId);
    }


    /*
    * update status when customer make booking in table room
    * */
    private void updateStatusOfRoom (Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setStatus(2);
        roomService.updateRoom(room.getRoomId(), roomDTO);
    }


    /*
    * update price to customer when customer make booking in table booking
    * */
    private void updatePriceOfBook (Double totalBookPrice, Booking book) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingPrice(BigDecimal.valueOf(totalBookPrice / 2));
        bookingDTO.setStatus(2);
        bookingService.updateBooking(book.getBookingId(), bookingDTO);
    }


}
