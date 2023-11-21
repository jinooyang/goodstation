<script setup>
import KakaoMap from "@/components/TripAddAttraction/KakaoMap.vue";
import {ref, watch} from "vue";
import {useTripStore} from "@/stores/trip";
const tripStore = useTripStore();
const chargingStations = ref([]);
const selectStation = ref({});
const selectedStation = ref(null);
const props = defineProps({stationList:Object, attLoading:Boolean, attractionList:Array});
const emit = defineEmits(["changeAttractionList","changeLoading","addLeftSideBar"]);

const onSelected = async()=>{
  // console.log("!!값이 변경됨!!",selectedStation.value.name, selectedStation.value.value);
  emit("changeLoading",true);
  await tripStore.getAttNearSt(selectedStation.value).then((response)=>{
    console.log("가져온 데이터 수 : " + response.data.data.list.length);
    emit("changeAttractionList",response.data.data.list);
  }).catch((error)=>{console.log(error);}).finally(()=>{
    emit("changeLoading",false);
  });
};
watch(selectedStation, onSelected);

const addToLeftSideBar = (index)=>{
  emit("addLeftSideBar",props.attractionList[index]);
}

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
            label="기차역을 선택하세요"
            outlined
            color="red-accent-3"
            item-title="name"
            item-value="value"

        ></v-select>
      </v-col>

    </v-row>


    <KakaoMap :attractionList="attractionList" :selectStation="selectStation"
    @addToLeftSideBar="addToLeftSideBar"
    />
  </v-col>

</template>

<style scoped></style>