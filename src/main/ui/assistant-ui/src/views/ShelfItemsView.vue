<template>
  <top-bar></top-bar>
  <v-container id="shelf-content" class="content-page-container">
    <v-row>
      <left-menu></left-menu>
      <shelf-items-table></shelf-items-table>
    </v-row>
  </v-container>
</template>

<script>
import TopBar from "@/components/TopBar";
import LeftMenu from "@/components/LeftMenu";
import ShelfItemsTable from "@/components/ShelfItemsTable";

export default {
  name: 'ShelfItemsView',
  components: {ShelfItemsTable, LeftMenu, TopBar},
  props: {
    msg: String
  },
  computed: {
  },
  data () {
    return {
    }
  },
  mounted() {
  },
  methods: {

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

.images-container {
  display: grid;
  grid-template-columns: repeat(auto-fill, 38px);
  border: lightgray 1px solid;
  border-radius: 5px;
  /*grid-column-gap: 5px;*/
  /*grid-row-gap: 5px;*/
  /*margin: 5px 5px 10px;*/
}

.selectedIcon {
  border: 1px solid rgba(44,62,80,0.5);
  border-radius: 5px;
  background: rgba(44,62,80,0.3);

}

.itemIcon {
  padding: 5px;
}


</style>
