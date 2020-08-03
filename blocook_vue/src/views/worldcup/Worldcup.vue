<template>
    <div>
      <div class="worldcup-top">
          <br>
          <center>
            <br>
            <br>
            <br>
            <h1 style="color:white; font-weight:bold;">메뉴 월드컵</h1>
          </center>
      </div>
      <div class="worldcup-middle">
          <center>
            <h3 style="font-weight:bold;">CATEGORY</h3>
            <div class="container">
              <div class="row">
                  <div class=col-4>
                      <label><input type="radio">전체</label>
                  </div>
                  <div class=col-4>
                      <label><input type="radio">한식</label>
                  </div>
                  <div class=col-4>
                      <label><input type="radio">중식</label>
                  </div>
                  <div class=col-4>
                      <label><input type="radio">일식</label>
                  </div>
                  <div class=col-4>
                      <label><input type="radio">양식</label>
                  </div>
                  <div class=col-4>
                      <label><input type="radio">이탈리아</label>
                  </div>
                  <div class=col-4>
                      <label><input type="radio">동남아</label>
                  </div>
                  <div class=col-4>
                      <label><input type="radio">퓨전</label>
                  </div>
              </div>
            </div>
            <h3 style="font-weight:bold; background-size:45%;">ROUND</h3>
            <div class="container">
              <div class="row">
                  <div class=col-4>
                      <label><input type="radio">8강</label>
                  </div>
                  <div class=col-4>
                      <label><input type="radio">16강</label>
                  </div>
                  <div class=col-4>
                      <label><input type="radio">32강</label>
                  </div>
              </div>
            </div>
            <button class="worldcup-btn">START</button>
          </center>
          <div class="worldcup-footer"></div>
      </div>
    </div>
</template>

<script>
export default {
    data() {
    return {
      title: "worldcup page"
    };
  }
};
</script>
