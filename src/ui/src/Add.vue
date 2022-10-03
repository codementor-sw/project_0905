<template>
  <div>
    <b-container fluid="sm">
      <b-navbar-brand>
        여행 프로그램 등록하기
      </b-navbar-brand>
      <b-card bg-variant="light">
        <b-form @submit="onSubmit">
          <b-form-group
              id="input-group-2"
              label="이름"
              label-for="input-2"
              label-cols-sm="1"
              label-cols-lg="1"
              content-cols-sm
              content-cols-lg="3"
          >
            <b-form-input
                id="input-2"
                v-model="data.name"
            >
            </b-form-input>
          </b-form-group>
          <b-form-group
              id="input-group-3"
              label="소개"
              label-for="input-3"
              label-cols-sm="1"
              label-cols-lg="1"
              content-cols-sm
              content-cols-lg="5"
          >
            <b-form-input
                id="input-3"
                v-model="data.introduction"
            >
            </b-form-input>
          </b-form-group>
          <b-form-group
              id="input-group-4"
              label="상세 소개"
              label-for="input-4"
              label-cols-sm="1"
              label-cols-lg="1"
              content-cols-sm
              content-cols-lg="10"
          >
            <b-form-input
                id="input-4"
                v-model="data.introductionDetail"
            >
            </b-form-input>
          </b-form-group>
          <b-form-group
              id="input-group-5"
              label="지역"
              label-for="input-5"
              label-cols-sm="1"
              label-cols-lg="1"
              content-cols-sm
              content-cols-lg="2"
          >
            <b-form-input
                id="input-4"
                v-model="data.region"
            >
            </b-form-input>
          </b-form-group>
          <b-form-group
              id="input-group-6"
              label="테마이름"
              label-for="input-6"
              label-cols-sm="1"
              label-cols-lg="1"
              content-cols-sm
              content-cols-lg="3"
          >
            <b-form-input
                id="input-6"
                v-model="data.themeName"
            >
            </b-form-input>
          </b-form-group>
          <b-button type="submit" variant="primary">등록하기</b-button>
        </b-form>
      </b-card>
    </b-container>
  </div>
</template>
<script>

import axios from "axios";

export default {
  data() {
    return {
      data: {}
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault()
      axios.post(`/api/programs/`, this.data).catch(function (error) {
        if (error.response) {
          alert(JSON.stringify(error.response.data));
        }
      }).then(() => {
        this.$router.push("/list");
      })
    }
  }
}
</script>
<style>
.form-row {
  display: flex;
  flex-wrap: wrap;
  margin-right: -5px;
  margin-left: -5px;
}

.form-group {
  margin-bottom: 1rem;
}
</style>