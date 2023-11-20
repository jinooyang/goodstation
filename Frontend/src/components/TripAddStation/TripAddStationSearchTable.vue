<script setup>

import TripAddStationNoData from "@/components/TripAddStation/TripAddStationNoData.vue";

const props = defineProps({trainList: Object});
const emit = defineEmits(["add-train-to-my-list"]);
const parseTimeString = (inputString) => {
  // Ensure the inputString has the correct length
  if (inputString.length !== 14) {
    throw new Error('Invalid input string length');
  }

  // Extract hours and minutes using slice
  const hours = inputString.slice(8, 10);
  const minutes = inputString.slice(10, 12);

  // Create a formatted time string
  return `${hours}:${minutes}`;
};
const addThisTrain = (val) => {
  console.log(val);

  emit("add-train-to-my-list",val);
}
</script>

<template>

  <div class="tableborder">
    <v-table fixed-header height="600px">
      <thead>
      <tr>
        <th class="text-left">출발역</th>
        <th class="text-left">도착역</th>
        <th class="text-left">열차 종류</th>
        <th class="text-left">출발시간</th>
        <th class="text-left">도착시간</th>
        <th class="text-left">추가하기</th>

      </tr>
      </thead>
      <tbody>
      <tr v-for="(train,index) in trainList" :key="index">
        <td>{{ train.depplacename }}</td>
        <td>{{ train.arrplacename }}</td>
        <td>{{ train.traingradename }}</td>
        <td>{{ parseTimeString(train.depplandtime) }}</td>
        <td>{{ parseTimeString(train.arrplandtime) }}</td>
        <td>
          <v-btn variant="outlined" color="red-accent-3" @click = "addThisTrain(train)">추가</v-btn>
        </td>

      </tr>


      </tbody>

    </v-table>

  </div>
</template>

<style scoped>
th {
  font-family: Jalnan;
}


</style>
