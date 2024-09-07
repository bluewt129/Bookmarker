/** @type {import('next').NextConfig} */
const nextConfig = {
    reactStrictMode: true,
    swcMinify: true,
    async redirects() {
      return [
        {
          source: '/', // 리다이렉션할 출발지 URL 패턴
          destination: '/bookmarks', // 리다이렉션할 도착지 URL
          permanent: true // 리다이렉션이 영구적인지 여부
        }
      ];
    }
  };
  
  export default nextConfig;
  