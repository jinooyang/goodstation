<script setup>
import {ref, watch, onMounted} from "vue";

var map;
const positions = ref([]);
const markers = ref([]);
const overlay = ref(null); // overlay 변수 추가

const props = defineProps({attractionList: Array, selectStation: Object, centerPos: Object});
const emit = defineEmits(["addToLeftSideBar"]);

const dialog = ref(false);
const dialogTitle = ref("");

const dialogText = ref("");
const dialogAddress = ref("");
const dialogImg = ref("");
const dialogImg2 = ref("");


watch(
    () => props.centerPos,
    () => {
      overlay.value.setMap(null);
      var moveLatLong = new kakao.maps.LatLng(props.centerPos.latitude, props.centerPos.longitude);
      map.panTo(moveLatLong);
      overlay.value = new kakao.maps.CustomOverlay({ // overlay 초기화
        content: '',
        map: map,
      });
      const content = `
        <div class="wrap">
          <div class="info">
            <div class="title">
              ${props.centerPos.title}
              <div class="close" onclick="closeOverlay()" title="닫기"></div>
            </div>
            <div class="body">
              <div class="img">
                <img src="${props.centerPos.img || '/src/assets/character2.jpeg'}" width="73" height="70">
              </div>
              <div class="desc">
                <div class="ellipsis">${props.centerPos.address}</div>
                <div style="text-align: center; padding-top: 5px;">
                <button class = "mycustbtn" onclick="openDialog(${props.centerPos.index})"">상세보기</button>
                <button class = "mycustbtn2" onclick="addToLeftSideBar(${props.centerPos.index})" >추가하기</button>

                </div>
              </div>
            </div>
          </div>
        </div>
      `;
      overlay.value.setContent(content);
      overlay.value.setPosition(moveLatLong);
      overlay.value.setMap(map);


    },
    {deep: true}
);


watch(
    () => props.selectStation.value,
    () => {
      // 이동할 위도 경도 위치를 생성합니다
      var moveLatLon = new kakao.maps.LatLng(props.selectStation.lat, props.selectStation.lng);

      // 지도 중심을 부드럽게 이동시킵니다
      // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
      map.panTo(moveLatLon);
    },
    {deep: true}
);

