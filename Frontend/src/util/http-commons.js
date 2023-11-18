import axios from "axios";
// import { httpStatusCode } from "./http-status";

const { VITE_VUE_API_URL, VITE_ELECTRIC_CHARGING_STATION_URL } = import.meta.env;

function localAxios() {
    const instance = axios.create({
        baseURL: VITE_VUE_API_URL,
        // withCredentials: true,
        // headers: {
        //   "Content-Type": "application/json;charset=utf-8",
        // },
    });
    instance.defaults.headers.common["Authorization"] = "";
    instance.defaults.headers.post["Content-Type"] = "application/json";
    instance.defaults.headers.put["Content-Type"] = "application/json";
    return instance;
}
export { localAxios };