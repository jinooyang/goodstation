<script setup>

import {ref} from "vue";

const trainList = ref([{
  startStation: "서울역1",
  endStation: "부산역1",
  startTime: "14:00",
  endTime: "17:00", type: "KTX"
}]);


</script>

<template>

  <div class = "tableborder">
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
        <td>
          <v-btn variant="outlined" color="red-accent-3">추가</v-btn>
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
