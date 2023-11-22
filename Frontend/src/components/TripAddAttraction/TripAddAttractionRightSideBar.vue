<script setup>


import {ref} from "vue";
import TripAddAttractionNoData from "@/components/TripAddAttraction/TripAddAttractionNoData.vue";
import TripAddAttractionLoadingData from "@/components/TripAddAttraction/TripAddAttractionLoadingData.vue";

const props = defineProps({attractionList: Object, attLoading: Boolean});
const emit = defineEmits(["addLeftSideBar","changeCenter"]);
const addItemToLeftSideBar = (item) => {
  emit("addLeftSideBar", item);
}
const findOnMap = (item,index)=>{

  var pos = {...item};
 pos.index = index;
  emit("changeCenter",pos);
}

</script>
<template>

  <v-col :cols="3">
    <TripAddAttractionLoadingData v-if="attLoading"></TripAddAttractionLoadingData>
    <v-table height="500" v-else-if="!attLoading && attractionList.length>0">
      <thead>
      <tr>
        <th class="text-left Jalnan">
          관광지
        </th>
        <th class = "Jalnan"></th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(item,index) in attractionList" :key="item.name">

        <td>{{ item.title }}</td>

        <td>
          <div class = "d-flex">
          <v-btn
              variant="plain"
              color="red-accent-3"
              size="auto"
              class="rounded-circle pe-2"

              @click="findOnMap(item,index)">
            <v-icon>mdi-magnify</v-icon>
            <v-tooltip
                activator="parent"
                location="end"

            >지도에서 보기
            </v-tooltip>
          </v-btn>
          <v-btn
              variant="plain"
              color="red-accent-3"
              size="auto"
              class="rounded-circle"

              @click="addItemToLeftSideBar(item)">
            <v-icon>mdi-plus-circle-outline</v-icon>
            <v-tooltip
                activator="parent"
                location="end"

            >추가
            </v-tooltip>
          </v-btn>
          </div>
        </td>
      </tr>
      </tbody>
    </v-table>
    <TripAddAttractionNoData v-else></TripAddAttractionNoData>
  </v-col>

</template>
<style scoped></style>