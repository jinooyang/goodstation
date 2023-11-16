<script setup>
import AddStationSideBar from "../components/AddStationSideBar.vue";
import {ref, onMounted, watch} from "vue";
import AddStationSearchStation from "../components/AddStationSearchStation.vue";
</script>

<template>
  <div class="red Jalnan ps-5">
    <v-container
    ><h1>기차역 선택</h1>여행하고 싶은 기차역을 선택하세요
    </v-container
    >
  </div>
  <v-container>
    <v-row>
      <v-col :cols="4">
        <AddStationSideBar/>
      </v-col>
      <v-col>
        <AddStationSearchStation/>
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
          @click="goToTripStation"
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
