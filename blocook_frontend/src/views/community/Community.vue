<template>
    <div>
        <LogoArea></LogoArea>
        <CommunityArea></CommunityArea>
    </div>
</template>
<script>
import CommunityArea from "@/components/community/CommunityArea";
import LogoArea from "@/components/main/LogoArea";

export default {
  name: "community",
  components: {
    CommunityArea,
    LogoArea
  },
  created(){this.$store.state.speechRecognition.stop()}
};
</script>
