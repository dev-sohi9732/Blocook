<template>
  <div class="mainview">
    <AddrecipeArea />
  </div>
</template>
<script>
import AddrecipeArea from "@/components/recipe/AddrecipeArea";
export default {
  components: {
    AddrecipeArea,
  },
  data() {
    return {
      title: "addrecipe page"
    };
  }
};
</script>