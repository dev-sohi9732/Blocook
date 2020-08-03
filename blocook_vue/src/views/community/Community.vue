<template>
  <div class="mainview">
    <CommunityMainArea />
  </div>
</template>
<script>
import CommunityMainArea from "@/components/community/CommunityMainArea";
export default {
  components: {
    CommunityMainArea,
  },
  data() {
    return {
      title: "community page"
    };
  },
};

</script>