<template>
  <div class="event-box">
    <div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-inner">
        <div
            class="carousel-item"
            :class="{ active: index === 0 }"
            v-for="(item, index) in data"
            :key="index"
        >
          <a :href="item.Link" target="_blank">
            <img :src="item.Thumbnail" class="d-block w-100 carousel-image" :alt="item.Title">
          </a>
        </div>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
const url = 'https://developer-lostark.game.onstove.com';

export default {
  data() {
    return {
      data: []
    };
  },
  async mounted() {
    const APIKEY = process.env.VUE_APP_LOSTARK_API_KEY;
    try {
      const response = await axios.get(url + '/news/events', {
        headers: {
          'accept': 'application/json',
          authorization: 'bearer ' + APIKEY
        }
      });
      this.data = response.data;
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  }
}
</script>

<style scoped>
.carousel-image {
  max-height: 330px; /* 원하는 높이로 조정 */
  object-fit: cover; /* 이미지 비율 유지 */
  border-radius: 10px;
}
.event-box {
  border-radius: 10px;
  background-color: #ffffff; /* Light background */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  margin-bottom: 10px;
}
</style>
