package com.project.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	
	@Modifying
	@Query(value="UPDATE board SET count = count+1 WHERE id = :id", nativeQuery = true)
	public void updateView(@Param("id") int id);
	
}
