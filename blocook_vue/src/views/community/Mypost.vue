<template>
  <div class="mainview">
    <MypostArea />
  </div>
</template>
<script>
import MypostArea from "@/components/community/MypostArea";
export default {
  components: {
    MypostArea,
  },
  data() {
    return {
      title: "mypost page"
    };
  }
};

</script>