<script setup>

import TripAddStationSearchTable from "@/components/TripAddStation/TripAddStationSearchTable.vue";
import {ref} from "vue";
import {useTripStore} from "@/stores/trip";
import TripAddStationNoData from "@/components/TripAddStation/TripAddStationNoData.vue";
import TripAddStationLoadingData from "@/components/TripAddStation/TripAddStationLoadingData.vue";
const emit = defineEmits(["add-train"]);
const tripStore = useTripStore();
const addTrainToMyList = (val)=>{
  emit("add-train",val);
}
const train = ref(
    {
      startStation: "",
      endStation: "",
      date: ""
    });
const trainList = ref([]);
const getTrainList = async () => {
  trainList.value = null;
  // console.log("시작 : " + train.value.startStation);
  // console.log("끝 : " + train.value.endStation);
  // console.log("날짜 : " + train.value.date);

  loading.value = true;
  tripStore.getTrains(train.value)
      .then((response) => {
        // 성공적으로 데이터를 받아왔을 때 처리할 로직
        // console.log("Train list successfully fetched:", response);
        trainList.value = response;
      })
      .catch((error) => {
        // 데이터 요청에 실패했을 때 처리할 로직
        // console.error("Failed to fetch train list:", error);
      })
      .finally(() => {
        loading.value = false;
      });
}
const loading = ref(false);

</script>

<template>
  <div>
    <v-row class="pt-2">
      <!--      <v-col :cols="11">-->
      <v-col>
        <v-text-field color="red-accent-3" v-model="train.startStation" label="출발역" variant="outlined"></v-text-field>
      </v-col>
      <v-col>
        <v-text-field color="red-accent-3" v-model="train.endStation" label="도착역" variant="outlined"></v-text-field>
      </v-col>
      <v-col>
        <v-text-field color="red-accent-3" v-model="train.date" label="날짜" variant="outlined" type="date">
        </v-text-field>
      </v-col>
      <!--      </v-col>-->
      <v-col :cols="2" class="d-flex align-center pb-8">
        <v-btn variant="outlined" color="red-accent-3" @click="getTrainList" :loading="loading">검색</v-btn>
      </v-col>
    </v-row>
    <TripAddStationLoadingData v-if="loading" />
    <TripAddStationSearchTable
        v-else-if="!loading && trainList && trainList.length > 0"
        :train-list = "trainList"
        @add-train-to-my-list="addTrainToMyList"
    />
    <TripAddStationNoData v-else />
  </div>
</template>

<style scoped></style>
