<template>

  <span style="font-size:0.8rem;"><i class="fa fa-heart" style="color:red;font-weight:bold;"></i>&nbsp; {{likeCnt}}</span>
</template>

<script>
import http from "@/util/http-common.js";
export default {
    name: 'MainAreaItem',
    data() {
        return {
            likeCnt:0
        }
    },
    props: {
        recipe: {
            type: Object,
        },
    },
    created() {
      http.get(`/recipes/${this.recipe.recipeId}/bookmark-count`)
		.then((res) => {
            this.likeCnt = res.data
		})
		.catch((error) => {
			console.dir(error);
		});
    },
    updated() {
      http.get(`/recipes/${this.recipe.recipeId}/bookmark-count`)
			.then((res) => {
        this.likeCnt = res.data

			})
			.catch((error) => {
				console.dir(error);
			});
    },
}
</script>

<style>

</style>