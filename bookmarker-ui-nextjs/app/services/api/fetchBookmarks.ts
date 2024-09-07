import { BookmarksResponse } from "@/app/types/bookmark";
import axios from "axios";

const API_BASE_URL = "http://localhost:18585";

// 북마크 목록을 가져오는 함수
export const fetchBookmarks = async (page: number, query?: string): Promise<BookmarksResponse> => {
  // query가 없을 때는 쿼리 파라미터를 제외
  const url = query ? `${API_BASE_URL}/api/bookmarks?page=${page}&query=${query}` : `${API_BASE_URL}/api/bookmarks?page=${page}`;
  
  const res = await axios.get<BookmarksResponse>(url);
  console.log(res.data);
  return res.data;
};

// 북마크를 저장하는 함수
export const saveBookmark = async (bookmark: { title: string, url: string }) => {
  try {
    const res = await axios.post(`${API_BASE_URL}/api/bookmarks`, bookmark);
    return res.data; // 성공 시 응답 데이터 반환
  } catch (error) {
    // 에러가 발생한 경우 에러 메시지 처리
    console.error("Error saving bookmark:", error);
    throw new Error("북마크 저장에 실패하였습니다"); // 오류를 throw하여 호출자에게 전달
  }
};
