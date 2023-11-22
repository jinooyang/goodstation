<script setup>
import {useMemberStore} from "@/stores/member";
import {useRoute, useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import axios from "axios";
import {computed} from 'vue';

const memberStore = useMemberStore();
const route = useRoute();
const router = useRouter();
const board = ref();
const isLogin = memberStore.isLogin;
const memberId = memberStore.userInfo.memberId;
const comments = ref([]);
const newComment = ref('');
const isLiked = ref(false);

const fetchDataFromServer = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/board/${route.params.boardId}`);
    board.value = response.data;
    console.log(board);
  } catch (error) {
    console.error('데이터를 불러오는데 실패했습니다.', error);
  }
};

const goToListPage = () => {
  router.push('/board').then(() => {
    window.scrollTo(0, 0);
  });
};

const formatDate = (dateTimeString) => {
  const dateTime = new Date(dateTimeString);
  const formattedTime = dateTime.toLocaleString("ko-KR", {
    year: "numeric",
    month: "2-digit",
    day: "2-digit",
    hour: "2-digit",
    minute: "2-digit",
  });
  return formattedTime;
};

const goToEditPage = async (board) => {
  router.push(`/board/edit/${route.params.boardId}`).then(() => {
    window.scrollTo(0, 0);
  });
};

const deleteBoard = async () => {
  try {
    await axios.delete(`http://localhost:8080/board/${route.params.boardId}`);
    console.log('게시물이 성공적으로 삭제되었습니다.');

    router.push('/board');
  } catch (error) {
    console.error('게시물 삭제 중 오류가 발생했습니다:', error);
  }
};

const addComment = async () => {
  try {
    const commentData = {
      boardId: route.params.boardId,
      content: newComment.value,
      memberId: memberId
    };
    await axios.post(`http://localhost:8080/board/${route.params.boardId}/comments`, commentData);
    console.log('댓글이 성공적으로 작성되었습니다.');
    newComment.value = '';
    await fetchComments();
  } catch (error) {
    console.error('댓글 작성 중 오류가 발생했습니다:', error);
  }
};

const deleteComment = async (comment) => {
  try {
    await axios.delete(`http://localhost:8080/board/${route.params.boardId}/comments/${comment.commentId}`);
    console.log('댓글이 성공적으로 삭제되었습니다.');
    await fetchComments(); // 댓글 목록을 다시 불러옵니다.
  } catch (error) {
    console.error('댓글 삭제 중 오류가 발생했습니다:', error);
  }
};

const like = async (memberId) => {
  console.log(memberId);
  try {
    const response = await axios.put(`http://localhost:8080/board/${route.params.boardId}/like?memberId=${memberId}`);
    console.log(response.data);
    await getLikesCount();
    isLiked.value = !isLiked.value;
  } catch (error) {
    if (error.response && error.response.data) {
      alert(error.response.data);
    } else {
      console.error(error);
    }
  }
};

const likesCount = ref(0);

const getLikesCount = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/board/${route.params.boardId}/likes`);
    likesCount.value = response.data;
  } catch (error) {
    console.error('좋아요 수를 불러오는데 실패했습니다.', error);
  }
};


const fetchComments = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/board/${route.params.boardId}/comments`);
    comments.value = response.data.data.commentList.map(comment => ({
      ...comment,
      commentLikeCount: 0,
    }));
  } catch (error) {
    console.error('댓글 데이터를 불러오는데 실패했습니다.', error);
  }
};

const likeComment = async (commentId) => {
  console.log(commentId);
  try {
    await axios.put(`http://localhost:8080/board/${route.params.boardId}/comments/like`, {
      commentId: commentId,
      memberId: memberId
    });
    console.log("댓글에 좋아요 성공!")
    const commentToUpdate = comments.value.find(comment => comment.commentId === commentId);
    if (commentToUpdate) {
      // 좋아요를 누른 댓글의 좋아요 수를 업데이트합니다.
      const response = await axios.get(`http://localhost:8080/board/${route.params.boardId}/comments/${commentId}/likes`);
      commentToUpdate.commentLikeCount = response.data;
      // console.log(commentToUpdate.commentLikeCount);
    }
  } catch (error) {
    if (error.response && error.response.data) {
      alert(error.response.data.message);
    } else {
      console.error(error);
    }
  }
};



