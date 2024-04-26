<template>
  <div id="main-content" class="container">
    <v-card flat
            style="min-width: 500px; width: 50%;">
      <v-card-title class="d-flex align-center pe-2">
        <v-icon icon="mdi-video-input-component"></v-icon> &nbsp;
        Produsele introduse

        <v-spacer></v-spacer>

        <v-text-field
            v-model="search"
            density="compact"
            label="Cautati"
            prepend-inner-icon="mdi-magnify"
            variant="solo-filled"
            flat
            hide-details
            single-line
        ></v-text-field>
      </v-card-title>

      <v-divider></v-divider>

      <v-data-table
      :items="shelfItems"
      :headers="headers"
      :search="search"
      items-per-page="25"
      density="compact">

      </v-data-table>
    </v-card>
  </div>
</template>

<script>
import ShelfItemService from "@/services/ShelfItemService";
import { shelfData } from "@/services/ShelfItemService";
import WebsocketService from '@/services/WebsocketService'

export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  computed: {
    shelfItems() {
      return shelfData.value.shelfItems;
    },
    headers() {
      return [
        {
          title: "Produs",
          align: 'center',
          key: 'name'
        },
        {
          title: "Categorie",
          align: 'center',
          key: 'category'
        }
      ]
    }
  },
  data () {
    return {
      search: ''
    }
  },
  mounted() {
    WebsocketService.init();
    ShelfItemService.getAll();
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
