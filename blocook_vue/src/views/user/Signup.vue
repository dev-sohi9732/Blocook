<template>
  <div>
    <LogoArea title="SIGN UP" />
    <SingupArea />
  </div>
</template>
<script>
import LogoArea from "@/components/LogoArea";
import SingupArea from "@/components/user/SignupArea";
export default {
  components: {
    LogoArea,
    SingupArea
  },
  data() {
    return {
      title: "signup page"
    };
  }
};
</script>
