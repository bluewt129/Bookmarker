// services/api/fetchBookmarks.ts
import { BookmarksResponse } from "@/app/types/bookmark";
import axios from "axios";

const getApiUrl = () => {
  // 서버 사이드 API URL
  const serverApiUrl = process.env.SERVER_SIDE_API_BASE_URL;
  
  // 클라이언트 사이드 API URL
  const clientApiUrl = process.env.NEXT_PUBLIC_CLIENT_SIDE_API_BASE_URL || "http://localhost:18585";
  
  // 서버 사이드에서는 serverApiUrl 사용
  if (typeof window === 'undefined') {
    return serverApiUrl || clientApiUrl; // 서버 사이드 환경에서 URL이 없을 경우 기본값 사용
  }
  
  // 클라이언트 사이드에서는 clientApiUrl 사용
  return clientApiUrl;
};

export const fetchBookmarks = async (page: number, query?: string): Promise<BookmarksResponse> => {
  const apiUrl = getApiUrl();  // 서버 배포시 사용
  const url = query ? `${apiUrl}/api/bookmarks?page=${page}&query=${query}` : `${apiUrl}/api/bookmarks?page=${page}`;
  const res = await axios.get<BookmarksResponse>(url);
  console.log(res.data);
  return res.data;
};


export const saveBookmark = async (bookmark: { title: string, url: string }) => {
  try {
    const apiUrl = getApiUrl();  // 서버 배포시 사용
    console.log('API URL:', apiUrl);  // URL이 정확한지 확인

    const res = await axios.post(`${apiUrl}/api/bookmarks`, bookmark, {
      headers: {
        'Content-Type': 'application/json',
      },
    });

    console.log('Response:', res.data);  // 응답 확인
    return res.data;  // 성공 시 응답 데이터 반환
  } catch (error) {
    console.error("Error saving bookmark:", error);
    throw new Error("북마크 저장에 실패하였습니다");  // 오류를 throw하여 호출자에게 전달
  }
};


