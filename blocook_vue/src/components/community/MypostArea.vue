<template>
	<center>
		<div class="left">
			작성한 글 : 15개 / 댓글 : 30개
		</div>
		<div>
			<button class="writing"><i class="fa fa-pencil">&nbsp;글쓰기</i></button>
		</div>

		<div>
			<table class="whole">
			<tbody>
				<b-tabs>
					<b-tab title="작성글" active>
						<table>
						<tbody>
							<tr>
								<td>
									<div class="title">더운 여름, 수박 화채로 시원하게 보내기! 내 레시피를 참고해!</div>
									<div class="info"><span>2020.7.15 13:45</span></div>
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
									<div class="info"><span>2020.7.15 13:45</span></div>
								</td>
								<td class="heart">
									<div><i class="fa fa-eye"></i>&nbsp;50</div>
									<div><i class="fa fa-heart" style="color:red;"></i>&nbsp;15</div>
									<div><i class="fa fa-comment-o"></i>&nbsp;4</div>
								</td>
							</tr>
						</tbody>
						</table>
					</b-tab>
					<b-tab title="작성댓글">
						<table>
						<tbody>
							<tr>
								<td>
									<div class="title">레시피가 좋네요! 저도 만들어 볼게요~</div>
									<div class="info"><span>2020.7.15 13:45</span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="title">레시피가 아주 좋네요!</div>
									<div class="info"><span>2020.7.15 13:45</span></div>
								</td>
							</tr>
						</tbody>
						</table>
					</b-tab>
				</b-tabs>
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
.whole {
	width: 98%;
}
table {
	width: 100%;
}
tr {
  width: 370px;
}
tr:hover {
	background-color: #f5f5f5;
}
td {
  padding: 14px;
  text-align: left;
	border-bottom: 1px solid #ddd;
}
.title {
	font-weight: bold;
}
.info {
	font-size: 14px;
	margin-top: 5px;
}
.heart {
	width: 21%
}
.left {
	margin: 0px 15px 7px;
  float: left;
}
.writing {
	margin: 26px 10px 0px;
  float: right;
	background-color :rgb(132, 192, 22);
	border-radius: 5px;
	border-top:1px solid;
	border-bottom: 1px solid;
	border-left: none;
	border-right: none;
	color:white;
}
</style>