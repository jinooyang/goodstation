import {createRouter, createWebHistory} from 'vue-router'
import MainView from '../views/MainView.vue'

import axios from "axios";
const { VITE_VUE_API_URL, VITE_ELECTRIC_CHARGING_STATION_URL } = import.meta.env;
const axiosInstance = axios.create({
    baseURL: VITE_VUE_API_URL,
});

const checkAuth = (to, from, next) => {


    axiosInstance.get('/auth/user', {
        headers: {
            "X-AUTH-TOKEN": sessionStorage.getItem("accessToken")
        }
    })
        .then(response => {
            if (response.data.data.auth) {
                next();
            } else {
                alert("1로그인이 필요한 페이지입니다. 로그인 페이지로 이동합니다.");
                next('/login');
            }
        })
        .catch(error => {
            console.error(error);
            alert("2로그인이 필요한 페이지입니다. 로그인 페이지로 이동합니다.");
            next('/login');
        });
};

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: MainView
        },
        {
            path: '/trip',
            name: 'trip',
            component: () => import('../views/Trip/TripStartView.vue')

        },
        {
            path: '/login',
            name: 'login',
            component: () => import('../views/LoginRegister/LoginView.vue')
        }, {
            path: '/trip/pickstation',
            name: 'tripstation',
            component: () => import('../views/Trip/TripAddStationView.vue'),
            beforeEnter: checkAuth
        }, {
            path: '/trip/create',
            name: 'create',
            component: () => import('../views/Trip/TripCreateView.vue'),
            beforeEnter: checkAuth

        }, {
            path: '/trip/attraction',
            name: 'attraction',
            component: () => import('../views/Trip/TripAddAttractionView.vue'),
            beforeEnter: checkAuth
        }, {
            path: '/register',
            name: 'register',
            component: () => import('../views/LoginRegister/RegisterView.vue')
        }, {
            path: '/board',
            name: 'board',
            component: () => import('../views/Board/BoardView.vue')
        }, {
            path: '/board/:boardId',
            name: 'detail',
            component: () => import('../views/Board/BoardDetailView.vue')
        }, {
            path: '/news',
            name: 'news',
            component: () => import('../views/Board/NewsView.vue')
        }, {
            path: '/board/write',
            name: 'write',
            component: () => import('../views/Board/BoardWrite.vue'),
            beforeEnter: checkAuth
        }, {
            path: '/board/edit/:boardId',
            name: 'update',
            component: () => import('../views/Board/BoardUpdate.vue'),
            beforeEnter: checkAuth

        }, {
            path: '/result',
            name: 'result',
            component: () => import('../views/Trip/Result.vue'),
            beforeEnter: checkAuth
        },
        {
            path : '/mypage',
            name : 'mypage',
            component : ()=>import('../views/MyPage/MyPage.vue'),
            beforeEnter: checkAuth
        }

    ]
})


export default router
