<template>
    <div>
        <NavBackgroundCommunity></NavBackgroundCommunity>
        <DetailPostArea></DetailPostArea>
    </div>
</template>
<script>
import DetailPostArea from "@/components/community/DetailPostArea";
import NavBackgroundCommunity from "@/components/main/NavBackgroundCommunity";

export default {
  name: "detailpost",
  components: {
    DetailPostArea,
    NavBackgroundCommunity
  }
};
</script>
