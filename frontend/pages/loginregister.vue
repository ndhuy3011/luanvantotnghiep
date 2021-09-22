<template>
  <b-container class="login">
    <b-alert :show="message != ''" variant="danger">{{ message }}</b-alert>
    <b-row>
      <!-- Register -->
      <b-col cols="12" md="6">
        <h4 class="d-flex justify-content-center">
          {{ $local.vn.register_title }}
        </h4>
        <b-form v-if="show" class="form" @submit.prevent="register(form)">
          <b-form-group>
            <b-form-input
              id="email"
              v-model="form.email"
              type="email"
              :state="
                /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(form.email)
                  ? true
                  : false
              "
              placeholder="Email"
              required
            ></b-form-input>
          </b-form-group>
          <b-form-group>
            <b-form-input
              id="name"
              v-model="form.name"
              type="text"
              :state="form.name.length >= 6"
              :placeholder="$local.vn.user_name"
              required
            ></b-form-input>
          </b-form-group>
          <b-form-group>
            <b-form-input
              id="uername"
              v-model="form.userName"
              type="text"
              :state="
                form.userName.length >= 5 && form.userName.includes(' ') <= 0
              "
              :placeholder="$local.vn.account_name"
              required
            ></b-form-input>
          </b-form-group>
          <b-form-group>
            <b-form-input
              id="address"
              v-model="form.address"
              type="text"
              :placeholder="$local.vn.address"
              required
            ></b-form-input>
          </b-form-group>

          <b-form-group>
            <b-form-input
              id="numberPhone"
              v-model="form.numberPhone"
              type="number"
              :state="
                form.numberPhone.length >= 10 && form.numberPhone.length <= 11
              "
              :placeholder="$local.vn.numberPhone"
              required
            ></b-form-input>
          </b-form-group>
          <b-form-group>
            <b-form-input
              id="password"
              v-model.trim="$v.form.password.$model"
              :state="form.password.length >= 6"
              type="password"
              :placeholder="$local.vn.password"
              required
            ></b-form-input>
          </b-form-group>

          <div v-if="!$v.form.password.minLength" class="mes_error">
            {{ $local.vn.err_password[0] }}
            {{ $v.form.password.$params.minLength.min }}
            {{ $local.vn.err_password[1] }}
          </div>

          <b-form-group>
            <b-form-input
              id="repeatPassword"
              v-model.trim="$v.form.repeatPassword.$model"
              :state="form.repeatPassword === form.password"
              type="password"
              :placeholder="$local.vn.repeatPassword"
              required
            ></b-form-input>
          </b-form-group>

          <div v-if="!$v.form.repeatPassword.sameAsPassword" class="mes_error">
            {{ $local.vn.err_password[2] }}
          </div>

          <Button :text="$local.vn.register_title" />
        </b-form>
      </b-col>
      <!-- Login -->
      <b-col cols="12" md="6">
        <h4 class="d-flex justify-content-center">
          {{ $local.vn.login_title }}
        </h4>
        <b-form v-if="show" class="form" @submit="onSubmit" @reset="onReset">
          <b-form-group>
            <b-form-input
              id="email"
              v-model="login.email"
              type="email"
              :state="
                /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(form.email)
                  ? true
                  : false
              "
              placeholder="Email"
              required
            ></b-form-input>
          </b-form-group>

          <b-form-group>
            <b-form-input
              id="passwordlogin"
              v-model="login.password"
              type="password"
              :placeholder="$local.vn.password"
              required
            ></b-form-input>
          </b-form-group>

          <div class="d-flex">
            <div>
              <b-form-group id="input-group-4" v-slot="{ ariaDescribedby }">
                <b-form-checkbox-group
                  id="checkboxes"
                  v-model="form.checked"
                  :aria-describedby="ariaDescribedby"
                >
                  <b-form-checkbox value="remember">{{
                    $local.vn.remember_password
                  }}</b-form-checkbox>
                </b-form-checkbox-group>
              </b-form-group>
            </div>
            <div>
              <a href="#" class="forget_password">{{
                $local.vn.forget_password
              }}</a>
            </div>
          </div>
          <Button :text="$local.vn.login_title" />
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { sameAs, minLength } from 'vuelidate/lib/validators'
import Button from '../components/Button'
export default {
  name: 'Login',
  components: { Button },
  data() {
    return {
      login: {
        email: '',
        password: '',
      },
      form: {
        email: '',
        name: '',
        userName: '',
        address: '',
        numberPhone: '',
        password: '',
        repeatPassword: '',
      },
      message: '',
      isActive: false,
      show: true,
    }
  },
  validations: {
    form: {
      password: { minLength: minLength(6) },
      repeatPassword: { sameAsPassword: sameAs('password') },
    },
  },
  methods: {
    async onSubmit(event) {
      event.preventDefault()
      try {
        await this.$auth
          .loginWith('local', {
            data: {
              email: this.login.email,
              password: this.login.password,
            },
          })
          .then(() => {
            this.$router.push({ name: 'index' })
            localStorage.removeItem('cart')
            this.$store.dispatch(this.$constant.user.ACTION_REMOVE_CART_USER)
            this.$store.dispatch(this.$constant.user.ACTION_SHOW_CART_USER)
          })
      } catch (ex) {
        alert('Vui lòng kiểm tra lại thông tin')
      }
    },
    register(form) {
      this.$store.dispatch(this.$constant.user.ACTIONS_USER_REGISTER, form)
      this.onReset()
    },
    onReset(event) {
      this.form.email = ''
      this.form.name = ''
      this.form.userName = ''
      this.form.password = ''
      this.form.repeatPassword = ''
      this.numberPhone = ''

      // Trick to reset/clear native browser form validation state
      this.show = false
      this.$nextTick(() => {
        this.show = true
      })
    },
  },
}
</script>
<style lang="scss" scoped>
.login {
  #title {
    font-size: 20px;
    justify-content: center;
  }
  .form {
    position: relative;
    .form-group {
      input {
        font-size: 18px;
        padding: 20px;
        display: block;
        width: 100%;
        border: solid 1px #d3d3d3;
        background-color: #fff;
      }
    }
  }
  .forget_password:nth-child(2) {
    margin-left: 10px;
  }
  .forget_password:hover {
    color: red;
  }
  .mes_error {
    font-size: 15px;
    color: red;
  }
}
@media screen and (max-width: 600px) {
  .login {
    #title {
      margin-top: 0px;
      font-size: 40px;
    }
  }
}
</style>
