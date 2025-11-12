// Kinshuk Khandelwal (13565)
package com.bookhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookhub.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
