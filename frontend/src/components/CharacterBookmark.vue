<template>
  <div class="container-box">
    <h5 class="title">즐겨찾기한 캐릭터</h5>
    <ul class="list-group">
      <li class="list-group-item bg-white text-dark">남극빙수</li>
      <li class="list-group-item bg-white text-dark">셩이일세</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'CharacterBookmark',
}
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
.list-group-item {
  border: none;
}
</style>
