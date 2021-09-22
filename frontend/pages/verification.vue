<template>
  <v-container fluid class="mt-10">
    <v-row>
      <v-col
        ><Bubble1 style="transform: rotate(180deg) translateY(25%)"
      /></v-col>
    </v-row>
    <v-row>
      <v-col>
        <div class="d-flex justify-content-center">
          <h2>Đã xác thực email</h2>
        </div>
      </v-col>
    </v-row>
    <v-row>
      <v-col
        ><Bubble2 style="transform: rotate(180deg) translate(-200px, -15%)"
      /></v-col>
    </v-row>
  </v-container>
</template>

<script>
import Bubble1 from '../components/Bubble1'
import Bubble2 from '../components/Bubble2'
export default {
  components: { Bubble1, Bubble2 },
  layout: 'enabled',
  mounted() {
    const token = this.$route.query.token
    this.$store.dispatch(this.$constant.user.ACTIONS_USER_ACTIVE, token)
  },
}
</script>

<style lang="scss" scoped>
.btn {
  display: block;
  height: 38px;
  width: 200px;
  border-radius: 20px;
  outline: none;
  border: none;
  background: rgb(0, 0, 0);
  color: white;
  background-size: 200%;
  text-transform: uppercase;
  margin: 0.5rem 0;
  cursor: pointer;
  font-size: 16px;
  transition: 0.5s;
}
.btn:hover {
  background: rgb(110, 110, 110);
}
#shopnow {
  width: 250px;
}
</style>
