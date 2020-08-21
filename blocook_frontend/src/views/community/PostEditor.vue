<template>
    <div>
        <NavBackgroundCommunity></NavBackgroundCommunity>
        <Editor></Editor>
    </div>
</template>
<script>
import Editor from "@/components/community/Editor";
import NavBackgroundCommunity from "@/components/main/NavBackgroundCommunity";

export default {
  name: "posteditor",
  components: {
    Editor,
    NavBackgroundCommunity
  },
  created() {
    if(this.$store.state.user.nickname == "") {
      alert("로그인이 필요합니다.");
      this.$router.push("/login");
    }
  }
};
</script>
