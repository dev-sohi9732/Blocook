<template>
    <div class="logo_img">
        <center>
            <img src = @/assets/img/logo.png>
        </center>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="logo_text text-center">
                        <h1 class="text-black">{{ title }}</h1>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
  name: "LogoArea",
  props: ["title"]
};
</script>