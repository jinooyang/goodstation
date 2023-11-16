import { createRouter, createWebHistory } from 'vue-router'
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
      component : () => import('../views/TripStartView.vue')

    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue')
    }, {
      path: '/trip/pickstation',
      name: 'tripstation',
      component: () => import('../views/TripAddStationView.vue')
    }
  ]
})

export default router
