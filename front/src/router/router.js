import {createRouter, createWebHistory} from "vue-router";
import Start from "@/pages/Start";

const routes = [
    {
        path: "/",
        component: Start
    }
]

const router = createRouter({
    routes,
    history: createWebHistory(process.env.BASE_URL)
})

export default router;
