<template>
  <router-link :to="{ name: 'cart' }">
    <v-btn id="cart" icon>
      <b-icon icon="handbag"></b-icon>
      <span>{{ amount }}</span>
    </v-btn>
  </router-link>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'ButtonCart',
  computed: {
    ...mapGetters({
      amount: 'user/cart/amount',
    }),
  },
}
</script>

<style lang="scss" scoped>
#cart {
  font-size: 1.2rem;
  margin-right: 10px;
  span {
    position: absolute;
    margin-top: -30px;
    margin-left: 25px;
    height: 20px;
    width: 20px;
    background: #ec410d;
    color: #ffffff;
    border-radius: 50%;
    font-size: 10px;
    font-weight: 650;
    text-align: center;
    line-height: 15px;
    padding-top: 2px;
  }
}
</style>
