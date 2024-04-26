<template>
  <v-toolbar
      density="compact"
      :elevation="2"
      style="z-index: 1010; position: fixed;"
      color="primary"

  >
    <v-icon icon="mdi-assistant" style="margin-left: 15px;"/>
    <span style="font-size: 22px; font-weight: 500; margin-left: 5px;">Assistant</span>
  </v-toolbar>
</template>

<script>
export default {
  name: "TopBar"
}
</script>

<style scoped>

</style>