onMounted(() => {
  console.log("지도로 넘어온 정보 : " + props.attractionList);
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
        import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY
    }&libraries=services,clusterer`;
    /* global kakao */
    script.onload = () => kakao.maps.load(() => initMap());
    document.head.appendChild(script);
  }

});

watch(
    () => props.attractionList.value,
    () => {
      positions.value = [];
      props.attractionList.forEach((attraction) => {
        let obj = {};
        obj.latlng = new kakao.maps.LatLng(attraction.latitude, attraction.longitude);
        obj.stlatlng = new kakao.maps.LatLng(attraction.stationLat, attraction.stationLng);
        obj.title = attraction.title;
        obj.address = attraction.address;
        obj.img = attraction.img;
        obj.att = {...attraction};
        positions.value.push(obj);
      });
      loadMarkers();
    },
    {deep: true}
);

const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(37.555917612, 126.970381295),
    level: 8,
  };
  map = new kakao.maps.Map(container, options);

  // loadMarkers();
};


const loadMarkers = () => {
  deleteMarkers();
  markers.value = [];
  overlay.value = new kakao.maps.CustomOverlay({ // overlay 초기화
    content: '',
    map: map,
  });

  positions.value.forEach((position, index) => {
    const marker = new kakao.maps.Marker({
      map: map,
      position: position.latlng,
      title: position.title,
      clickable: true,
    });

    const content = `
  <div class="wrap">
    <div class="info">
      <div class="title">
        ${position.title}
        <div class="close" onclick="closeOverlay()" title="닫기"></div>
      </div>
      <div class="body">
        <div class="img">
          <img src="${position.img || '/src/assets/character2.jpeg'}" width="73" height="70">
        </div>
        <div class="desc">
          <div class="ellipsis">${position.address}</div>
          <div style="text-align: center; padding-top: 5px;">
          <button class = "mycustbtn" onclick="openDialog(${index})"">상세보기</button>
          <button class = "mycustbtn2" onclick="addToLeftSideBar(${index})" >추가하기</button>

          </div>
        </div>
      </div>
    </div>
  </div>
`;

    kakao.maps.event.addListener(marker, "click", function () {
      overlay.value.setMap(null);
      overlay.value.setContent(content);
      overlay.value.setPosition(marker.getPosition());
      overlay.value.setMap(map);
      map.panTo(marker.getPosition());
      // closeOverlay(); // closeOverlay 함수 호출 추가
    });


    markers.value.push(marker);
  });

  // const bounds = positions.value.reduce(
  //     (bounds, position) => bounds.extend(position.latlng),
  //     new kakao.maps.LatLngBounds()
  // );


  // 마커 이미지의 주소(URL)
  var imageUrl = '/src/assets/marker.png';

// 마커 이미지의 크기
  var imageSize = new kakao.maps.Size(44, 46);

// 마커 이미지 생성
  var markerImage = new kakao.maps.MarkerImage(imageUrl, imageSize);
  const marker = new kakao.maps.Marker({
    map: map,
    position: positions.value[0].stlatlng,
    title: positions.value[0].att.stationName,
    clickable: false,
    image: markerImage,
  });

  markers.value.push(marker);
  // map.setBounds(bounds);
  // 위도와 경도 좌표 생성


  //중앙 좌표 결정 짓는 부분!!!!
  var latlng = new kakao.maps.LatLng(positions.value[0].att.stationLat, positions.value[0].att.stationLng);

// 지도의 중심을 이동
  map.setCenter(latlng);
  map.setLevel(5);


};
const deleteMarkers = () => {
  if (markers.value.length > 0) {
    markers.value.forEach((marker) => marker.setMap(null));
  }
};


const openDialog = (index) => {
  dialogTitle.value = props.attractionList[index].title;
  dialogText.value = props.attractionList[index].content;
  dialogAddress.value = props.attractionList[index].address;
  dialogImg.value = props.attractionList[index].img;
  dialogImg2.value = props.attractionList[index].img2;
  dialog.value = true;
}
window.openDialog = openDialog;
const addToLeftSideBar = (index) => {

  emit("addToLeftSideBar", index);
}
window.addToLeftSideBar = addToLeftSideBar;
window.closeOverlay = function () {
  // console.log("닫기");
  overlay.value.setMap(null);
};

</script>

<template>
  <div id="map"></div>
  <v-dialog
      v-model="dialog"
      width="80%"
  >
    <v-card>
      <v-card-title class="Jalnan text-center">
        <br/>
        <h1 class="mt-5">{{ dialogTitle }}</h1>
      </v-card-title>
      <v-card-text>
        <p class="addr text-center">주소 : {{ dialogAddress }}</p><br/>
        <br/>
        <v-container fluid>
          <v-row justify="center">
            <v-col cols="auto">
              <v-img
                  :width="500"
                  aspect-ratio="16/9"
                  cover
                  :src="dialogImg"

              >

              </v-img>
            </v-col>
          </v-row>
        </v-container>
        <!--        <v-img-->
        <!--            :width="300"-->
        <!--            aspect-ratio="16/9"-->
        <!--            cover-->
        <!--            :src="dialogImg2"-->
        <!--        ></v-img>-->
        <br/>
        <v-col :cols="10" offset="1">
          <p>설명 : {{ dialogText }}</p>
        </v-col>
      </v-card-text>
      <v-card-actions>
        <v-btn class="mb-3" color="red-accent-3" block @click="dialog = false">닫기</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<style scoped>
.addr {
  font-weight: bold;
}


</style>

<style>
.mycustbtn {
  display: inline-block;
  border-radius: 5px;
  background-color: white;
  color: black;
  border: 2px solid #f7323f;
  padding: 5px 5px !important;
  font-size: 16px;
  cursor: pointer;
  margin: 0 7px 0 0 !important;
  transition: background-color 0.3s ease, color 0.3s ease; /* 부드러운 전환을 위한 CSS transition */
}

.mycustbtn:hover {
  background-color: #f7323f;
  color: white;
}


.mycustbtn2 {
  display: inline-block;
  border-radius: 5px;
  background-color: white;
  color: black;
  border: 2px solid #f7323f;
  padding: 5px 5px !important;
  font-size: 16px;
  cursor: pointer;
  margin: 0 0 0 7px !important;
  transition: background-color 0.3s ease, color 0.3s ease; /* 부드러운 전환을 위한 CSS transition */
}

.mycustbtn2:hover {
  background-color: #f7323f;
  color: white;
}

#map {
  width: 100%;
  height: 420px;
}

.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;
  line-height: 1.5;
}

.wrap * {
  padding: 0;
  margin: 0;
}

.wrap .info {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}

.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}

.info .title {
//padding: 0 0 0 10px; background: #f7323f; border-bottom: 1px solid #ddd; font-size: 18px;
  font-weight: bold;
  color: white;
  overflow: hidden;
  text-overflow: ellipsis;
  padding-right: 30px;
  padding-left: 5px;
}

.info .close {
  position: absolute;
  top: 6px;
  right: 10px;
  color: white;
  width: 17px;
  height: 17px;
  background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');
}

.info .close:hover {
  cursor: pointer;
}

.info .body {
  position: relative;
  overflow: hidden;
}

.info .desc {
  position: relative;
  margin: 13px 13px 0 90px;
  height: 75px;
}

.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}

.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: hidden;
}

.info:after {
  content: '';
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')
}

.info .link {
  color: #5085BB;
}
</style>