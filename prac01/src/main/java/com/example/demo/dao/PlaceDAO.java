package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.Place;

@Repository
public interface PlaceDAO extends JpaRepository<Place, Integer> {

	
}
