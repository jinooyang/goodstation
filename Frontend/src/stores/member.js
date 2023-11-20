import {onMounted, ref} from "vue";
import {useRouter} from "vue-router";
import {defineStore} from "pinia";
import {jwtDecode} from "jwt-decode";

import {userConfirm, findById, logout, checkDupId, checkDupEmail, checkDupNick} from "@/api/memberApi";
import {httpStatusCode} from "@/util/http-status";

export const useMemberStore = defineStore("memberStore", () => {
    const router = useRouter();

    const isLogin = ref(false);
    const isLoginError = ref(false);
    const userInfo = ref(null);
    const isValidToken = ref(false);


    onMounted(() => {
        const storedAccessToken = sessionStorage.getItem("accessToken");

        if (storedAccessToken) {
            isValidToken.value = true;
            getUserInfo(storedAccessToken);
            if (isValidToken) {
                isLogin.value = true;
                isLoginError.value = false;
            } else {
                isLogin.value = false;
                isLoginError.value = true;
            }
        }
    });
    const userLogin = async (loginUser) => {
        // console.log("userLogin");
        await userConfirm(
            loginUser,
            (response) => {
                // console.log("memberjs , userLogin : " + response.data.data.token);
                if (response.status === httpStatusCode.CREATE) {
                    // let {data} = response;
                    // console.log("memberjs , userLogin : " + data);
                    let accessToken = response.data.data.token;
                    // console.log("accessToken : " + accessToken);
                    // let refreshToken = data["refresh-token"];
                    isLogin.value = true;
                    isLoginError.value = false;
                    isValidToken.value = true;
                    sessionStorage.setItem("accessToken", accessToken);
                    // sessionStorage.setItem("refreshToken", refreshToken);
                } else {
                    console.log("FAIL!!!!!!!");
                    isLogin.value = false;
                    isLoginError.value = true;
                    isValidToken.value = false;
                }
            },
            (error) => {
                console.error(error);
            }
        );
    };

    const getUserInfo = (token) => {
        // console.log(token);
        let decodeToken = jwtDecode(token);
        findById(
            decodeToken.sub,
            (response) => {
                if (response.status === httpStatusCode.OK) {
                    // console.log("member.js의 getUserInfo까지옴!!!")
                    userInfo.value = response.data.data.memberInfo;
                    console.log("로그인 유저 정보 : " + userInfo.value.memberId);
                    console.log("로그인 유저 정보 : " + userInfo.value.name);
                    console.log("로그인 유저 정보 : " + userInfo.value.role);
                    console.log("로그인 유저 정보 : " + userInfo.value.email);
                    console.log("로그인 유저 정보 : " + userInfo.value.nickname);
                } else {
                    console.log("유저 정보 없음!!!!");
                    isValidToken.value = false;
                }
            },
            async (error) => {
                console.error(
                    "getUserInfo() error code [토큰 만료되어 사용 불가능.] ::: ",
                    error.response.status
                );
                isValidToken.value = false;

                // await tokenRegenerate();
            }
        );
    };

    const checkId = async (memberId) => {
        return new Promise((resolve, reject) => {
            checkDupId(
                memberId,
                (response) => {
                    const res = response.data.data.pos;
                    resolve(res);
                },
                (error) => {
                    reject(error);
                }
            );
        });
    };


    const checkEmail = async (email) => {
        return new Promise((resolve, reject) => {
            checkDupEmail(
                email,
                (response) => {
                    const res = response.data.data.pos;
                    resolve(res);
                },
                (error) => {
                    reject(error);
                }
            );
        });
    };


    const checkNick = async (nick) => {
        return new Promise((resolve, reject) => {
            checkDupNick(
                nick,
                (response) => {
                    const res = response.data.data.pos;
                    resolve(res);
                },
                (error) => {
                    reject(error);
                }
            );
        });
    }

    const userLogout = async () => {

        sessionStorage.removeItem("accessToken");
        isLogin.value = false;
        userInfo.value = null;
        isValidToken.value = false;
        // console.log("로그아웃 완료!!");
    };


    return {
        isLogin,
        isLoginError,
        userInfo,
        isValidToken,
        userLogin,
        getUserInfo,
        // tokenRegenerate,
        userLogout,
        checkId,
        checkEmail,
        checkNick
    };
});
