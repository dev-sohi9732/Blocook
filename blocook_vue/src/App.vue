<template>
  <div id="app">
    <div id="nav">
      <Header-Navbar />
    </div>
    <router-view to="/" />
  </div>
</template>

<script>
import HeaderNavbar from '@/components/HeaderNavbar.vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

export default {
  components: {
    HeaderNavbar
  }
}
</script>
