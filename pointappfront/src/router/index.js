import { createRouter, createWebHistory } from 'vue-router'
import Start from "@/views/Start"
import PointApp from "@/views/PointApp";
import Registration from "@/views/Registration";

const routes = [
  {
    path: '/',
    name: 'Start',
    component: Start
  },
  {
    path: '/registration',
    name: 'Registration',
    component: Registration
  },
  {
    path: '/pointapp',
    name: 'PointApp',
    component: PointApp,
    params: true
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
