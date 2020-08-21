<template>
  <div>
    <LogoArea title="USER INFO" />
    <UserInfoArea />
  </div>
</template>
<script>
import LogoArea from "@/components/LogoArea";
import UserInfoArea from "@/components/user/UserInfoArea";
export default {
  components: {
    LogoArea,
    UserInfoArea
  },
  data() {
    return {
      title: "user info page"
    };
  }
};
</script>
