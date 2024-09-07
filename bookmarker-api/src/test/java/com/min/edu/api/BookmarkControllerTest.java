package com.min.edu.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.min.edu.domain.Bookmark;
import com.min.edu.domain.BookmarkRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
	    "spring.flyway.enabled=false"
	})
class BookmarkControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private BookmarkRepository bookmarkRepository;

	private List<Bookmark> boomarks;

	//@BeforeEach
	void setUp() {
		bookmarkRepository.deleteAllInBatch();
	}

	@BeforeEach
	void setUp2() {
		bookmarkRepository.deleteAllInBatch();
		boomarks = new ArrayList<>();
		boomarks.add(new Bookmark(null, "devopsbookmark", "https://www.devopsbookmark.com", Instant.now()));
		boomarks.add(new Bookmark(null, "techblog", "https://www.techblog.com", Instant.now()));
		boomarks.add(new Bookmark(null, "codingresource", "https://www.codingresource.com", Instant.now()));
		boomarks.add(new Bookmark(null, "programminghub", "https://www.programminghub.com", Instant.now()));
		boomarks.add(new Bookmark(null, "javaguide", "https://www.javaguide.com", Instant.now()));
		boomarks.add(new Bookmark(null, "webdevtips", "https://www.webdevtips.com", Instant.now()));
		boomarks.add(new Bookmark(null, "cloudcomputing", "https://www.cloudcomputing.com", Instant.now()));
		boomarks.add(new Bookmark(null, "datasciencetools", "https://www.datasciencetools.com", Instant.now()));
		boomarks.add(new Bookmark(null, "aiinsights", "https://www.aiinsights.com", Instant.now()));
		boomarks.add(new Bookmark(null, "devtools", "https://www.devtools.com", Instant.now()));
		boomarks.add(new Bookmark(null, "machinelearning", "https://www.machinelearning.com", Instant.now()));
		boomarks.add(new Bookmark(null, "opensource", "https://www.opensource.com", Instant.now()));
		boomarks.add(new Bookmark(null, "cybersecurity", "https://www.cybersecurity.com", Instant.now()));
		boomarks.add(new Bookmark(null, "frontendfocus", "https://www.frontendfocus.com", Instant.now()));
		boomarks.add(new Bookmark(null, "backendbasics", "https://www.backendbasics.com", Instant.now()));
		bookmarkRepository.saveAll(boomarks);
	}

//	@Test
	void shouldBookmarks1() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/bookmarks")).andExpect(status().isOk())
				.andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(0)));
	}

	@Test
	void shouldBookmarks2() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/bookmarks")).andExpect(status().isOk()).andExpect(status().isOk())
				.andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(15)))
				.andExpect(jsonPath("$.totalPages", CoreMatchers.equalTo(2)))
				.andExpect(jsonPath("$.currentPage", CoreMatchers.equalTo(1)))
				.andExpect(jsonPath("$.isFirst", CoreMatchers.equalTo(true)))
				.andExpect(jsonPath("$.isLast", CoreMatchers.equalTo(false)))
				.andExpect(jsonPath("$.hasNext", CoreMatchers.equalTo(true)))
				.andExpect(jsonPath("$.hasPrevious", CoreMatchers.equalTo(false)));
	}
	
	@ParameterizedTest
	@CsvSource({"1, 15, 2, 1, true, false, true, false",
				"2, 15, 2, 2, false, true, false, true"})
	void shouldBookmarks(int pageNo, int totalElements, int totalPages, int currentPage,
				boolean isFirst, boolean isLast, boolean hasNext, boolean hasPrevious) throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/bookmarks?page="+pageNo)).andExpect(status().isOk()).andExpect(
				 jsonPath("$.totalElements", CoreMatchers.equalTo(totalElements)))
				 .andExpect(jsonPath("$.totalPages", CoreMatchers.equalTo(totalPages)))
				 .andExpect(jsonPath("$.currentPage", CoreMatchers.equalTo(currentPage)))
				 .andExpect(jsonPath("$.isFirst", CoreMatchers.equalTo(isFirst)))
				 .andExpect(jsonPath("$.isLast", CoreMatchers.equalTo(isLast)))
				 .andExpect(jsonPath("$.hasNext", CoreMatchers.equalTo(hasNext)))
				 .andExpect(jsonPath("$.hasPrevious", CoreMatchers.equalTo(hasPrevious)));
	}

}
