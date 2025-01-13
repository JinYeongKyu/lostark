<template>
  <TopNavbar />
  <div class="container mt-4">
    <!-- 제목 -->
    <div class="card mb-3">
      <div class="card-header">
        <h5>{{post.boardTitle}}</h5>
      </div>
      <div class="card-body">
        <div class="d-flex justify-content-between">
          <span class="text-muted">{{post.boardUpdatedAt}}</span>
          <span class="text-muted">조회수: 20017</span>
        </div>
      </div>
    </div>

    <!-- 내용 -->
    <div class="card mb-3">
      <div class="card-body">
        <p>
          {{post.boardContent}}
        </p>
      </div>
    </div>

    <!-- 댓글 -->
    <div class="card mb-3">
      <div class="card-header">댓글 (27)</div>
      <div class="card-body">
        <ul class="list-group">
          <li class="list-group-item">좋은 정보 감사합니다!</li>
          <li class="list-group-item">점검이 빠르게 끝나길 바랍니다.</li>
          <li class="list-group-item">오류 없이 잘 끝났으면 좋겠네요.</li>
        </ul>
      </div>
    </div>
  </div>
  <AppFooter />
</template>

<script>
import axios from "axios";
import TopNavbar from "@/components/TopNavbar.vue";
import AppFooter from "@/components/AppFooter.vue";

export default {
  components: {AppFooter, TopNavbar},
  data() {
    return {
      post: {} // 게시글 상세 정보를 저장할 객체
    };
  },
  methods: {
    async fetchPost() {
      const postId = this.$route.params.id;
      try {
        const response = await axios.get(`/api/board/detail/${postId}`); // API 요청
        this.post = response.data; // 데이터 저장
        console.log(this.post)
      } catch (error) {
        console.error("Error fetching post:", error);
      }
    },
    formatDate(dateString) {
      const date = new Date(dateString);
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const day = String(date.getDate()).padStart(2, '0');
      return `${year}-${month}-${day}`;
    }
  },
  mounted() {
    this.fetchPost();
  }
}
</script>

<style scoped>
.container {
  max-width: 800px;
  margin: 0 auto;
}
</style>
