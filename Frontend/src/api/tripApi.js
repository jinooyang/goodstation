import {localAxios} from "@/util/http-commons";

const local = localAxios();

async function insertTrip(param, success, fail) {
    local.defaults.headers["X-AUTH-TOKEN"] = sessionStorage.getItem("accessToken");
    await local.post(`/trip/new`, param).then(success).catch(fail);
}

async function getTrainListFromApi(param, success, fail) {
    local.defaults.headers["X-AUTH-TOKEN"] = sessionStorage.getItem("accessToken");
    await local.get(`/trip/trainapi`, {params : param} ).then(success).catch(fail);
}

async function insertStationToDB(param,success,fail){
    console.log("param >> ", param);
    local.defaults.headers["X-AUTH-TOKEN"] = sessionStorage.getItem("accessToken");
    await local.post(`/trip/station`, param).then(success).catch(fail);
}


export {insertTrip, getTrainListFromApi, insertStationToDB};
