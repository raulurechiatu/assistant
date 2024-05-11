<template>
  <top-bar></top-bar>
  <v-container id="shopping-content" class="content-page-container">
    <v-row>
      <left-menu></left-menu>
      <div>
        <shopping-items-grid></shopping-items-grid>
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
import ShoppingItemsGrid from "@/components/ShoppingItemsGrid";

export default {
  name: 'ShoppingView',
  components: {ShoppingItemsGrid, ShelfItemsGrid, LeftMenu, TopBar},
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
