<template>
  <nav class="navbar navbar-expand-lg bg-body-tertiary custom-navbar">
    <div class="container-fluid">
      <router-link to="/" class="navbar-brand">JLOA</router-link>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent" style="margin-left: 230px">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item item-custom">
            <a class="nav-link active" aria-current="page" href="#">공지사항</a>
          </li>
          <li class="nav-item item-custom">
            <a class="nav-link active" aria-current="page" href="#">전투정보실</a>
          </li>
          <li class="nav-item item-custom">
            <router-link to="/board" class="nav-link active">자유게시판</router-link>
          </li>
        </ul>
        <form class="d-flex" role="search">
          <input class="form-control me-2" style="width: 250px" type="search" placeholder="캐릭터명을 입력하세요" aria-label="Search">
          <button class="btn btn-outline-success" type="submit" style="margin-right: 150px">검색</button>
        </form>
        <ul class="navbar-nav">
          <li class="nav-item" v-if="!loggedIn">
            <router-link to="/login" class="nav-link">로그인</router-link>
          </li>
          <li class="nav-item" v-if="loggedIn">
            <span class="nav-link">{{ name }}</span>
          </li>
          <li class="nav-item" v-if="loggedIn">
            <button class="nav-link btn btn-link" @click="logout">로그아웃</button>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      loggedIn: false,
      user: "",
      name: "",
    };
  },
  mounted() {
    this.checkLogin();
  },
  methods: {
    async checkLogin() {
      try {
        const response = await axios.get('/api/check-login');
        if (response.data.loggedIn) {
          this.loggedIn = true;
          this.user = response.data.user;
          this.name = response.data.name;
        } else {
          this.loggedIn = false;
        }
      } catch (error) {
        console.error("로그인 상태 확인 오류:", error);
      }
    },
    async logout() {
      try {
        const response = await axios.post('/api/logout');
        if (response.data.success) {
          this.loggedIn = false;
          this.user = "";
          this.$router.push('/');  // 로그아웃 후 로그인 페이지로 리디렉션
        }
      } catch (error) {
        console.error("로그아웃 오류:", error);
      }
    }
  }
};
</script>

<style scoped>
.custom-navbar {
  margin-bottom: 10px;
  border-bottom: 2px solid #dee2e6;
}
.item-custom {
  margin-right: 20px;
}
</style>
