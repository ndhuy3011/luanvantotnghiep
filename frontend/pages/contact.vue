<template>
  <v-container fluid>
    <v-row>
      <v-col cols="12" class="hidden-sm-and-down" md="6">
        <Map :map="info.linkAddress" />
      </v-col>
      <v-col cols="12" md="6">
        <Bubble1 style="transform: rotate(180deg) translateY(25%)" />
        <h3 align="center" justify="center">{{ info.name }} Xin chào!!</h3>
        <h5 align="center" justify="center">SĐT: {{ info.numberphone }}</h5>
        <h5 align="center" justify="center">Facebook: {{ info.facebook }}</h5>
        <h5 align="center" justify="center">Địa chỉ: {{ info.address }}</h5>
        <Bubble2 style="transform: rotate(180deg) translate(-200px, -15%)" />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import Map from '../components/user/Map.vue'
import Bubble1 from '../components/Bubble1'
import Bubble2 from '../components/Bubble2'
export default {
  components: { Map, Bubble1, Bubble2 },
  computed: {
    info() {
      return this.$store.state.admin.infoweb.info
    },
  },
}
</script>
<style lang="scss" scoped></style>
