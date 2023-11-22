import {defineStore} from "pinia";


import {
    insertTrip,
    getTrainListFromApi,
    insertStationToDB,
    getTripStationInfo,
    getAttractionNearStation, insertAttraction, getTripResult
} from "@/api/tripApi";
import {httpStatusCode} from "@/util/http-status";
import {ref} from "vue";

export const useTripStore = defineStore("useTripStore", {
    persist: () => ({
        enabled: true
    }),

    state: () => ({
        newTripId: 0,
        minDate: null,
        maxDate: null,

    }),

    actions: {
        setMinDate(date) {
            this.minDate = date;
        },

        setMaxDate(date) {
            this.maxDate = date;
        },

        change(val) {
            this.newTripId.value = val;
        },


        async addTrip(tripDto) {
            // console.log("userLogin");
            await insertTrip(
                tripDto,
                (response) => {
                    if (response.status === httpStatusCode.OK) {
                        console.log(response.data.message);
                        console.log("새로 추가된 아이디 : ", response.data.data.tripId);
                        this.newTripId = response.data.data.tripId;
                        // this.$patch({newTripId: this.newTripId});
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

        async getAttNearSt(param) {
            return new Promise((resolve, reject) => {
                getAttractionNearStation(param,
                    (response) => {
                        resolve(response);
                    }, (error) => {
                        reject(error);
                    });
            });
        },
        async addAttraction(param) {
            return new Promise((resolve, reject) => {
                insertAttraction(param, (response) => {
                    resolve(response)
                }, (error) => {
                    reject(error)
                });
            });

        },

        async getTrainStationList(param) {
            return new Promise((resolve, reject) => {
                getTripStationInfo(
                    param,
                    (response) => {
                        resolve(response);
                    },
                    (error) => {
                        reject(error);
                    }
                )
            });
        },

        async getResult(val) {
            // console.log("getResult of tripId : " + this.newTripId.value);
            return new Promise((resolve, reject) => {
                getTripResult(
                    val,
                    (response) => {
                        resolve(response)
                    },
                    (error) => {
                        reject(error)
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