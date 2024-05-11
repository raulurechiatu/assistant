<template>
  <div class="content-container">
    <v-card flat
            style="min-width: 520px; width: 50%;">
      <v-card-title class="d-flex align-center pe-2">
        <v-icon icon="mdi-warehouse"></v-icon> &nbsp;
        Produsele introduse

        <v-spacer></v-spacer>
        <v-btn icon="mdi-plus" size="small" style="margin-left: 10px;" @click="openAdd()"></v-btn>
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
          v-for="item in shelfItems"
          :key="item"
        @click="addShelfItemToShopping(item)">
<!--        <div style="display: flex; line-break: anywhere;">-->
<!--            <v-btn size="x-small" icon="mdi-pencil" elevation="0"></v-btn>-->
            <v-img v-if="!item.icon" :src="require('@/assets/images/generic.png')" width="40px" style="margin-left: calc(50% - 20px); margin-top: 10px;"></v-img>
            <v-img v-if="item.icon" :src="require('@/assets/images/' + item.icon + '.png')" width="40px" style="margin-left: calc(50% - 20px); margin-top: 10px;"></v-img>
          <v-menu>
            <template v-slot:activator="{ props }">
              <v-btn icon="mdi-dots-vertical" v-bind="props" style="position: absolute; right: 0; top: 0;" size="x-small" elevation="0"></v-btn>
            </template>
            <v-list>
              <v-list-item @click="openEdit(item)">
                <v-list-item-title>Editeaza</v-list-item-title>
              </v-list-item>
              <v-list-item @click="openDelete(item)">
                <v-list-item-title>Sterge</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
<!--            <v-btn size="x-small" icon="mdi-delete" elevation="0"></v-btn>-->
<!--        </div>-->
          <v-card-text style="font-size: 16px; font-weight: 500; padding: 10px 0;">{{item.name}}</v-card-text>
        </v-card>
      </div>

<!--      <v-data-table-->
<!--      :items="shelfItems"-->
<!--      :headers="headers"-->
<!--      :search="search"-->
<!--      items-per-page="25"-->
<!--      density="compact">-->

<!--        <template v-slot:[`item.icon`]="{item}">-->
<!--          <v-img v-if="!item.icon" :src="require('@/assets/images/generic.png')" width="32px"></v-img>-->
<!--          <v-img v-if="item.icon" :src="require('@/assets/images/' + item.icon + '.png')" width="32px"></v-img>-->
<!--        </template>-->
<!--        <template v-slot:[`item.actions`]="{item}">-->
<!--          <v-btn icon="mdi-pencil" size="x-small" @click="openEdit(item)" style="margin-right: 5px;"></v-btn>-->
<!--          <v-btn icon="mdi-delete" size="x-small" @click="openDelete(item)"></v-btn>-->
<!--        </template>-->

<!--      </v-data-table>-->
    </v-card>
  </div>

  <v-dialog
    :model-value="editDialog">
    <v-card
        class="mx-auto my-8"
        elevation="16"
        max-width="80%"
        width="400px"
    >
      <v-card-item>
        <v-card-title style="white-space: normal; margin-bottom: 10px;">
          {{ isAdd ? "Adaugati un produs" : ("Editati produsul " + editItem.name) }}
        </v-card-title>
        <v-form>
          <v-text-field
              v-model="editItem.name"
              label="Nume produs *"
              hide-details density="compact"
              required style="margin-bottom: 10px;"
          ></v-text-field>
          <v-combobox
              label="Categorie *"
              v-model="editItem.category"
              :items="itemsCategory"
              required style="margin-bottom: 10px;"
              density="compact"
          ></v-combobox>
          <span>Icon</span>
          <div class="images-container">
            <div style="width: 40px; height: 40px;"
                 v-for="image in images"
                 :key="image"
                 :class="selectedIconIndex == images.indexOf(image) ? 'selectedIcon itemIcon' : 'itemIcon'"
                 :id="'icon-' + images.indexOf(image)"
                 @click="selectIcon(image)">
              <v-img
                  :src="image.pathLong"
                  width="32px"
              >
              </v-img>
            </div>
          </div>
        </v-form>
      </v-card-item>

      <v-card-actions>
        <v-btn text="Renunta" @click="editDialog = false;"></v-btn>
        <v-spacer></v-spacer>
        <v-btn :text="isAdd ? 'Adauga' : 'Editeaza'" color="success" @click="isAdd ? addShelfItem() : editShelfItem()"></v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>

  <v-dialog
    :model-value="deleteDialog">
    <v-card
        class="mx-auto my-8"
        elevation="16"
        max-width="344"
    >
      <v-card-item>
        <v-card-title style="white-space: normal; margin-bottom: 10px;">
          Sunteti sigur ca doriti sa stergeti acest produs?
        </v-card-title>
        <v-card-subtitle>
          Aceasta actiune este ireversibila!
        </v-card-subtitle>
      </v-card-item>

      <v-card-actions>
        <v-btn text="Nu" @click="deleteDialog = false;"></v-btn>
        <v-spacer></v-spacer>
        <v-btn text="Da" color="error" @click="deleteShelfItem()"></v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import ShelfItemService from "@/services/ShelfItemService";
import { shelfData } from "@/services/ShelfItemService";
import WebsocketService from '@/services/WebsocketService'
import ShoppingItemService from "@/services/ShoppingItemService";

export default {
  name: 'ShelfItemsGrid',
  props: {
    msg: String
  },
  computed: {
    shelfItems() {
      return shelfData.value.shelfItems.filter(item => {
        if (!item.name || !item.icon || !item.category) return false;
        return !this.search || this.search.length < 1 ||
            item.name.includes(this.search)  ||
            item.category.includes(this.search)  ||
            item.icon.includes(this.search)
      });
    },
    itemsCategory() {
      return ShelfItemService.getCategories();
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
      deleteItem: null,
      editItem: null,
    }
  },
  mounted() {
    WebsocketService.init();
    ShelfItemService.getAll();
    this.importAll(require.context('../assets/images/', true, /\.png$/));
  },
  methods: {
    importAll(imgs) {
      imgs.keys().forEach(key => (this.images.push({ pathLong: imgs(key), pathShort: key })));
    },
    openAdd() {
      this.editItem = {};
      this.editDialog = true;
      this.isAdd = true;
    },
    addShelfItem() {
      this.editDialog = false;
      if (!this.editItem) return;
      ShelfItemService.add(this.editItem);
    },
    openEdit(item) {
      this.editItem = {...item};
      if (!item || !item.icon) item.icon = 'generic';
      this.selectedIconIndex = this.images.indexOf(this.getImage(item.icon))
      this.editDialog = true;
      this.isAdd = false;
      // this.selectIcon(this.getImage(item.icon));
    },
    editShelfItem() {
      // console.log(this.editItem);
      this.editDialog = false;
      if (!this.editItem || !this.editItem.id) return;
      ShelfItemService.edit(this.editItem);
    },
    openDelete(item) {
      this.deleteItem = {...item};
      this.deleteDialog = true;
    },
    deleteShelfItem() {
      this.deleteDialog = false;
      if (!this.deleteItem || !this.deleteItem.id) return;
      console.log("Deleting shelf item " + this.deleteItem.id);
      ShelfItemService.delete(this.deleteItem.id);
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
    addShelfItemToShopping(item) {
      ShoppingItemService.add(item);
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
