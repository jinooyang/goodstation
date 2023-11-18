<script setup>
import TripCreateForm from "@/components/TripCreate/TripCreateForm.vue";
import { useRoute, useRouter } from "vue-router";
import {ref, watchEffect} from "vue";



</script>

<template>
  <div class="red Jalnan ps-5">
    <v-container
    ><h1>여행 생성</h1>새로운 여행을 간편하게 생성하세요
    </v-container>
  </div>
  <TripCreateForm @update-trip="handleUpdateTrip" />


<!--  <v-row class="ms-3">-->
<!--  <a href="https://www.roblox.com/games/7594841232/seoulbike" target="_blank"><img src="../../assets/bike.png"></a>-->
<!--  </v-row>-->
</template>

<style scoped>
.red {
  background-color: #f7323f;
  color: white;
}


</style>
