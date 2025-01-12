import { createRouter, createWebHistory } from "vue-router";
import LoginPage from "@/views/LoginPage.vue"; // 내가 만들어준 로그인 컴포넌트 경로
import MainPage from '@/views/MainPage.vue'
import FreeBoard from "@/views/FreeBoard.vue";
import axios from "axios";

const routes = [
    {
        path: "/login",
        name: "login",
        component: LoginPage, // 로그인 컴포넌트
    },
    {
        path: "/",
        name: "main",
        component: MainPage, // 로그인 컴포넌트
    },
    {
        path: "/board",
        name: "board",
        component: FreeBoard, // 로그인 컴포넌트
        meta: { requiresAuth: true }
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

// 네비게이션 가드 추가
router.beforeEach(async (to, from, next) => {
    if (to.meta.requiresAuth) {
        try {
            const response = await axios.get('/api/check-login');
            if (response.data.loggedIn) {
                next(); // 로그인 상태일 경우 해당 라우트로 이동
            } else {
                next('/login'); // 로그인되지 않은 경우 로그인 페이지로 이동
            }
        } catch (error) {
            console.error("로그인 상태 확인 오류:", error);
            next('/login'); // 오류 발생 시 로그인 페이지로 이동
        }
    } else {
        next(); // 인증이 필요 없는 라우트는 그대로 이동
    }
});

export default router;