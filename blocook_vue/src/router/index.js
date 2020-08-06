import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../views/Main.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Main',
    component: Main
  },
  {
    path: '/about',
    name: 'About',
    component: () => import('../views/About.vue')
  },
  /* community */
  {
    path: '/community',
    name: 'Community',
    component: () => import('../views/community/Community.vue')
  },
  {
    path: '/post',
    name: 'Post',
    component: () => import('../views/community/Post.vue')
  },
  {
    path: '/mypost',
    name: 'Mypost',
    component: () => import('../views/community/Mypost.vue')
  },
  /* recipe */
  {
    path: '/addrecipe',
    name: 'Addrecipe',
    component: () => import('../views/recipe/Addrecipe.vue')
  },
  {
    path: '/category',
    name: 'Category',
    component: () => import('../views/recipe/Category.vue')
  },
  {
    path: '/myrecipe',
    name: 'Myrecipe',
    component: () => import('../views/recipe/Myrecipe.vue')
  },
  {
    path: '/recipe',
    name: 'Recipe',
    component: () => import('../views/recipe/Recipe.vue')
  },
  {
    path: '/search',
    name: 'Search',
    component: () => import('../views/recipe/Search.vue')
  },
  /* user */
  {
    path: '/findidpw',
    name: 'Findidpw',
    component: () => import('../views/user/Findidpw.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/user/Login.vue')
  },
  {
    path: '/mypage',
    name: 'Mypage',
    component: () => import('../views/user/Mypage.vue')
  },
  {
    path: '/userinfo',
    name: 'UserInfo',
    component: () => import('../views/user/UserInfo.vue')
  },
  {
    path: '/signup',
    name: 'Signup',
    component: () => import('../views/user/Signup.vue')
  },
  /* worldcup */
  {
    path: '/worldcup',
    name: 'Worldcup',
    component: () => import('../views/worldcup/Worldcup.vue')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
