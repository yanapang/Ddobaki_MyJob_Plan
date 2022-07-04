package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.Dibs;

@Repository
public interface DibsDAO extends JpaRepository<Dibs, Integer> {

}
