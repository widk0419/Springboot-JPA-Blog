package com.widk.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.widk.blog.model.Board;


// DAO
// 자동으로 bean 등록이 된다.
// @Repository 생략이 가능.

public interface BoardRepository extends JpaRepository<Board, Integer>{

	
}


