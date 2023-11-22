<script setup>
import KakaoMap from "@/components/TripAddAttraction/KakaoMap.vue";
import {ref, watch} from "vue";
import {useTripStore} from "@/stores/trip";

const tripStore = useTripStore();
const chargingStations = ref([]);
const selectStation = ref({});

const props = defineProps({stationList: Array, attLoading: Boolean, attractionList: Array, centerPos: Object});
const emit = defineEmits(["changeAttractionList", "changeLoading", "addLeftSideBar"]);
const selectedStation = ref(null);
const selectedContent = ref(12);
const selectedSearch = ref(null);
const contentType = ref([
  {name: "관광지", value: 12,}, {name: "문화시설", value: 14,}, {name: "축제/공연/행사", value: 15,},
  {name: "레포츠", value: 28,}, {name: "숙박", value: 32,}, {name: "쇼핑", value: 38,}, {name: "음식점", value: 39,},
]);

const myCenterPos  = ref("");
watch(()=>props.centerPos, ()=>{
  console.log(props.centerPos)
  myCenterPos.value = props.centerPos;
}, {immediate: true});


const searchAttractionInfo = async () => {
  // console.log("!!값이 변경됨!!",selectedStation.value.name, selectedStation.value.value);
  emit("changeLoading", true);
  console.log(selectedStation.value);
  console.log(selectedContent.value);
  console.log(selectedSearch.value);
  await tripStore.getAttNearSt(
      {
        station: selectedStation.value,
        content: selectedContent.value,
        search: selectedSearch.value,

      }
  ).then((response) => {
    console.log("가져온 데이터 수 : " + response.data.data.list.length);
    emit("changeAttractionList", response.data.data.list);
  }).catch((error) => {
    console.log(error);
  }).finally(() => {
    emit("changeLoading", false);
  });
};
watch(selectedStation, searchAttractionInfo);
watch(selectedContent, searchAttractionInfo);
const addToLeftSideBar = (index) => {
  emit("addLeftSideBar", props.attractionList[index]);
}

</script>

<template>

  <v-col :cols="6">
    <v-row>
      <!--      <v-col :cols="6">-->
      <v-col :cols="4">
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
      <v-col :cols="4">
        <v-select
            variant="outlined"
            v-model="selectedContent"
            :items="contentType"
            label="유형"
            outlined
            color="red-accent-3"
            item-title="name"
            item-value="value"
            :disabled="selectedStation === null"
        ></v-select>
      </v-col>
      <v-col :cols="4">
        <v-text-field
            color="red-accent-3"
            label="검색"
            variant="outlined"
            v-model="selectedSearch"
            prepend-inner-icon="mdi-magnify"
            :disabled="selectedStation === null"
            @change="searchAttractionInfo"
        ></v-text-field>
      </v-col>


    </v-row>


    <KakaoMap :attractionList="attractionList" :selectStation="selectStation" :centerPos="myCenterPos"
              @addToLeftSideBar="addToLeftSideBar"
    />
  </v-col>

</template>

<style scoped></style>