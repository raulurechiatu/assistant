<template>
  <HelloWorld/>
</template>

<script>
import HelloWorld from './views/Home.vue'

export default {
  name: 'App',
  components: {
    HelloWorld
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.v-table > div > table > thead > tr {
  background-color: #2c3e50 !important;
  color: white;
}
</style>
