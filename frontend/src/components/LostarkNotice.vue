<template>
  <div class="container-box">
    <h5 class="title">로스트아크 공지사항</h5>
    <ul class="list-group">
      <li v-for="(notice, index) in notices" :key="index" class="list-group-item bg-white text-dark">
        <a :href="notice.Link" target="_blank"
           rel="noopener noreferrer"
           class="d-flex justify-content-between align-items-center">
          <span class="type">{{ notice.Type }}</span>
          <span class="title">{{ notice.Title }}</span>
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';
const url = 'https://developer-lostark.game.onstove.com';

export default {
  data() {
    return {
      notices: []
    };
  },
  async mounted() {
    const APIKEY = process.env.VUE_APP_LOSTARK_API_KEY;
    try {
      const response = await axios.get(url + '/news/notices', {
        headers: {
          'accept': 'application/json',
          authorization: 'bearer ' + APIKEY
        }
      });
      this.notices = response.data.slice(0, 5);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  }
}
</script>

<style scoped>
.list-group-item {
  border: none;
}
.container-box {
  border: 1.5px solid #ccc;
  border-radius: 10px;
  padding: 15px;
  background-color: #ffffff; /* Light background */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  margin-top: 10px;
  margin-bottom: 10px;
}
.title {
  font-weight: bold;
}
a {
  text-decoration: none;
  color: inherit;
}
a:hover {
  text-decoration: underline;
}
.type {
  font-weight: bold;
  flex-shrink: 0;
  margin-right: 20px;
}
.title {
  white-space: nowrap;        /* 한 줄로 표시 */
  overflow: hidden;           /* 넘치는 텍스트 숨김 */
  text-overflow: ellipsis;    /* 넘치는 부분에 "..." 표시 */
  flex-grow: 1;               /* 가로 공간 차지 */
}
</style>
