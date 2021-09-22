<template>
  <v-app-bar
    absolute
    color="white"
    elevate-on-scroll
    scroll-target="#scrolling-techniques-7"
    app
  >
    <v-app-bar-nav-icon @click="$emit('action')"></v-app-bar-nav-icon>
    <v-toolbar-title>{{ $local.vn_admin.admin }}</v-toolbar-title>
    <v-spacer></v-spacer>
    <MenuAccount></MenuAccount>
  </v-app-bar>
</template>

<script>
import MenuAccount from '~/components/user/MenuAccount.vue'
export default {
  components: { MenuAccount },
}
</script>

<style></style>
