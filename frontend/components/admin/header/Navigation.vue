<template>
  <div>
    <v-navigation-drawer v-model="drawer" app>
      <!--  -->
      <v-list shaped>
        <v-list-item
          color="deep-orange lighten-1"
          @click="$router.push({ name: 'admin' })"
        >
          <v-list-item-icon>
            <v-icon>mdi-home</v-icon>
          </v-list-item-icon>
          <v-list-item-title>{{ $local.vn_admin.home }}</v-list-item-title>
        </v-list-item>

        <v-list-group
          v-for="(item, index) in menu"
          :key="index"
          color="deep-orange lighten-1"
        >
          <template #activator>
            <v-list-item-title>{{ item.name }}</v-list-item-title>
          </template>

          <v-list-group
            v-for="(itemFunction, indexFunction) in item.function"
            :key="indexFunction"
            no-action
            sub-group
            color="deep-orange lighten-2"
          >
            <template #activator>
              <v-list-item-content>
                <v-list-item-title>{{ itemFunction.name }}</v-list-item-title>
              </v-list-item-content>
            </template>

            <v-list-item
              v-for="(childFunctions, childIndex) in itemFunction.function"
              :key="childIndex"
              link
              color="deep-orange lighten-3"
              @click="$router.push({ name: childFunctions.url })"
            >
              <v-list-item-title
                v-text="childFunctions.name"
              ></v-list-item-title>
            </v-list-item>
          </v-list-group>
        </v-list-group>
      </v-list>
    </v-navigation-drawer>
    <AppBar @action="action"></AppBar>
  </div>
</template>

<script>
import AppBar from './AppBar.vue'
export default {
  components: { AppBar },
  props: {
    menu: { type: Array, default: null },
  },
  data: () => ({ drawer: true }),
  methods: {
    action() {
      this.drawer = !this.drawer
    },
  },
}
</script>

<style></style>
