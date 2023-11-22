<script setup>
import {onMounted, ref} from "vue";
import {useTripStore} from "@/stores/trip";

const tripStore = useTripStore();


const items = [
  {
    color: 'red-darken-2',
    icon: 'mdi-train',
  },
  {
    color: 'red-lighten-4',
    icon: 'mdi-train',
  },
  {
    color: 'red-lighten-4',
    icon: 'mdi-train',
  },
  {
    color: 'red-lighten-4',
    icon: 'mdi-train',
  },
]

const result = ref([]);
const endStationName = ref("");
const getResult = async () => {
  console.log("getting result of " + tripStore.newTripId);
  await tripStore.getResult(tripStore.newTripId).then(
      (response) => {
        var res = response.data.data.result;
        console.log(res);
        let resSize = res.length;
        for (let i = 0; i < res.length; i++) {
          var train = {};
          train.trainType = res[i].trainType;
          train.startStation = res[i].stationName;
          if (i !== 0) {
            train.startDate = parseAndFormatDateWithTime(res[i].endDate);
          } else {
            train.startDate = parseAndFormatDateWithTime(res[i].startDate);
            endStationName.value = res[i].stationName;
          }
          let nextIdx = (i + 1) % resSize;
          if (i !== resSize - 1) {
            train.endDate = parseAndFormatDateWithTime(res[nextIdx].startDate);
          } else {
            train.endDate = parseAndFormatDateWithTime(res[nextIdx].endDate);
          }
          train.endStation = res[nextIdx].stationName;
          result.value.push(res[i]);
          result.value.push(train);
        }


        // result.value = res;
        console.log(result);
      }
  ).catch(
      (error) => {
        console.log(error)
      }
  );
};

onMounted(() => {
  getResult();
});


const parseAndFormatDate = (dateString) => {
  let parsedDate = new Date(dateString);

  if (isNaN(parsedDate.getTime())) {
    console.error("유효하지 않은 날짜입니다.");
    return null;
  }

  // let formattedDate = `${parsedDate.getFullYear()}-${(parsedDate.getMonth() + 1).toString().padStart(2, '0')}-${parsedDate.getDate().toString().padStart(2, '0')} ${parsedDate.getHours().toString().padStart(2, '0')}:${parsedDate.getMinutes().toString().padStart(2, '0')}`;
  let formattedDate = `${(parsedDate.getMonth() + 1).toString().padStart(2, '0')}-${parsedDate.getDate().toString().padStart(2, '0')} `;

  return formattedDate;
}

const parseAndFormatDateWithTime = (dateString) => {
  let parsedDate = new Date(dateString);

  if (isNaN(parsedDate.getTime())) {
    console.error("유효하지 않은 날짜입니다.");
    return null;
  }

  let formattedDate = `${parsedDate.getFullYear()}-${(parsedDate.getMonth() + 1).toString().padStart(2, '0')}-${parsedDate.getDate().toString().padStart(2, '0')} ${parsedDate.getHours().toString().padStart(2, '0')}:${parsedDate.getMinutes().toString().padStart(2, '0')}`;
  // let formattedDate = `${(parsedDate.getMonth() + 1).toString().padStart(2, '0')}-${parsedDate.getDate().toString().padStart(2, '0')} `;

  return formattedDate;
}

</script>

<template>
  <v-container>
    <v-row>
      <v-col :cols="4" offset="4" class="text-center">
        <div class="title"><h1>여행 생성 완료</h1></div>
      </v-col>
    </v-row>
    <v-row class="mb-5">
      <v-col :cols="12">
        <div class="title"><h4>생성된 여행은 마이페이지에서 조회할 수 있습니다.</h4></div>
      </v-col
      >
    </v-row>


    <div class="divider div-transparent div-dot"></div>
    <v-col :cols="8" offset="2">
      <v-timeline align="start">
        <v-timeline-item v-for="(stat, i) in result" :key="i" :dot-color="'#f7323f'" :icon="'mdi-train'" fill-dot>
          <v-card v-if="i%2===0">
            <v-card-title class="text-h6 custCard ">
              {{ stat.stationName }}
            </v-card-title>
            <v-card-text class="pt-4">
              <v-table density="compact">
                <tbody>
                <tr>
                  <td v-if="i!==0">
                    <span>기간</span> : {{ parseAndFormatDate(stat.startDate) }} ~
                    {{ parseAndFormatDate(stat.endDate) }}<br/>
                  </td>
                  <td v-else>
                    <span>여행 출발</span>
                  </td>
                </tr>
                <tr v-for="(att,idx) in stat.content" :key="idx">
                  <td>{{ att.title }}</td>
                </tr>
                </tbody>
              </v-table>

            </v-card-text>
          </v-card>
          <v-card v-else>
            <v-card-title class="text-h6 custCard ">
              {{ stat.startStation }} => {{ stat.endStation }}
            </v-card-title>
            <v-card-text class="pt-4">
              <v-table density="compact">
                <tbody>
                <tr>
                  <td>출발 시간 : {{ stat.startDate }}</td>
                </tr>
                <tr>
                  <td>도착 시간 : {{ stat.endDate }}</td>
                </tr>
                <tr>
                  <td>열차 : {{ stat.trainType }}</td>
                </tr>
                </tbody>
              </v-table>
            </v-card-text>
          </v-card>
        </v-timeline-item>
        <!-- 여행 종료-->
        <v-timeline-item :dot-color="'#f7323f'" :icon="'mdi-train'" fill-dot>
          <v-card>
            <v-card-title class="text-h6 custCard ">
              {{ endStationName }}
            </v-card-title>
            <v-card-text class="pt-4">
              <v-table density="compact">
                <tbody>
                <tr>
                  <td>
                    <span>여행 종료</span>
                  </td>
                </tr>
                </tbody>
              </v-table>
            </v-card-text>
          </v-card>
        </v-timeline-item>
      </v-timeline>
    </v-col>
    <div class="divider div-transparent div-dot"></div>
    <div class="text-center mt-9 pt-6 mb-9">
      <v-btn color="#f7323f" class="Jalnan me-5">홈으로</v-btn>
      <v-btn color="#f7323f" class="Jalnan ms-5">홈으로</v-btn>
    </div>
  </v-container>
</template>

<style scoped>
.title {
  text-align: center;
  font-family: Jalnan;
  /*#f7323f*/
}


.divider {
  position: relative;
//margin-top: 90px; height: 1px;
}

.div-transparent:before {
  content: "";
  position: absolute;
  top: 0;
  left: 5%;
  right: 5%;
  width: 90%;
  height: 1px;
  background-image: linear-gradient(to right, transparent, rgb(48, 49, 51), transparent);
  opacity: 0.12;
}

.div-dot:after {
//content: ""; //position: absolute; //z-index: 1; //top: -9px; //left: calc(50% - 9px); //width: 18px; //height: 18px; //background-color: rgb(247 ,50 ,63 ); //border: 1px solid rgb(143, 143, 147); //border-radius: 50%; //box-shadow: inset 0 0 0 2px white, //0 0 0 4px white;
}
</style>
<style>
.custCard.v-card-title {

  background-color: #f7323f;
  color: white;
  font-family: Jalnan !important;

}


</style>