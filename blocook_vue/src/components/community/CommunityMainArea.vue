<template>
	<center class="area">
		<div class="community">
			<select>
				<!-- v-model="option" -->
				<!-- v-on:change="filter" -->
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="writer">작성자</option>
			</select>
			<form class="s-form">
				<input type="text">
				<!-- v-model="search" -->
				<!-- v-on:keyup="filter" -->
				<button><i class="fa fa-search"></i></button>
			</form>
		</div>

		<div>
			<select class="sorting">
				<!-- v-model="option" -->
				<!-- v-on:change="filter" -->
				<option value="new">최신순</option>
				<option value="see">조회순</option>
				<option value="like">좋아요순</option>
			</select>

			<b-dropdown id="dropdown-right" right text="내 포스트" variant="success" class="writing" no-caret>
				<b-dropdown-item href="#" style="text-align:center">새 글 쓰기</b-dropdown-item>
				<b-dropdown-item href="/mypost" style="text-align:center">내가 쓴 글</b-dropdown-item>
			</b-dropdown>
		</div>

		<div class="posts">
			<table>
				<tbody>
				<tr>
					<td>
						<div class="title">더운 여름, 수박 화채로 시원하게 보내기! 내 레시피를 참고해!</div>
						<div class="info"><span>ssafy123</span>&emsp;<span>2020.7.15 13:45</span></div>
					</td>
					<td class="heart">
						<div><i class="fa fa-eye"></i>&nbsp;50</div>
						<div><i class="fa fa-heart" style="color:red;"></i>&nbsp;15</div>
						<div><i class="fa fa-comment-o"></i>&nbsp;4</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="title">맛있게 김치찌개 끓이는 법!</div>
						<div class="info"><span>ssafy456</span>&emsp;<span>2020.7.10 17:35</span></div>
					</td>
					<td class="heart">
						<div><i class="fa fa-eye"></i>&nbsp;50</div>
						<div><i class="fa fa-heart" style="color:red;"></i>&nbsp;15</div>
						<div><i class="fa fa-comment-o"></i>&nbsp;4</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="title">더운 여름, 수박 화채로 시원하게 보내기! 내 레시피를 참고해!</div>
						<div class="info"><span>ssafy123</span>&emsp;<span>2020.7.15 13:45</span></div>
					</td>
					<td class="heart">
						<div><i class="fa fa-eye"></i>&nbsp;50</div>
						<div><i class="fa fa-heart" style="color:red;"></i>&nbsp;15</div>
						<div><i class="fa fa-comment-o"></i>&nbsp;4</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="title">맛있게 김치찌개 끓이는 법!</div>
						<div class="info"><span>ssafy456</span>&emsp;<span>2020.7.10 17:35</span></div>
					</td>
					<td class="heart">
						<div><i class="fa fa-eye"></i>&nbsp;50</div>
						<div><i class="fa fa-heart" style="color:red;"></i>&nbsp;15</div>
						<div><i class="fa fa-comment-o"></i>&nbsp;4</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="title">더운 여름, 수박 화채로 시원하게 보내기! 내 레시피를 참고해!</div>
						<div class="info"><span>ssafy123</span>&emsp;<span>2020.7.15 13:45</span></div>
					</td>
					<td class="heart">
						<div><i class="fa fa-eye"></i>&nbsp;50</div>
						<div><i class="fa fa-heart" style="color:red;"></i>&nbsp;15</div>
						<div><i class="fa fa-comment-o"></i>&nbsp;4</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="title">맛있게 김치찌개 끓이는 법!</div>
						<div class="info"><span>ssafy456</span>&emsp;<span>2020.7.10 17:35</span></div>
					</td>
					<td class="heart">
						<div><i class="fa fa-eye"></i>&nbsp;50</div>
						<div><i class="fa fa-heart" style="color:red;"></i>&nbsp;15</div>
						<div><i class="fa fa-comment-o"></i>&nbsp;4</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="title">더운 여름, 수박 화채로 시원하게 보내기! 내 레시피를 참고해!</div>
						<div class="info"><span>ssafy123</span>&emsp;<span>2020.7.15 13:45</span></div>
					</td>
					<td class="heart">
						<div><i class="fa fa-eye"></i>&nbsp;50</div>
						<div><i class="fa fa-heart" style="color:red;"></i>&nbsp;15</div>
						<div><i class="fa fa-comment-o"></i>&nbsp;4</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="title">맛있게 김치찌개 끓이는 법!</div>
						<div class="info"><span>ssafy456</span>&emsp;<span>2020.7.10 17:35</span></div>
					</td>
					<td class="heart">
						<div><i class="fa fa-eye"></i>&nbsp;50</div>
						<div><i class="fa fa-heart" style="color:red;"></i>&nbsp;15</div>
						<div><i class="fa fa-comment-o"></i>&nbsp;4</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="title">더운 여름, 수박 화채로 시원하게 보내기! 내 레시피를 참고해!</div>
						<div class="info"><span>ssafy123</span>&emsp;<span>2020.7.15 13:45</span></div>
					</td>
					<td class="heart">
						<div><i class="fa fa-eye"></i>&nbsp;50</div>
						<div><i class="fa fa-heart" style="color:red;"></i>&nbsp;15</div>
						<div><i class="fa fa-comment-o"></i>&nbsp;4</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="title">맛있게 김치찌개 끓이는 법!</div>
						<div class="info"><span>ssafy456</span>&emsp;<span>2020.7.10 17:35</span></div>
					</td>
					<td class="heart">
						<div><i class="fa fa-eye"></i>&nbsp;50</div>
						<div><i class="fa fa-heart" style="color:red;"></i>&nbsp;15</div>
						<div><i class="fa fa-comment-o"></i>&nbsp;4</div>
					</td>
				</tr>
			</tbody>
			</table>
		</div>
	</center>
</template>

<script>
export default {
}
</script>

<style scoped>
.posts {
	margin-top: 10px;
}
table {
  width: 100%;
}
tr:hover {
	background-color: #f5f5f5;
}
td {
  padding: 14px;
	border-top: 1px solid #ddd;
	border-bottom: 1px solid #ddd;
  text-align: left;
}
.title {
	font-weight: bold;
}
.info {
	font-size: 14px;
	margin-top: 5px;
}
.heart {
	width: 21%;
	padding-left: 15px;
}
.sorting {
	margin: 17px 8px 7px;
  float: left;
	display:inline;
	height: 39px;
	width: 100px;
	border-radius: 5px;
	border: 4px solid rgb(40, 167, 69);
	background-color:white;
	font-weight:bolder;
}
.writing {
	margin: 17px 8px 7px;
  float: right;
	background-color :rgb(132, 192, 22);
	border-radius: 5px;
	border-top:1px solid;
	border-bottom: 1px solid;
	border-left: none;
	border-right: none;
	color:white;
}
.area {
	margin-top: 10px;
}
</style>