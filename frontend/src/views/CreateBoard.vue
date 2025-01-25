<template>
  <TopNavbar />
  <div class="container mt-4">
    <h2>게시물 등록</h2>
    <form @submit.prevent="submitPost">
      <div class="mb-3">
        <label for="title" class="form-label">제목</label>
        <input type="text" id="title" v-model="title" class="form-control" required />
      </div>
      <div class="mb-3">
        <label for="content" class="form-label">내용</label>
        <textarea id="content" v-model="content" class="form-control" rows="5" required></textarea>
      </div>
      <button type="submit" class="btn btn-primary">등록</button>
    </form>
  </div>
</template>

<script>
import TopNavbar from "@/components/TopNavbar.vue";
import axios from "axios";

export default {
  components: {
    TopNavbar
  },
  data() {
    return {
      title: '', // 제목
      content: '' // 내용
    };
  },
  methods: {
    async submitPost() {
      try {
        // 게시물 데이터를 객체로 생성
        const username = localStorage.getItem("username");
        console.log('username',username)
        const postData = {
          boardTitle: this.title,
          boardContent: this.content
        };
        // POST 요청으로 데이터 전송
        const response = await axios.post('/api/board/create', postData); // API 요청
        console.log('Response:', response.data); // 응답 데이터 확인
        this.$router.push('/board'); // 등록 후 게시판 목록으로 이동
      } catch (error) {
        console.error("Error creating post:", error);
      }
    }
  }
}
</script>

<style scoped>
.container {
  margin: 0 auto;
  min-height: 700px;
}
</style>
