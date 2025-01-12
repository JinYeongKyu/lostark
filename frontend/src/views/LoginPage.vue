<template>
  <TopNavbar />
  <div class="login-container d-flex justify-content-center align-items-center">
    <div class="login-box">
      <form @submit.prevent="handleLogin">
        <div class="mb-3">
          <label for="email" class="form-label">이메일</label>
          <input
              type="email"
              class="form-control"
              id="email"
              v-model="email"
              placeholder="이메일을 입력하세요"
              required
          />
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">비밀번호</label>
          <input
              type="password"
              class="form-control"
              id="password"
              v-model="password"
              placeholder="비밀번호를 입력하세요"
              required
          />
        </div>
        <button type="button" class="btn btn-outline-secondary w-100 mb-3">
          디스코드 계정으로 로그인
        </button>
        <div class="form-check mb-3">
          <input
              class="form-check-input"
              type="checkbox"
              id="remember"
              v-model="rememberMe"
          />
          <label class="form-check-label" for="remember">
            로그인 상태 유지
          </label>
        </div>
        <button type="submit" class="btn btn-custom w-100 mb-2">
          로그인
        </button>
      </form>
      <div class="d-flex justify-content-between mt-3">
        <a href="#" class="link-text">회원가입</a>
        <a href="#" class="link-text">앗, 비밀번호를 잊어버렸어요!</a>
      </div>
    </div>
  </div>
  <AppFooter />
</template>

<script>
import axios from 'axios';
import TopNavbar from "@/components/TopNavbar.vue";
import AppFooter from "@/components/AppFooter.vue";

export default {
  name: 'LoginPage',
  components: {
    AppFooter,
    TopNavbar
  },
  data() {
    return {
      email: "",
      password: "",
      rememberMe: false,
    };
  },
  methods: {
    async handleLogin() {
      try {
        const response = await axios.post('/api/login', {
          email: this.email,
          password: this.password,
        });
        if (response.data.success) {
          this.$router.push("/");
          alert("환영합니다.");
        } else {
          alert("로그인 실패. 이메일이나 비밀번호를 확인해주세요.");
        }
      } catch (error) {
        console.error("로그인 요청 오류:", error);
      }
    },
  },
};
</script>

<style scoped>
.login-container {
  height: 660px;
  background-color: #f8f9fa;  /* 밝은 배경색 */
}

.login-box {
  background-color: #ffffff;  /* 흰색 배경 */
  padding: 40px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
  color: #212529;  /* 어두운 텍스트 색상 */
}

.btn-custom {
  background: linear-gradient(to right, #0d6efd, #0b5ed7);  /* 파란 계열 그라데이션 */
  color: white;
  border: none;
}

.btn-custom:hover {
  background: linear-gradient(to left, #0d6efd, #0b5ed7);
}

.link-text {
  color: #0d6efd;  /* Bootstrap 파란색 */
  text-decoration: none;
}

.link-text:hover {
  text-decoration: underline;
}
</style>
