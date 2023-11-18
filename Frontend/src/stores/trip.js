import {defineStore} from "pinia";


import {insertTrip} from "@/api/tripApi";
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
                    newTripId.value = response.data.data.tripId;
                } else {
                    console.log("FAIL1!!!!!!!");
                }
            },
            (error) => {
                console.log("FAIL2!!!!!!!");
                console.error(error);
            }
        );
    };


    return {
        newTripId,
        addTrip

    };
});
