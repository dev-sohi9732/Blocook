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
  created(){
    if (typeof webkitSpeechRecognition === 'function') {
      this.$store.commit('recognition/setOnEnd', null);
      this.$store.state.recognition.speechRecognition.stop();
      this.$store.state.audio.pause();
    }  
  }
};
</script>
