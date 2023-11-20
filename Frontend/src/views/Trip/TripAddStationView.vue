<script setup>
import TripAddStationSideBar from "../../components/TripAddStation/TripAddStationSideBar.vue";
import {ref, onMounted, watch} from "vue";
import TripAddStationSearchStation from "../../components/TripAddStation/TripAddStationSearchStation.vue";

import { useRoute, useRouter } from "vue-router";
const route = useRoute();
const router = useRouter();
const goToTripAttraction = () => {
  router.push("/trip/attraction");
};
const trainStations = ref([

]);
const addTrain = (val)=>{
  val.arrplandtime = convertToFormattedDateTime(val.arrplandtime);
  val.depplandtime = convertToFormattedDateTime(val.depplandtime);

  trainStations.value.push(val);
}
function convertToFormattedDateTime(inputString) {
  // Ensure the inputString has the correct length
  if (inputString.length !== 14) {
    throw new Error('Invalid input string length');
  }

  // Extract date and time components
  const year = inputString.slice(0, 4);
  const month = inputString.slice(4, 6);
  const day = inputString.slice(6, 8);
  const hours = inputString.slice(8, 10);
  const minutes = inputString.slice(10, 12);

  // Create a Date object using extracted components
  const dateObject = new Date(`${year}-${month}-${day}T${hours}:${minutes}:00`);

  // Format the output string
  const formattedDateTime = dateObject.toLocaleString('en-US', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit',
    hour12: false, // 24시간 형식으로 설정
  });

  return formattedDateTime;
}
</script>

<template>
  <div class="red Jalnan ps-5">
    <v-container
    ><h1>기차역 선택</h1>여행하고 싶은 기차역을 선택하세요
    </v-container>
  </div>
  <v-container>
    <v-row>
      <v-col :cols="4">
        <TripAddStationSideBar :train-stations="trainStations"/>
      </v-col>
      <v-col>
        <TripAddStationSearchStation @add-train="addTrain"/>
      </v-col>
    </v-row>
    <v-row>
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
          @click="goToTripAttraction"
      >
        관광지 추가하기 =>
      </v-btn>
      </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
.red {
  background-color: #f7323f;
  color: white;
}

.blue {
  background-color: blue;
}
</style>
