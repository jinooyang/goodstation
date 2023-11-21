import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function userConfirm(param, success, fail) {
    // console.log("userConfirm");
    await local.post(`/member/login`, param).then(success).catch(fail);
}

async function findById(userid, success, fail) {
    local.defaults.headers["X-AUTH-TOKEN"] = sessionStorage.getItem("accessToken");
    await local.get(`/member/info`).then(success).catch(fail);
}

// async function tokenRegeneration(user, success, fail) {
//     local.defaults.headers["refreshToken"] = sessionStorage.getItem("refreshToken"); //axios header에 refresh-token 셋팅
//     await local.post(`/user/refresh`, user).then(success).catch(fail);
// }

async function logout(userid, success, fail) {
    await local.get(`/user/logout/${userid}`).then(success).catch(fail);
}

async function checkDupId(memberId, success,fail){
    await local.get(`member/memberId/${memberId}`).then(success).catch(fail);
}

async function checkDupEmail(email, success,fail){
    await local.get(`member/email/${email}`).then(success).catch(fail);
}

async function checkDupNick(nick, success,fail){
    await local.get(`member/nickname/${nick}`).then(success).catch(fail);
}

async function register(param,success,fail){
    await local.post(`member/signup`,param).then(success).catch(fail);
}


export { userConfirm, findById,  logout ,
    checkDupId, checkDupEmail, checkDupNick,
    register};
