<template>
  <div class="d-flex justify-content-around">
    <b-button class="btn" type="submit">{{ text }} </b-button>
    <b-button
      v-show="text === $local.vn.login_title"
      class="btn"
      @click="loginFacebook()"
    >
      <img :src="require('@/assets/logo/facebook.png')" class="img" />Facebook
    </b-button>
  </div>
</template>

<script>
export default {
  props: {
    text: {
      type: String,
      default: '',
    },
  },
  methods: {
    async loginFacebook() {
      localStorage.removeItem('cart')
      await this.$auth.loginWith('facebook')
    },
  },
}
</script>

<style lang="scss" scoped>
.btn {
  display: block;
  height: 38px;
  width: 50%;
  border-radius: 20px;
  outline: none;
  border: none;
  background: rgb(241, 16, 16);
  color: white;
  background-size: 200%;
  text-transform: uppercase;
  margin: 0.5rem 0;
  cursor: pointer;
  font-size: 16px;
  transition: 0.5s;
}
.btn:nth-child(2) {
  background: rgb(49, 96, 252);
  color: white;
  margin-left: 50px;
}
.btn:hover {
  background: rgb(110, 110, 110);
}
.img {
  width: 20px;
  height: 20px;
  margin: 5px;
}
@media screen and (max-width: 600px) {
  .btn {
    font-size: 10px;
  }
}
</style>
