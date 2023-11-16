<script setup>

import {ref} from "vue";

const trainList = ref([{
  startStation: "서울역1",
  endStation: "부산역1",
  startTime: "14:00",
  endTime: "17:00", type: "KTX"
}, {
  startStation: "서울역2",
  endStation: "부산역2",
  startTime: "14:00",
  endTime: "17:00", type: "KTX"
}, {
  startStation: "서울역3",
  endStation: "부산역3",
  startTime: "14:00",
  endTime: "17:00", type: "KTX"
}, {
  startStation: "서울역4",
  endStation: "부산역4",
  startTime: "14:00",
  endTime: "17:00", type: "KTX"
}, {
  startStation: "서울역5",
  endStation: "부산역5",
  startTime: "14:00",
  endTime: "17:00", type: "KTX"
}, {
  startStation: "서울역6",
  endStation: "부산역6",
  startTime: "14:00",
  endTime: "17:00", type: "KTX"
}, {
  startStation: "서울역11",
  endStation: "부산역1",
  startTime: "14:00",
  endTime: "17:00", type: "KTX"
}, {
  startStation: "서울역21",
  endStation: "부산역2",
  startTime: "14:00",
  endTime: "17:00", type: "KTX"
}, {
  startStation: "서울역31",
  endStation: "부산역3",
  startTime: "14:00",
  endTime: "17:00", type: "KTX"
}, {
  startStation: "서울역41",
  endStation: "부산역4",
  startTime: "14:00",
  endTime: "17:00", type: "KTX"
}, {
  startStation: "서울역51",
  endStation: "부산역5",
  startTime: "14:00",
  endTime: "17:00", type: "KTX"
}, {
  startStation: "서울역61",
  endStation: "부산역6",
  startTime: "14:00",
  endTime: "17:00", type: "KTX"
}]);


</script>

<template>
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
    <tr v-for="train in trainList" :key="train.startStation">
      <td>{{ train.startStation }}</td>
      <td>{{ train.endStation }}</td>
      <td>{{ train.type }}</td>
      <td>{{ train.startTime }}</td>
      <td>{{ train.endTime }}</td>
      <td><v-btn variant="outlined" color = "red-accent-3">추가</v-btn>
      </td>

    </tr>
    </tbody>
  </v-table>
</template>

<style scoped>
th {
  color: #f7323f;
  font-family: Jalnan;
}


</style>
