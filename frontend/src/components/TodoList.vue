<template>
  <div class="container-box">
    <h5 class="title">오늘의 일정</h5>
    <div class="container py-3">
      <div class="row mb-4">
        <!-- 제목 섹션 -->
        <div class="col-4 text-center">
          <strong>모험 섬</strong>
          <span v-if="nextAdventureTime ==='자리비움'" class="ms-2 text-secondary">{{nextAdventureTime}}</span>
          <span v-else class="ms-2 text-warning">{{nextAdventureTime}}</span>
        </div>
        <div class="col-4 text-center">
          <strong>필드보스</strong>
          <span v-if="nextFieldBossTime ==='자리비움'" class="ms-2 text-secondary">{{nextFieldBossTime}}</span>
          <span v-else class="ms-2 text-warning">{{nextFieldBossTime}}</span>
        </div>
        <div class="col-4 text-center">
          <strong>카오스게이트</strong>
          <span v-if="nextGateTime ==='자리비움'" class="ms-2 text-secondary">{{nextGateTime}}</span>
          <span v-else class="ms-2 text-warning">{{nextGateTime}}</span>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-4 col-md-6 mb-3" v-for="(card, index) in filteredCards" :key="index">
          <div class="card p-3">
            <div class="d-flex align-items-center">
              <img :src="card.ContentsIcon"
                   class="rounded me-3"
                   style="width: 50px; height: 50px;" />
              <div>
                <strong>{{ card.ContentsName }}</strong>
              </div>
            </div>
            <div class="mt-3">
              <span v-for="(rewardItem, idx) in card.RewardItems" :key="idx" class="me-2">
                <span v-for="(item, itemIndex) in rewardItem.Items" :key="itemIndex">
                  <img :src="item.Icon"
                       class="rounded"
                       :title="item.Name"
                       alt="보상 아이콘"
                       style="width: 30px; height: 30px; margin-right: 7px" />
                </span>
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import axios from "axios";
const url = 'https://developer-lostark.game.onstove.com';

export default {
  data() {
    return {
      cards: []
    };
  },
  async mounted() {
    const APIKEY = process.env.VUE_APP_LOSTARK_API_KEY;
    try {
      const response = await axios.get(url + '/gamecontents/calendar', {
        headers: {
          'accept': 'application/json',
          authorization: 'bearer ' + APIKEY
        }
      });
      this.cards = response.data;
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  },
  computed: {
    filteredCards() {
      const now = new Date();
      const { startOfToday, endOfTomorrow } = this.getTimeRange(now);

      return this.cards.filter(card => {
        return card.CategoryName === '모험 섬' &&
            card.StartTimes.some(startTime => {
              const startDate = new Date(startTime);
              return startDate >= startOfToday && startDate < endOfTomorrow; // 범위 내에 있는지 확인
            });
      });
    },
    nextAdventureTime() {
      return this.getNextEventTime('모험 섬');
    },
    nextFieldBossTime() {
      return this.getNextEventTime('필드보스');
    },
    nextGateTime() {
      return this.getNextEventTime('카오스게이트');
    },
  },
  methods: {
    getTimeRange(now) {
      const startOfToday = new Date(now.getFullYear(), now.getMonth(), now.getDate(), 6, 30); // 오늘 6시 30분
      const endOfTomorrow = new Date(startOfToday);
      endOfTomorrow.setDate(endOfTomorrow.getDate() + 1); // 다음날 6시 30분
      return { startOfToday, endOfTomorrow };
    },
    getNextEventTime(category) {
      const now = new Date();
      const { startOfToday, endOfTomorrow } = this.getTimeRange(now);
      let nextTime = null;

      this.cards.forEach(card => {
        if (card.CategoryName === category) {
          card.StartTimes.forEach(startTime => {
            const startDate = new Date(startTime);
            let adjustedStartDate = startDate;
            if (category === '카오스게이트') {
              adjustedStartDate = new Date(startDate.getTime() + 10 * 60 * 1000); // 10분 추가
            }
            if (adjustedStartDate >= startOfToday && adjustedStartDate < endOfTomorrow && adjustedStartDate >= now) {
              if (!nextTime || adjustedStartDate < nextTime) {
                nextTime = adjustedStartDate;
              }
            }
          });
        }
      });
      return nextTime ? nextTime.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }) : '자리비움';
    }
  }
};
</script>

<style scoped>
.container-box {
  border: 1.5px solid #ccc;
  border-radius: 10px;
  padding: 15px;
  background-color: #ffffff; /* Light background */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}
.title {
  font-weight: bold;
}
.card {
  border-radius: 10px;
}
</style>
