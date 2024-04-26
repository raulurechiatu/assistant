<template>
  <router-view />
</template>

<script>

export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.v-table > div > table > thead > tr {
  background-color: rgb(var(--v-theme-secondary)) !important;
  color: white;
}

.content-page-container {
  padding-top: 60px !important;
  margin: 0 !important;
}

.content-container {
  padding: 20px
;
}
</style>
