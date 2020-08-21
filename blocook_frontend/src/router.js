import Vue from "vue";
import Router from "vue-router";
import AppHeader from "./layout/AppHeader";
import Main from "./views/Main.vue";
import Category from "./views/category/Category.vue";
import Country from "./views/category/Country.vue";
import Login from "./views/user/Login.vue";
import FindIdPw from "./views/user/FindIdPw.vue";
import Signup from "./views/user/Signup.vue";
import Mypage from "./views/user/Mypage.vue";
import UserInfo from "./views/user/UserInfo.vue";
import Recipe from "./views/recipe/Recipe.vue";
import SearchRecipe from "./views/recipe/SearchRecipe.vue";
import AddRecipe from "./views/recipe/AddRecipe.vue";
import EditRecipe from "./views/recipe/EditRecipe.vue";
import MyRecipe from "./views/recipe/MyRecipe.vue";
import Community from "./views/community/Community.vue";
import PostEditor from "./views/community/PostEditor.vue";
import MyPost from "./views/community/MyPost.vue";
import DetailPost from "./views/community/DetailPost.vue";
import EditPost from "./views/community/EditPost.vue";
import Timer from "./components/recipe/Timer.vue";
import PageNotFound from "./views/PageNotFound.vue";
Vue.use(Router);

export default new Router({
  mode: 'history',
  linkExactActiveClass: "active",
  routes: [
    {
      path: "/timer",
      name: "timer",
      components: {
        header: AppHeader,
        default: Timer
      }
    },
    {
      path: "/",
      name: "main",
      components: {
        header: AppHeader,
        default: Main
      }
    },
    {
      path: "/login",
      name: "login",
      components: {
        header: AppHeader,
        default: Login
      }
    },
    {
      path: "/findidpw",
      name: "findidpw",
      components: {
        header: AppHeader,
        default: FindIdPw
      }
    },
    {
      path: "/signup",
      name: "signup",
      components: {
        header: AppHeader,
        default: Signup
      }
    },
    {
      path: "/mypage",
      name: "mypage",
      components: {
        header: AppHeader,
        default: Mypage
      }
    },
    {
      path: "/userinfo",
      name: "userinfo",
      components: {
        header: AppHeader,
        default: UserInfo
      }
    },
    {
      path: "/recipe",
      name: "recipe",
      components: {
        header: AppHeader,
        default: Recipe
      }
    },
    {
      path: "/search",
      name: "search",
      components: {
        header: AppHeader,
        default: SearchRecipe
      }
    },
    {
      path: "/addrecipe",
      name: "addrecipe",
      components: {
        header: AppHeader,
        default: AddRecipe
      }
    },
    {
      path: "/editrecipe",
      name: "editrecipe",
      components: {
        header: AppHeader,
        default: EditRecipe
      }
    },
    {
      path: "/myrecipe",
      name: "myrecipe",
      components: {
        header: AppHeader,
        default: MyRecipe
      }
    },
    {
      path: "/category",
      name: "category",
      components: {
        header: AppHeader,
        default: Category
      }
    },
    {
      path: "/country",
      name: "country",
      components: {
        header: AppHeader,
        default: Country
      }
    },
    {
      path: "/community",
      name: "community",
      components: {
        header: AppHeader,
        default: Community
      }
    },
    {
      path: "/posteditor",
      name: "posteditor",
      components: {
        header: AppHeader,
        default: PostEditor
      }
    },
    {
      path: "/mypost",
      name: "mypost",
      components: {
        header: AppHeader,
        default: MyPost
      }
    },
    {
      path: "/detailpost",
      name: "detailpost",
      components: {
        header: AppHeader,
        default: DetailPost
      }
    },
    {
      path: "/editpost",
      name: "editpost",
      components: {
        header: AppHeader,
        default: EditPost
      }
    },
    {
      path: "*",
      redirect: '/404'
    },
    {
      path: '/404',
      component : PageNotFound
    }
  ],
  scrollBehavior: to => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  }
});
