<template>
  <v-card>
    <v-layout>
      <v-navigation-drawer
          v-model="drawer"
          :rail="rail"
          permanent
          @click="rail = false"
          style="position: block;"
          color="background"
          elevation="8"
      >
        <v-list-item
            prepend-avatar="https://randomuser.me/api/portraits/men/85.jpg"
            title="Raul Urechiatu"
            nav
        >
          <template v-slot:append>
            <v-btn
                icon="mdi-chevron-left"
                variant="text"
                @click.stop="rail = !rail"
            ></v-btn>
          </template>
        </v-list-item>

        <v-divider></v-divider>

        <v-list density="compact" nav>
          <v-list-item prepend-icon="mdi-cart" title="Shopping cart" value="shopping"
                       @click="goToShopping()" color="secondary"></v-list-item>
          <v-list-item prepend-icon="mdi-warehouse" title="Created items" value="shelf"
                       @click="goToShelf()" color="secondary"></v-list-item>
          <v-list-item prepend-icon="mdi-cog" title="Settings" value="settings"
                       color="secondary"></v-list-item>
        </v-list>
      </v-navigation-drawer>
      <v-main style="height: 250px"></v-main>
    </v-layout>
  </v-card>
</template>

<script>
import router from "@/router";

export default {
  name: "LeftMenu",
  data () {
    return {
      drawer: true,
      rail: true,
    }
  },
  methods: {
    goToShopping() {
      router.push("shopping");
    },
    goToShelf() {
      router.push("shelf");
    },
  }
}
</script>

<style scoped>

</style>