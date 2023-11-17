<script setup>
import TripAddAttractionSideBar from "@/components/TripAddAttraction/TripAddAttractionLeftSideBar.vue";
import {ref} from "vue";

import KakaoMap from "@/components/TripAddAttraction/KakaoMap.vue";
import TripAddAttractionMap from "@/components/TripAddAttraction/TripAddAttractionCenter.vue";
import TripAddAttractionRightSideBar from "@/components/TripAddAttraction/TripAddAttractionRightSideBar.vue";




const att = ref([{

  contentId: 125266,
  name: '국립 청태산자연휴양림',
  address: '강원도 횡성군 둔내면 청태산로 610',
  img: 'http://tong.visitkorea.or.kr/cms/resource/21/2657021_image2_1.jpg'

}])


const listitems = ref([
  {text: '남한산성도립공원 [유네스코 세계문화유산]', icon: 'mdi-minus-circle-outline'},
  {text: '남한산성도립공원 [유네스코 세계문화유산]', icon: 'mdi-minus-circle-outline'},
  {text: '남한산성도립공원 [유네스코 세계문화유산]', icon: 'mdi-minus-circle-outline'},
]);
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
      <TripAddAttractionSideBar/>
      <TripAddAttractionMap/>
      <TripAddAttractionRightSideBar/>
    </v-row>
    <v-row class = "mb-5 mt-7">
      <v-col :cols="4" offset="4">
        <div class="text-center Jalnan">
          <v-btn
              v-btn
              v-btn--elevated
              v-theme--light
              bg-primary
              v-btn--density-default
              v-btn--size-default
              v-btn--variant-elevated
              size="large"
              color="#f7323f"
              @click="goToTripStation"
              align="center"
          >
            여행 떠나기 =>
          </v-btn>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>

svg {
  fill: #f7323f
}

li {
  list-style: none;
}
</style>
