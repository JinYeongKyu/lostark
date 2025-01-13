<template>
  <TopNavbar />
  <div class="container-sm mt-4">
    <h2 class="mb-4">자유게시판</h2>
    <table class="table">
      <thead>
      <tr>
        <th scope="col">번호</th>
        <th scope="col" style="width: 40%">제목</th>
        <th scope="col">작성자</th>
        <th scope="col">작성일자</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(notice, idx) in notices" :key="idx">
        <td>
          {{ notice.boardNo }}
        </td>
        <td>
          <router-link :to="`/board/detail/${notice.boardNo}`" class="text-decoration-none text-dark">
            {{ notice.boardTitle }}
          </router-link>
        </td>
        <td>{{ notice.name }}</td>
        <td>{{ formatDate(notice.boardUpdatedAt) }}</td>
      </tr>
      </tbody>
    </table>

    <!-- 페이지네이션 -->

  </div>
  <nav aria-label="Page navigation" class="d-flex justify-content-center mt-4">
    <ul class="pagination">
      <li class="page-item">
        <a class="page-link" href="#" aria-label="Previous">
          <span aria-hidden="true">&laquo;</span>
        </a>
      </li>
      <li class="page-item active"><a class="page-link" href="#">1</a></li>
      <li class="page-item"><a class="page-link" href="#">2</a></li>
      <li class="page-item">
        <a class="page-link" href="#" aria-label="Next">
          <span aria-hidden="true">&raquo;</span>
        </a>
      </li>
    </ul>
  </nav>
  <AppFooter />
</template>

<script>
import TopNavbar from "@/components/TopNavbar.vue";
import AppFooter from "@/components/AppFooter.vue";
import axios from "axios";

export default {
  components: {
    AppFooter,
    TopNavbar
  },
  data() {
    return {
      notices: [] // 게시글 목록을 저장할 배열
    };
  },
  methods: {
    async fetchPosts() {
      try {
        const response = await axios.get('/api/board'); // API 요청
        this.notices = response.data; // API에서 받은 데이터를 notices에 저장
        console.log(this.notices)
      } catch (error) {
        console.error("Error fetching posts:", error);
      }
    },
    formatDate(dateString) {
      const date = new Date(dateString);
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1
      const day = String(date.getDate()).padStart(2, '0'); // 일은 2자리로 맞춤
      return `${year}-${month}-${day}`;
    }
  },
  mounted() {
    this.fetchPosts();
  }
}
</script>

<style scoped>
.table th {
  border-top: 2px solid #dee2e6;
  background-color: #f8f9fa;
}

.table td {
  vertical-align: middle;
}

.container-sm {
  max-width: 1000px;  /* 테이블 전체 최대 너비 제한 */
  margin: 0 auto;
  min-height: 500px;
}
</style>