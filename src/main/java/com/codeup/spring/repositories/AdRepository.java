package com.codeup.spring.repositories;

import com.codeup.spring.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Long> {

}
