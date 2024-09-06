package com.min.edu.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.min.edu.domain.BookmarksDTO;
import com.min.edu.domain.BookmarkService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {
	private final BookmarkService bookmarkService;

  //DTO 페이징 컨트롤러	
	 @GetMapping
	 public BookmarksDTO getBookmark(@RequestParam(name = "page", defaultValue = "1" ) Integer page){
	 return bookmarkService.getBookmarks(page);
	 }
	
//	//JPA 페이징 컨트롤러
//	@GetMapping
//	public Page<Bookmark> getBookmark(@RequestParam(name = "page", defaultValue = "1") Integer page) {
//		return bookmarkService.getBookmarksJPA(page);
//	}
	

}