<template>
  <div class="mainview">
    <MyrecipeArea />
  </div>
</template>
<script>
import MyrecipeArea from "@/components/recipe/MyrecipeArea";
export default {
  components: {
    MyrecipeArea,
  },
  data() {
    return {
      title: "myrecipe page"
    };
  }
};

</script>