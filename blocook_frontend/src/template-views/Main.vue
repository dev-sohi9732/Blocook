<template>
    <div>
        <LogoArea></LogoArea>
        <MainArea></MainArea>
    </div>
</template>
<script>
import MainArea from "./main/MainArea";
import LogoArea from "./main/LogoArea";

export default {
  name: "components",
  components: {
    MainArea,
    LogoArea
  }
};
</script>
