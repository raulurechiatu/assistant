import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/Home.vue'

const routes = [
  {
    path: '/home',
    name: 'upload',
    component: HomeView,
    meta: {
      requiresAuth: true,
      requiresAdmin: false
    }
  },
  {
    path: '/error',
    name: 'error',
    component: HomeView,
    meta: {
      requiresAuth: false,
      requiresAdmin: false
    }
  },
  {
    path: '/:catchAll(.*)',
    redirect: '/error'
  },
  {
    path: '/',
    redirect: '/home'
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

