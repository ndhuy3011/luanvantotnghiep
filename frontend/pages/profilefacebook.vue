<template>
  <v-container>
    <v-row>
      <v-col id="title">
        <p>{{ $local.vn.register_title }}</p>
      </v-col>
      <v-col md="7">
        <v-form v-if="show" class="form" @submit.prevent="register">
          <v-form-group>
            <v-text-field
              id="email"
              v-model="form.email"
              type="email"
              label="Email"
              required
            ></v-text-field>
          </v-form-group>
          <v-form-group>
            <v-text-field
              id="address"
              v-model="form.address"
              type="text"
              :label="$local.vn.address"
              required
            ></v-text-field>
          </v-form-group>

          <v-form-group>
            <v-text-field
              id="numberPhone"
              v-model="form.numberPhone"
              type="number"
              :label="$local.vn.numberPhone"
              required
            ></v-text-field>
          </v-form-group>
          <Button text="xác nhận thông tin" />
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import Button from '../components/Button'
export default {
  components: { Button },
  data() {
    return {
      form: {
        name: '',
        address: '',
        numberPhone: '',
      },
      show: true,
      isActive: false,
    }
  },
  methods: {
    register() {
      this.form.accessToken = this.$route.query.access_token
      this.form.name = this.$route.query.name
      this.$store.dispatch(
        this.$constant.user.ACTIONS_USER_REGISTERFACEBOOk,
        this.form
      )
    },
  },
}
</script>
<style lang="scss" scope>
.register {
  #title {
    font-size: 60px;
    justify-content: center;
  }
  .mes_error {
    font-size: 15px;
    color: red;
  }
  .form {
    position: relative;
    .form-group {
      input {
        font-size: 18px;
        padding: 20px;
        display: block;
        width: 100%;
        border: solid 1px #f3f3f3;
        background-color: #fff;
      }
    }
  }
}
@media screen and (max-width: 600px) {
  .register {
    #title {
      margin-top: 0px;
      font-size: 40px;
    }
  }
}
</style>
