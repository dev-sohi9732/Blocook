<template>
  <div>
    <LogoArea title="LOGIN" />
    <LoginArea />
  </div>
</template>
<script>
import LogoArea from "@/components/LogoArea";
import LoginArea from "@/components/user/LoginArea";
export default {
  components: {
    LogoArea,
    LoginArea
  },
  data() {
    return {
      title: "login page"
    };
  }
};
</script>
