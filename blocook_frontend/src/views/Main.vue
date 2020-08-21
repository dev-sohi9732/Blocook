<template>
    <div>
        <LogoArea></LogoArea>
        <MainArea></MainArea>
    </div>
</template>
<script>
import MainArea from "@/components/main/MainArea";
import LogoArea from "@/components/main/LogoArea";

export default {
  name: "components",
  components: {
    MainArea,
    LogoArea
  }
};
</script>
