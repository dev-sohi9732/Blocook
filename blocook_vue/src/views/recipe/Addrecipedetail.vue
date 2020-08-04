<template>
  <div class="mainview">
    <AddrecipedetailArea />
  </div>
</template>

<script>
import AddrecipedetailArea from "@/components/recipe/AddrecipedetailArea";
export default {
  components: {
    AddrecipedetailArea,
  },
  data() {
    return {
			title: "addrecipedetail page",
    	};
	},
};
</script>