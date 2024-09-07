import type { Bookmark, BookmarksResponse } from './../types/bookmark.d'; // 올바른 경로로 수정
import Link from 'next/link';
 // Props 타입 정의
interface BookmarksProps {
  bookmark: Bookmark;
 }
 // Bookmarks 컴포넌트 정의
const Bookmark :React.FC<BookmarksProps> = ({ bookmark }) => {
  return (
    <div>
      <div className="alert alert-primary" role="alert">
        <h5>
            <Link href={bookmark.url}>{bookmark.title}</Link>
        </h5>
      </div>
    </div>
  );
 };
 export default Bookmark;