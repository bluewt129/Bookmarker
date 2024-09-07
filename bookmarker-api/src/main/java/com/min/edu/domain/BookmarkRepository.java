package com.min.edu.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

	@Query("""
			select new com.min.edu.domain.BookmarkDTO(b.id, b.title, b.url, b.createdAt) from Bookmark b
			""")
	Page<BookmarkDTO> findByBookmarks(Pageable pageable);

	@Query("""
			select new com.min.edu.domain.BookmarkDTO(b.id, b.title, b.url, b.createdAt) from Bookmark b
			where lower(b.title) like lower(concat('%',:query, '%'))
			""")
	Page<BookmarkDTO> searchBookmarks(String query, Pageable pageable);

	// JPQL의 사용이 아닌 JPA의 제공 쿼리 사용
//  https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html > JPA > JPA Query Methods
	//Page<BookmarkDTO> findByTitleContainsIgnoreCase(String query, Pageable pageable);
	
	 //인터페이스 프로덕션 객체로 반환타입 변환
	Page<BookmarkVM> findByTitleContainsIgnoreCase(String query, Pageable pageable);

}