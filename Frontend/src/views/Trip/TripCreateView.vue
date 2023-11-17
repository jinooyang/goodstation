<script setup>
import TripCreateForm from "@/components/TripCreate/TripCreateForm.vue";
import { useRoute, useRouter } from "vue-router";
const route = useRoute();
const router = useRouter();
const goToTripStation = () => {
  router.push("/trip/pickstation");
};
</script>

<template>
  <div class="red Jalnan ps-5">
    <v-container
    ><h1>여행 생성</h1>새로운 여행을 간편하게 생성하세요
    </v-container>
  </div>
  <TripCreateForm/>


  <v-row>
    <v-col :cols="4" offset="4">
      <div class="text-center Jalnan mb-9 pb-9 pt-9">
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
          여행 생성하기 =>
        </v-btn>
      </div>
    </v-col>
  </v-row>
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
