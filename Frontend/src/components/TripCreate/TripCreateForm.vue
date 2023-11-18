<script setup>

import TripCreateTimeLine from "@/components/TripCreate/TripCreateTimeLine.vue";
import {useRoute, useRouter} from "vue-router";
import {ref} from "vue";
import {useTripStore} from "@/stores/trip";


const {addTrip, newTripId} = useTripStore();

const route = useRoute();
const router = useRouter();


const trip = ref({
  tripName: "",
  startDate: "",
  endDate: "",
});

const goToTripStation = async () => {
  if (trip.value.tripName === "" || trip.value.startDate==="" || trip.value.endDate==="") {
    alert("여행 정보를 입력해주세요");
    return;
  }
  console.log("이름 : " + trip.value.tripName);
  console.log("출발 : " + trip.value.startDate);
  console.log("종료 : " + trip.value.endDate);
  await addTrip(trip.value);
  if (newTripId !== 0) {
    await router.push("/trip/pickstation");
  }
  else{
    alert("여행 등록 실패. 잠시 후 다시 시도 해주세요");
  }

};


</script>

<template>
  <v-container>

    <v-col :cols="8" offset="2">
      <TripCreateTimeLine/>
      <v-text-field color="red-accent-3" v-model="trip.tripName" class="mb-5" label="여행 이름"
                    variant="outlined"></v-text-field>
      <v-text-field color="red-accent-3" v-model="trip.startDate" class="mb-5" label="출발일" variant="outlined"
                    type="date"></v-text-field>
      <v-text-field color="red-accent-3 " v-model="trip.endDate" class="mb-5" label="종료일" variant="outlined"
                    type="date"></v-text-field>
      <v-row>
      </v-row>
    </v-col>

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
  </v-container>
</template>

<style scoped>

</style>
