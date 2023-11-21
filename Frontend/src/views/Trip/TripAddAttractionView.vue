<script setup>
import TripAddAttractionLeftSideBar from "@/components/TripAddAttraction/TripAddAttractionLeftSideBar.vue";
import {onMounted, ref} from "vue";

import KakaoMap from "@/components/TripAddAttraction/KakaoMap.vue";
import TripAddAttractionCenter from "@/components/TripAddAttraction/TripAddAttractionCenter.vue";
import TripAddAttractionRightSideBar from "@/components/TripAddAttraction/TripAddAttractionRightSideBar.vue";
import axios from "axios";

import {useTripStore} from "@/stores/trip";

const tripStore = useTripStore();
const attLoading = ref(false);
const changeLoading = (val) => {
  attLoading.value = val;
};
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
const attractionList = ref([]);

const changeAttractionList = (val) => {
  attractionList.value = val;
};

const stationList = ref([]);

const leftSideBarList = ref([]);
const removeLeftSideBarItem = (index) => {
  leftSideBarList.value.splice(index, 1);
}

const addLeftSideBar = (val) => {

  const temp = {...val};
  temp.text = val.title;
  leftSideBarList.value.push(temp);
  console.log(leftSideBarList);

};

const getTrainSelect = async () => {
  await tripStore.getTrainStationList(tripStore.newTripId).then(
      (response) => {
        const result = response.data.data.list;
        for (let i = 0; i < result.length; i++) {
          result[i].name = result[i].stationName;
          result[i].value = result[i].stationId;
          result[i].list = ref([]);
          stationList.value.push(result[i]);
        }
      }
  ).catch(
      (error) => {
        console.log(error);
      }
  );
}

const addAttractions = async () => {
  await tripStore.addAttraction({
    tripId: tripStore.newTripId,
    list: leftSideBarList.value,
  }).then((response) => {
    console.log(response.data.message);
  }).catch((error) => {
    console.log("failed to add attractions to database");
  })
      .finally();
}


onMounted(() => {
  console.log("tripId : " + tripStore.newTripId);
  getTrainSelect();

});

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
      <TripAddAttractionLeftSideBar :station-list="stationList" :listitems="leftSideBarList"
                                    @removeLeftSideBarItem="removeLeftSideBarItem"/>
      <TripAddAttractionCenter :station-list="stationList" :att-loading="attLoading" :attraction-list="attractionList"
                               @changeAttractionList="changeAttractionList" @changeLoading="changeLoading"
                               @addLeftSideBar="addLeftSideBar"/>
      <TripAddAttractionRightSideBar :attraction-list="attractionList" :att-loading="attLoading"
                                     @addLeftSideBar="addLeftSideBar"/>
    </v-row>
    <v-row class="mb-5 mt-7">
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
              @click="addAttractions"
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
