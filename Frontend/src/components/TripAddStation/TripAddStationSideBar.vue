<script setup>
import { ref } from "vue";
const trainStations = ref([
  {
    startStation: "서울역1",
    endStation: "부산역1",
    startDate: "2023-08-25 14:00",
    endDate: "2023-08-25 17:00",
    color: "bg-deep-purple-lighten-1",
  }
]);
</script>

<template>
  <v-container>
    <v-row justify="space-around">
      <v-card width="400">
        <v-toolbar color="rgba(0, 0, 0, 0)">
          <v-toolbar-title class="text-h6">
            <p class="Jalnan ps-5">여행 장바구니</p>
          </v-toolbar-title>
        </v-toolbar>

        <!-- <v-card-text style="width: 100%"> -->
        <v-card-text>
          <v-table height="600px" >
          <v-timeline density="compact" align="start">
            <v-timeline-item
              v-for="train in trainStations"
              :key="train.startStation"
              dot-color="red-accent-3"
              size="x-small"
              class="timelineparent"
            >
              <div class="d-flex" justify="space-between">
                <div style="flex: 1">
                  <div class="font-weight-normal">
                    <strong class="Jalnan"
                      >{{ train.startStation }} -> {{ train.endStation }}</strong
                    >
                  </div>

                  <div>출발 : {{ train.startDate }}<br />도착 : {{ train.endDate }}</div>
                </div>
                <div class = "d-flex align-center">
                  <v-btn variant="outlined" color = "red-accent-3">삭제</v-btn>
                </div>
              </div>
            </v-timeline-item>
          </v-timeline>
        </v-table>
        </v-card-text>
      </v-card>
    </v-row>

  </v-container>
</template>

<style scoped>
.red {
  background-color: #f7323f;
  color: white;
}
</style>

<style>
.v-timeline-item__body {
  width: 100% !important;
  padding-right: 1em !important;
}

.v-toolbar__content {
  background-color: #f7323f;
  color: white;
}
</style>
