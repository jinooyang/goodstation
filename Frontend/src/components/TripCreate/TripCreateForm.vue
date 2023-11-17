<script setup>

import TripCreateTimeLine from "@/components/TripCreate/TripCreateTimeLine.vue";
</script>

<template>
  <v-container>

    <v-col :cols="8" offset="2">
      <TripCreateTimeLine/>
      <v-text-field color="red-accent-3"  class = "mb-5" label="여행 이름" variant="outlined"></v-text-field>
      <v-text-field color="red-accent-3"  class = "mb-5" label="출발일" variant="outlined" type="date"></v-text-field>
      <v-text-field color="red-accent-3 " class = "mb-5"  label="종료일" variant="outlined" type="date"></v-text-field>
      <v-row>
<!--        <v-col> <v-text-field color="red-accent-3"  class = "mb-5" label="출발역" variant="outlined"></v-text-field></v-col>-->
<!--        <v-col> <v-text-field color="red-accent-3"  class = "mb-5" label="도착역" variant="outlined"></v-text-field></v-col>-->

      </v-row>
    </v-col>
    <!--    {-->
    <!--    "tripName":"여행6",-->
    <!--    "startDate":"2023-08-10",-->
    <!--    "endDate":"2023-08-17",-->
    <!--    "startStationId":"NAT010000",-->
    <!--    "endStationId" : "NAT010000"-->
    <!--    }-->
  </v-container>
</template>

<style scoped>

</style>
