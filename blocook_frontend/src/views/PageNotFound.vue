<template>
  <div style="">
      <center>
          <div style="background-color: rgb(205, 100, 100); height:120px; margin-bottom:-3px;"></div>
          <div style="margin-bottom:-20px;" class="pagenotfound">
                <h1 style="color:white; font-weight:bold">404</h1>
            <h1 style="margin-top:-20px;color:white; font-weight:bold">PageNotFound</h1>
            <router-link :to="{path : '/'}" style="color:#005b8f;font-weight:bold; font-size:1.5rem">메인으로 돌아가기</router-link>
          </div>
          <LogoArea></LogoArea>
          <section class="section-hero section-shaped section-lg my-0">
                <div class="shape shape-style-1 gradient">
                    <span class="span-150"></span>
                    <span class="span-50"></span>
                    <span class="span-50"></span>
                    <span class="span-75"></span>
                    <span class="span-100"></span>
                    <span class="span-75"></span>
                    <span class="span-50"></span>
                    <span class="span-100"></span>
                    <span class="span-50"></span>
                    <span class="span-100"></span>
                </div>
            </section>
      </center>
  </div>
</template>

<script>
import LogoArea from "@/components/main/LogoArea";
export default {
    name: "PageNotFound",
    components: {
    LogoArea,
  }
}
</script>

<style>
.pagenotfound {
    background-color: rgb(205, 100, 100)
}
@media(min-height: 190px) {
    section {
        height: 100vh;
    }
}
</style>