<script setup>
import TripAddAttractionSideBar from "@/components/TripAddAttractionSideBar.vue";
</script>

<template>
  <div class="red Jalnan ps-5">
    <v-container>
      <h1>관광지 선택</h1>
      방문하고 싶은 관광지를 선택하세요
    </v-container>
  </div>
  <v-container>
    <v-row>
    <v-col :cols="4"><TripAddAttractionSideBar/></v-col>
    </v-row>





  </v-container>
</template>

<style scoped></style>
