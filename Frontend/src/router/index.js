import {createRouter, createWebHistory} from 'vue-router'
import MainView from '../views/MainView.vue'

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
            component: () => import('../views/Trip/TripAddStationView.vue')
        }, {
            path: '/trip/create',
            name: 'create',
            component: () => import('../views/Trip/TripCreateView.vue')
        }, {
            path: '/trip/attraction',
            name: 'attraction',
            component: () => import('../views/Trip/TripAddAttractionView.vue')
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
            component: () => import('../views/Board/BoardWrite.vue')
        }, {
            path: '/board/edit/:boardId',
            name: 'update',
            component: () => import('../views/Board/BoardUpdate.vue')
        }
    ]
})


export default router
