<template>
  <TopNavbar />
  <div class="container mt-4">
    <div class="card-body d-flex justify-content-between align-items-center">
      <h2 class="mb-4">자유게시판</h2>
      <div>
        <button class="btn btn-success me-1">수정</button>
        <button class="btn btn-danger" @click="deletePost">삭제</button>
      </div>
    </div>
    <div class="card mb-4">
      <div class="card-body d-flex justify-content-between align-items-center">
        <h5 class="mb-0">{{ post.boardTitle }}</h5>
        <div>
          <span class="text-muted me-3">{{ post.name }}</span>
          <span class="text-muted me-3">{{ formatDate(post.boardUpdatedAt) }}</span>
          <span class="text-muted">조회수: 20017</span>
        </div>
      </div>
      <hr>
      <div class="card-body">
        <p>
          {{ post.boardContent }}
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import TopNavbar from "@/components/TopNavbar.vue";

export default {
  components: { TopNavbar },
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
        this.post = response.data;
        console.log('this.post',this.post)
      } catch (error) {
        console.error("Error fetching post:", error);
      }
    },
    async deletePost() {
      if (!confirm("정말 삭제하시겠습니까?")) return;
      try {
        await axios.delete(`/api/board/delete/${this.post.boardNo}`);
        alert("게시글이 삭제되었습니다.");
        this.$router.push('/board');
      } catch (error) {
        console.error("Error deleting post:", error);
      }
    },
    formatDate(dateString) {
      return dateString ? dateString.split('T')[0] : '';
    }
  },
  mounted() {
    this.fetchPost();
  }
};
</script>

<style scoped>
.container {
  margin: 0 auto;
  min-height: 700px;
}
</style>
