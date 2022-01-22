import { createRouter, createWebHistory } from 'vue-router'
import Start from "@/views/Start"
import PointApp from "@/views/PointApp";

const routes = [
  {
    path: '/',
    name: 'Start',
    component: Start
  },
  {
    path: '/pointapp',
    name: 'PointApp',
    component: PointApp
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
