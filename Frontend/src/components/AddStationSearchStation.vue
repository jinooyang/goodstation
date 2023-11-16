<script setup>

import AddStationSearchTable from "@/components/AddStationSearchTable.vue";
</script>

<template>
  <div>
    <v-row class="pt-2">
      <!--      <v-col :cols="11">-->
      <v-col>
        <v-text-field color="red-accent-3" label="출발역" variant="outlined"></v-text-field>
      </v-col>
      <v-col>
        <v-text-field color="red-accent-3" label="도착역" variant="outlined"></v-text-field>
      </v-col>
      <v-col>
        <v-text-field color="red-accent-3" label="날짜" variant="outlined" type="date">
        </v-text-field>
      </v-col>
      <!--      </v-col>-->
      <v-col :cols="2" class="d-flex align-center pb-8">
        <v-btn variant="outlined" color = "red-accent-3">검색</v-btn>
      </v-col>
    </v-row>
    <AddStationSearchTable/>
  </div>
</template>

<style scoped></style>
