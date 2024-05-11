<template>
  <div class="content-container">
    <v-card flat
            style="min-width: 520px; width: 50%;">
      <v-card-title class="d-flex align-center pe-2">
        <v-icon icon="mdi-cart"></v-icon> &nbsp;
        Lista de cumparaturi

        <v-spacer></v-spacer>
<!--        <v-btn icon="mdi-plus" size="small" style="margin-left: 10px;" @click="openAdd()"></v-btn>-->
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

      <v-divider></v-divider>

      <div id="shelf-grid">
        <v-card
          v-for="item in shoppingItems"
          :key="item"
        @click="deleteItem(item)">
<!--        <div style="display: flex; line-break: anywhere;">-->
<!--            <v-btn size="x-small" icon="mdi-pencil" elevation="0"></v-btn>-->
            <v-img v-if="!item.shelfItem.icon" :src="require('@/assets/images/generic.png')" width="40px" style="margin-left: calc(50% - 20px); margin-top: 10px;"></v-img>
            <v-img v-if="item.shelfItem.icon" :src="require('@/assets/images/' + item.shelfItem.icon + '.png')" width="40px" style="margin-left: calc(50% - 20px); margin-top: 10px;"></v-img>
          <v-menu>
            <template v-slot:activator="{ props }">
              <v-btn icon="mdi-dots-vertical" v-bind="props" style="position: absolute; right: 0; top: 0;" size="x-small" elevation="0"></v-btn>
            </template>
            <v-list>
              <v-list-item @click="deleteItem(item)">
                <v-list-item-title>Sterge</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
          <v-card-text style="font-size: 16px; font-weight: 500; padding: 10px 0;">{{item.shelfItem.name}}</v-card-text>
        </v-card>
      </div>
    </v-card>
  </div>
</template>

<script>
import WebsocketService from '@/services/WebsocketService'
import ShoppingItemService, {shoppingData} from "@/services/ShoppingItemService";

export default {
  name: 'ShoppingItemsGrid',
  props: {
    msg: String
  },
  computed: {
    shoppingItems() {
      console.log(shoppingData.value.shoppingItems);
      return shoppingData.value.shoppingItems;
    },
    headers() {
      return [
        {
          title: "",
          align: 'center',
          key: 'icon'
        },
        {
          title: "Produs",
          align: 'center',
          key: 'name'
        },
        {
          title: "Categorie",
          align: 'center',
          key: 'category'
        },
        {
          title: "Actiuni",
          align: 'center',
          key: 'actions'
        },
      ]
    }
  },
  data () {
    return {
      search: '',
      images: [],
      selectedIconIndex: -1,
      deleteDialog: false,
      editDialog: false,
      isAdd: false,
      editItem: null,
    }
  },
  mounted() {
    WebsocketService.init();
    ShoppingItemService.getAll();
    this.importAll(require.context('../assets/images/', true, /\.png$/));
  },
  methods: {
    importAll(imgs) {
      imgs.keys().forEach(key => (this.images.push({ pathLong: imgs(key), pathShort: key })));
    },
    getImage(itemIcon) {
      return this.images.find(img => img.pathShort.includes(itemIcon));
    },
    selectIcon(image) {
      if (!image) return;
      let index = this.images.indexOf(image);
      this.selectedIconIndex = index;
      if (index === -1) return;
      let selectedItems = document.getElementsByClassName('selectedIcon');
      for (let i = 0; i < selectedItems.length; i++) {
        if (selectedItems.length < 1) break;
        selectedItems[i].classList.remove("selectedIcon");
      }
      let element = document.getElementById("icon-" + index);
      if (element && element.classList) {
        element.classList.add('selectedIcon');
        this.editItem.icon = image.pathShort.replace("./", "").replace(".png", "");
      }
    },
    deleteItem(item) {
      ShoppingItemService.delete(item.id);
    }
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

#shelf-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, 120px);
  border: lightgray 1px solid;
  border-radius: 5px;
  grid-column-gap: 5px;
  grid-row-gap: 5px;
  /*margin: 5px 5px 10px;*/
  max-height: 300px;
  overflow-y: scroll;
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
