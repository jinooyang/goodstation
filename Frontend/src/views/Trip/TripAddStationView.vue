<script setup>
import TripAddStationSideBar from "../../components/TripAddStation/TripAddStationSideBar.vue";
import {ref, onMounted, watch} from "vue";
import TripAddStationSearchStation from "../../components/TripAddStation/TripAddStationSearchStation.vue";
import {useTripStore} from "@/stores/trip";

import {useRoute, useRouter} from "vue-router";
import {storeToRefs} from "pinia";

const route = useRoute();
const router = useRouter();

const tripStore = useTripStore();


const goToTripAttraction = () => {
  router.push("/trip/attraction").then(() => {
    window.scrollTo(0, 0);
  });
};

const insertStationsToTrip = async () => {
  console.log(trainStations.value);
  console.log("tripId : " + tripStore.newTripId);
  await tripStore.insertStation({
    tripId : tripStore.newTripId,
    stationList : trainStations.value
  })
      .then((response) => {
        console.log("success!!!!!");
        goToTripAttraction();
      })
      .catch((error) => {
        console.log("failed!!");
      });
}


const trainStations = ref([           ]);

const removeTrainStations = (index) => {
  trainStations.value.splice(index, 1);

}


const addTrain = (val) => {

  const modifiedVal = {...val};
  trainStations.value.tripId = tripStore.newTripId;
  modifiedVal.arrplandtime = convertToFormattedDateTime(modifiedVal.arrplandtime);
  modifiedVal.depplandtime = convertToFormattedDateTime(modifiedVal.depplandtime);

  trainStations.value.push(modifiedVal);
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


  return `${year}/${month}/${day} ${hours}:${minutes}`;
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
        <TripAddStationSideBar :train-stations="trainStations" @remove-train-stations="removeTrainStations"/>
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
              @click="insertStationsToTrip"
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
