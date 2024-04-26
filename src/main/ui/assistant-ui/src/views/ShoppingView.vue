<template>
  <top-bar></top-bar>
  <v-container id="shopping-content" class="content-page-container">
    <v-row>
      <left-menu></left-menu>
      <div>
        <div class="content-container">
          <v-card flat
                  style="min-width: 500px; width: 50%;">
            <v-card-title class="d-flex align-center pe-2">
              <v-icon icon="mdi-cart"></v-icon> &nbsp;
              Cosul de cumparaturi

              <v-spacer></v-spacer>
            </v-card-title>

            <v-text-field
                v-model="search"
                density="compact"
                label="Cautati"
                prepend-inner-icon="mdi-magnify"
                variant="solo-filled"
                flat
                hide-details
                single-line
                style="margin: 10px 10px"
            ></v-text-field>

          </v-card>
        </div>
        <shelf-items-grid></shelf-items-grid>
      </div>
    </v-row>
  </v-container>
</template>

<script>
import { shelfData } from "@/services/ShelfItemService";
import TopBar from "@/components/TopBar";
import LeftMenu from "@/components/LeftMenu";
import ShelfItemsGrid from "@/components/ShelfItemsGrid";

export default {
  name: 'ShoppingView',
  components: {ShelfItemsGrid, LeftMenu, TopBar},
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
