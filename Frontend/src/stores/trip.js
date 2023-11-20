import {defineStore} from "pinia";


import {insertTrip, getTrainListFromApi, insertStationToDB} from "@/api/tripApi";
import {httpStatusCode} from "@/util/http-status";
import {ref} from "vue";
//
// export const useTripStore = defineStore("useTripStore", () => {
//
//     const newTripId = ref(0);
//
//     const addTrip = async (tripDto) => {
//         // console.log("userLogin");
//         await insertTrip(
//             tripDto,
//             (response) => {
//                 if (response.status === httpStatusCode.OK) {
//                     console.log(response.data.message);
//                     newTripId.value = response.data.data.tripId;
//
//                 } else {
//                     console.log("addTrip FAIL");
//
//                 }
//             },
//             (error) => {
//                 console.log("addTrip FAIL");
//                 console.error(error);
//
//             }
//         );
//     };
//     const insertStation = async (list) => {
//         console.log("list : " + JSON.stringify(list));
//         await insertStationToDB(
//             list,
//             (response) => {
//                 console.log("tripjs insertstation 성공");
//             },
//             (error) => {
//                 console.log("tripjs insertstation 실패");
//             }
//         );
//
//
//     }
//
//     const getTrains = async (train) => {
//         return new Promise((resolve, reject) => {
//             getTrainListFromApi(
//                 train,
//                 (response) => {
//                     if (response.status === httpStatusCode.OK) {
//                         console.log(response.data.data.trainList);
//                         console.log("성공");
//                         resolve(response.data.data.trainList);
//                     } else {
//                         console.log("getTrainListFromApi FAIL1");
//                         reject("Failed to get train list");
//                     }
//                 },
//                 (error) => {
//                     console.log("getTrainListFromApi FAIL2");
//                     console.error(error);
//                     reject(error);
//                 }
//             );
//         });
//     }
//
//
//     return {
//         newTripId,
//         addTrip,
//         getTrains,
//         insertStation
//     };
// });
export const useTripStore = defineStore("useTripStore", {
    persist: true,

    state: () => ({
        newTripId: 0,

    }),

    actions: {

        change(val){
            this.newTripId.value = val;
        },


        async addTrip(tripDto) {
            // console.log("userLogin");
            await insertTrip(
                tripDto,
                (response) => {
                    if (response.status === httpStatusCode.OK) {
                        console.log(response.data.message);
                        console.log("새로 추가된 아이디 : " , response.data.data.tripId);
                        this.newTripId = response.data.data.tripId;
                        this.$patch({ newTripId: this.newTripId });
                        console.log("변경된 newTripId : " + this.newTripId);

                    } else {
                        console.log("addTrip FAIL");

                    }
                },
                (error) => {
                    console.log("addTrip FAIL");
                    console.error(error);

                }
            );
        },
        async insertStation(list) {
            return new Promise((resolve, reject) => {
                console.log("list : " + JSON.stringify(list));
                insertStationToDB(
                    list,
                    (response) => {
                        console.log("tripjs insertstation 성공");
                        resolve(response);
                    },
                    (error) => {
                        console.log("tripjs insertstation 실패");
                        reject(error);
                    }
                );
            });
        },
        async getTrains(train) {
            return new Promise((resolve, reject) => {
                getTrainListFromApi(
                    train,
                    (response) => {
                        if (response.status === httpStatusCode.OK) {
                            console.log(response.data.data.trainList);
                            console.log("성공");
                            resolve(response.data.data.trainList);
                        } else {
                            console.log("getTrainListFromApi FAIL1");
                            reject("Failed to get train list");
                        }
                    },
                    (error) => {
                        console.log("getTrainListFromApi FAIL2");
                        console.error(error);
                        reject(error);
                    }
                );
            });
        },
        // 다른 액션들...
    },
});