<template>
  <TopNavbar />
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <EventView />
        <TodoList />
        <div class="row">
          <div class="col-6">
            <LostarkNotice />
          </div>
          <div class="col-6">
            <JloaNotice />
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <CharacterBookmark />
      </div>
    </div>
  </div>
</template>

<script>
import LostarkNotice from '@/components/LostarkNotice.vue';
import JloaNotice from '@/components/JloaNotice.vue';
import EventView from '@/components/EventView.vue';
import TodoList from '@/components/TodoList.vue';
import CharacterBookmark from '@/components/CharacterBookmark.vue';
import TopNavbar from "@/components/TopNavbar.vue";


export default {
  name: 'MainPage',
  components: {
    TopNavbar,
    EventView,
    LostarkNotice,
    JloaNotice,
    TodoList,
    CharacterBookmark,
  },
};
</script>