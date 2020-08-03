<template>
  <div class="mainview">
    <AddrecipedetailArea />
		<div class="addbutton">
			<button v-on:click="add" class="button btn" style="border-radius: 50%; width:40px;"><i class="fa fa-plus"></i></button>
			<button class="button btn" style="float:right; border-radius: 5px; background-color: rgb(241, 196, 15); ">
				<i class="fa fa-check"></i>&nbsp;레시피 등록 완료
			</button>
		</div>
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
	methods: {
	}
};
</script>

<style scoped>
.button {
	width : 180px;
	background-color: rgb(45, 180, 0); 
	margin-top: 10px;
	color:white;
	font-weight: bold;
}
.addbutton {
	margin-bottom: 30px;
	margin-left: 30px;;
	margin-right: 30px;
}
</style>