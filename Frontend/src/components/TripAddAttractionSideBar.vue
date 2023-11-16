<script setup>
import {ref} from "vue";
// import TripAddAttractionSideBarTimeLineItem from "@/components/TripAddAttractionSideBarTimeLineItem.vue";
const trainStations = ref([
  {
    station: "서울역",
    count: 0
  }, {
    station: "용산역", count: 0
  }, {
    station: "대전역", count: 0
  }, {
    station: "구미역", count: 0
  }, {
    station: "여수EXPO역", count: 0
  }, {
    station: "부산역", count: 0
  }

]);
</script>

<template>
  <v-container>
    <v-row justify="space-around">
      <v-card width="400">
        <v-toolbar color="rgba(0, 0, 0, 0)">
          <v-toolbar-title class="text-h6">
            <p class="Jalnan ps-5">여행 장바구니</p>
          </v-toolbar-title>
        </v-toolbar>

        <!-- <v-card-text style="width: 100%"> -->
        <v-card-text>
        안녕하세요
        </v-card-text>
      </v-card>
    </v-row>

  </v-container>
</template>

<style scoped>
.red {
  background-color: #f7323f;
  color: white;
}
</style>

<style>
.v-timeline-item__body {
  width: 100% !important;
  padding-right: 1em !important;
}

.v-toolbar__content {
  background-color: #f7323f;
  color: white;
}
</style>
