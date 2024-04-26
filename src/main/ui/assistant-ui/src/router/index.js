import { createRouter, createWebHistory } from 'vue-router'
import ShelfItemsView from '../views/ShelfItemsView.vue'
import ShoppingView from '../views/ShoppingView.vue'

const routes = [
  {
    path: '/shopping',
    name: 'shopping',
    component: ShoppingView,
  },
  {
    path: '/shelf',
    name: 'shelf',
    component: ShelfItemsView,
  },
  {
    path: '/error',
    name: 'error',
    component: ShelfItemsView,
  },
  {
    path: '/:catchAll(.*)',
    redirect: '/error'
  },
  {
    path: '/',
    redirect: '/shopping'
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

