package com.crio.RentRead.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.crio.RentRead.model.*;



public interface BookRepo extends JpaRepository<Book, Long>{

}