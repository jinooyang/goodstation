import {defineStore} from "pinia";


import {insertTrip, getTrainListFromApi} from "@/api/tripApi";
import {httpStatusCode} from "@/util/http-status";
import {ref} from "vue";

export const useTripStore = defineStore("useTripStore", () => {

    const newTripId = ref(0);

    const addTrip = async (tripDto) => {
        // console.log("userLogin");
        await insertTrip(
            tripDto,
            (response) => {
                if (response.status === httpStatusCode.OK) {
                    console.log(response.data.message);
                    newTripId.value = response.data.data.tripId;

                } else {
                    console.log("addTrip FAIL");

                }
            },
            (error) => {
                console.log("addTrip FAIL");
                console.error(error);

            }
        );
    };

    const getTrains = async (train) => {
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
    }


    return {
        newTripId,
        addTrip,
        getTrains

    };
});
