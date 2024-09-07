package com.min.edu.domain;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {
	private final BookmarkRepository repository;
	private final BookmarkMapper bookmarkMapper;

	// JPA 페이징
	@Transactional(readOnly = true)
	public Page<Bookmark> getBookmarksJPA(Integer page) {
		int pageNo = page < 1 ? 0 : page - 1;
		Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");
		return repository.findAll(pageable);
	}

	// DTO 페이징 //Dirty Check BookmarkDTO
	@Transactional(readOnly = true)
	public BookmarksDTO getBookmarks(Integer page) {
		int pageNo = page < 1 ? 0 : page - 1;
		Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");
		// return new BookmarksDTO(repository.findAll(pageable));
		// 이부분은 bookmarkMapper 삭제했음 BookmarkDTO 소프트삭제
		// Page<BookmarkDTO> bookmarkPage = repository.findAll(pageable).map(bookmark ->
		// bookmarkMapper.toDto(bookmark));
		Page<BookmarkDTO> bookmarkPage = repository.findByBookmarks(pageable);
		return new BookmarksDTO(bookmarkPage);
	}

	// TODO 029 'search' 요청처리 service
	@Transactional(readOnly = true)
	public BookmarksDTO searchBookmarks(String query, Integer page) {
		int pageNo = page < 1 ? 0 : page - 1;
		Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");
		Page<BookmarkDTO> bookmarkPage = repository.searchBookmarks(query, pageable);
		return new BookmarksDTO(bookmarkPage);
	}

	@Transactional(readOnly = true)
	public BookmarksDTO searchBookmarks2(String query, Integer page) {
		int pageNo = page < 1 ? 0 : page - 1;
		Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");
		Page<BookmarkDTO> bookmarkPage = repository.searchBookmarks(query, pageable);
		// JPA 메소드를 사용한 service 실행
		// Page<BookmarkDto> bookmarkPage =
		// repository.findByTitleContainsIgnoreCase(query, pageable);
		// 인터페이스 프로적션 Repository 실행
		Page<BookmarkVM> bookmarkVMPage = repository.findByTitleContainsIgnoreCase(query, pageable);
		System.out.println("인터페이스 기반의 프로적션 객체 :" + bookmarkVMPage);
		return new BookmarksDTO(bookmarkPage);
	}

	// 입력받은 값을 처리 하기 위한 Bookmark 객체를 생성 입력 respository 실행
	public BookmarkDTO createBookmark(@RequestBody @Valid CreateBookmarkRequest request) {
		Bookmark bookmark = new Bookmark(null, request.getTitle(), request.getUrl(), Instant.now());
		Bookmark saveBookmark = repository.save(bookmark);
		return bookmarkMapper.toDto(saveBookmark);
	}

}