const reversedComments = computed(() => {
  return [...comments.value].reverse();
});


onMounted(async () => {
  fetchDataFromServer();
  fetchComments();
  await getLikesCount();
});
const goToTripStation = () => {
  router.push("/trip/create");
};
</script>

<template>
  <v-container v-if="board">
    <v-row class="mt-5">
      <v-col :cols="8" offset="2" class="text-center">
        <div class="title"><h1>제목 : {{ board.title }}</h1></div>
      </v-col>
    </v-row>
    <v-row class="mb-5">
      <v-col :cols="12">
        <div class="title"><h2>작성자 : {{ board.memberId }}</h2></div>
      </v-col
      >
    </v-row>
    <v-row class="mb-2">
      <v-col :cols="12" class="text-right">
        <div class="create-time"><p>작성 시간 : {{ formatDate(board.createTime) }}</p></div>
      </v-col>
    </v-row>
    <hr/>


    <v-row class="mt-5 mb-5">
      <v-col :cols="8" offset="2">
        <p>{{ board.content }}</p>
      </v-col>
    </v-row>
    <div class="d-flex flex-column align-center justify-center ma-9 pa-9">
      <div v-for="n in 1">
        <img src="../../assets/goodchoice.jpg" width="500"/>
        <!--        <v-img src="src/assets/goodchoice.jpg"/>-->
      </div>
    </div>

    <v-row class="mt-6 mb-6">
      <v-col cols="12" class="text-center mb-5  " >
        <v-btn  icon color="white" @click="like(memberId)">
          <v-icon color="red" v-if="isLiked">mdi-heart</v-icon>
          <v-icon color="red" v-else>mdi-heart-outline</v-icon>
        </v-btn>
        <span class="ml-4 Jalnan">{{ likesCount }}</span>
      </v-col>
      <v-col cols="12" class="text-center">
        <v-btn class="custom-btn mr-3 Jalnan" @click="goToListPage">글목록</v-btn>
        <v-btn class="custom-btn mr-3 Jalnan" @click="() => goToEditPage(board)"
               v-if="isLogin && memberId === board.memberId">글수정
        </v-btn>
        <v-btn class="custom-btn Jalnan" @click="deleteBoard" v-if="isLogin && memberId === board.memberId">글삭제</v-btn>
      </v-col>
    </v-row>
  </v-container>
  <v-container>
    <v-row>
      <v-col cols="11" offset="1">
        <v-card v-for="comment in reversedComments" :key="comment.commentId" class="mb-5">
          <v-row>
            <v-col cols="9">
              <v-card-text class="comment-text">
                {{ comment.memberId }} : {{ comment.content }}
              </v-card-text>
            </v-col>
            <v-col cols="3" class="text-end">
              <v-card-text>
                작성 시간 : {{ formatDate(comment.create_time) }}
              </v-card-text>
              <v-card-actions>
                <v-col class="text-end">
<!--                  <v-btn small class="mr-2 custom-btn Jalnan" @click="editComment(comment)"-->
<!--                         v-if="isLogin && memberId === comment.memberId">댓글 수정-->
<!--                  </v-btn>-->
                  <v-btn small class="custom-btn Jalnan" @click="deleteComment(comment)"
                         v-if="isLogin && memberId === comment.memberId">댓글 삭제
                  </v-btn>
                  <v-btn small icon color="white" @click="likeComment(comment.commentId)">
                    <v-icon color="red">mdi-heart</v-icon>
                  </v-btn>
                  <span class="mr-4 Jalnan">{{ comment.commentLikeCount }}</span>
                </v-col>
              </v-card-actions>
            </v-col>
          </v-row>
        </v-card>

      </v-col>
    </v-row>
    <v-row>
      <v-col cols="11" offset="1">
        <v-textarea hide-details color="red-accent-3" label="댓글을 입력하세요" variant="outlined" v-model="newComment"
                    rows="3"></v-textarea>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" class="text-center">
        <v-btn class="mb-5 custom-btn Jalnan" @click="addComment">댓글 작성</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
.title {
  text-align: center;
  font-family: Jalnan;
}

.custom-btn {
  background-color: rgb(247, 50, 63);
  color: rgb(255, 255, 255);
  caret-color: rgb(255, 255, 255);
}

.comment-text{
  font-size: 15px;
  font-family: Jalnan;
}
</style>
