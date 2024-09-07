// app/bookmarks/page.tsx
import Bookmarks from '../components/Bookmarks';
import Pagination from '../components/Pagination';
import SearchForm from '../components/SearchForm';
import { fetchBookmarks } from '../services/api/fetchBookmarks';

const Home = async ({ searchParams }: { searchParams: { page?: string, query?: string } }) => {
  const page = searchParams.page ? parseInt(searchParams.page) : 1;
  const query = searchParams.query ? String(searchParams.query) : ""; 
  const bookmarks = await fetchBookmarks(page, query);  // fetchBookmarks 호출
  return (
    <div>
      <h2>Welcome to Bookmark</h2>
      <SearchForm />
      <Pagination bookmarks={bookmarks} query={query} />
      <ul style={{ paddingLeft: 0 }}>
        <Bookmarks bookmarks={bookmarks} />
      </ul>
    </div>
  );
};
export default Home;
