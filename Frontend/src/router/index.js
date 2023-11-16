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
            component: () => import('../views/TripStartView.vue')

        },
        {
            path: '/login',
            name: 'login',
            component: () => import('../views/LoginView.vue')
        }, {
            path: '/trip/pickstation',
            name: 'tripstation',
            component: () => import('../views/TripAddStationView.vue')
        }, {
            path: '/trip/create',
            name: 'create',
            component: () => import('../views/TripCreateView.vue')
        }, {
            path: '/trip/attraction',
            name: 'attraction',
            component: () => import('../views/TripAddAttractionView.vue')
        }, {
            path: '/register',
            name: 'register',
            component: () => import('../views/RegisterView.vue')
        }
    ]
})

export default router
