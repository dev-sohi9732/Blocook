<template>
	<div style="margin-bottom: 20px;">
		<center class="menu">
			<h5>STEP 1</h5>
			<img src="@/assets/img/addimage.jpg">
			<div>
				<button class="button btn" style="width:180px;">과정 사진 등록&nbsp;<i class="fa fa-camera"></i></button>
			</div>
		</center>

		<div class="container inputbox">
			<table>
			<tbody>
				<tr>
					<th>
						<h6>1단계 설명</h6>
					</th>
				</tr>
				<tr>
					<td>
						<b-form-textarea
							id="textarea"
							placeholder="레시피 상세 설명을 입력해주세요."
						></b-form-textarea>
					</td>
				</tr>
			</tbody>
			</table>
		</div>
	</div>
</template>

<script>
  export default {
    data() {
      return {
      }
		},
		methods: {
		}
  }
</script>

<style scoped>
center {
	margin-top: 10px;
	font-weight: bold;
}
img {
	margin-top: 10px;
	max-width: 80%;
	height: auto;
}
h1, h4, h5, h6 {
	font-weight: bold;
}
.container {
	width: 86%;
	margin-top: 20px;
}
th {
	width: 15%;
	text-align: left;
	vertical-align: middle;
}
.inputbox td {
	vertical-align: top;
}
td {
	vertical-align: top;
}
a {
	float: right;
}
.button {
	width : 268px;
	background-color: rgb(45, 180, 0); 
	margin-top: 10px;
	color:white;
	font-weight: bold;
}
table {
	width: 100%;
}
select {
	margin-top: auto;
}
.shortbox select, input {
	margin-bottom: 8px;
}
#textarea {
	height: 150px;
}
</style>