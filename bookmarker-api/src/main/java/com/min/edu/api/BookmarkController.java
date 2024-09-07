package com.min.edu.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.min.edu.domain.BookmarkDTO;
import com.min.edu.domain.BookmarkService;
import com.min.edu.domain.BookmarksDTO;
import com.min.edu.domain.CreateBookmarkRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {
	private final BookmarkService bookmarkService;

	// DTO 페이징 컨트롤러
	@GetMapping
	public BookmarksDTO getBookmark(@RequestParam(name = "page", defaultValue = "1") Integer page) {
		return bookmarkService.getBookmarks(page);
	}

//	//JPA 페이징 컨트롤러
//	@GetMapping
//	public Page<Bookmark> getBookmark(@RequestParam(name = "page", defaultValue = "1") Integer page) {
//		return bookmarkService.getBookmarksJPA(page);
//	}

	// get all search 요청처리
	public BookmarksDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "query", defaultValue = "") String query) {
		if (query == null || query.trim().length() == 0) {
			return bookmarkService.getBookmarks(page); // get all 요청처리
		}
		return bookmarkService.searchBookmarks(query, page); // search 요청처리
	}

	// 'create' 요청처리
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public BookmarkDTO createBookmark(@RequestBody @Valid CreateBookmarkRequest request) {
		System.out.println("POST 호출중");
		return bookmarkService.createBookmark(request);
	}

}