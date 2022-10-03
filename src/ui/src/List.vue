<template>
  <div>
    <b-container fluid="sm">
    <b-navbar-brand>
      여행 프로그램 리스트
    </b-navbar-brand>
      <b-button href="#/add" variant="primary">등록하기</b-button>
    <b-table :items="contents"
             :per-page="perPage"
             :current-page="currentPage">
      <template #cell(id)="data">
        <a :href="`#/view/${data.value}`">{{ data.value }}</a>
      </template>
    </b-table>
      <b-pagination
          v-model="currentPage"
          :total-rows="rows"
          :per-page="perPage"
      ></b-pagination>
    </b-container>
  </div>
</template>
<script>

import axios from "axios";

export default {
  data() {
    return {
      perPage: 10,
      rows: 0,
      currentPage: 1,
      contents: []
    }
  },
  mounted() {
    axios.get("/api/programs").then(({data}) => {
      this.contents = data.content;
      this.perPage = data.pageable.pageSize;
      this.rows = data.pageable.totalElements;
      this.currentPage = data.pageable.totalPages;
    })
  }

}
</script>
