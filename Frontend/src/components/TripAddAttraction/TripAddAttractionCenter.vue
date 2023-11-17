<script setup>
import KakaoMap from "@/components/TripAddAttraction/KakaoMap.vue";
import {ref} from "vue";


const chargingStations = ref([]);
const selectStation = ref({});
const selectedStation = ref(null);
const stationList = ref([

  {name: "서울역", value: "NAT0100001"},
  {name: "천안아산역", value: "NAT0100002"},
  {name: "서울역3", value: "NAT0100003"},
  {name: "서울역4", value: "NAT0100004"},
  {name: "서울역5", value: "NAT0100005"}

]);
</script>

<template>

  <v-col :cols="6">
    <v-row>
<!--      <v-col :cols="6">-->
      <v-col>
        <v-select
            variant="outlined"
            v-model="selectedStation"
            :items="stationList"
            label="기차역"
            outlined
            color="red-accent-3"
            item-title="name"
            item-value="value"
        ></v-select>
      </v-col>
<!--      <v-col :cols="6">-->
<!--        <v-select-->
<!--            color="red-accent-3"-->
<!--            variant="outlined"-->
<!--            label="유형"-->
<!--            :items="['California', 'Colorado', 'Florida', 'Georgia', 'Texas', 'Wyoming']"-->
<!--        ></v-select>-->
<!--      </v-col>-->
    </v-row>


    <KakaoMap :stations="chargingStations" :selectStation="selectStation"/>
  </v-col>

</template>

<style scoped></style>