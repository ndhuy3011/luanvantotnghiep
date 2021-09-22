<template>
  <v-app id="inspire">
    <v-container fill-height>
      <v-layout align-center justify-center>
        <v-flex xs12 sm8 md4>
          <v-form
            ref="form"
            v-model="valid"
            lazy-validation
            @submit.prevent="submit"
          >
            <v-card class="elevation-12">
              <v-toolbar dark color="primary">
                <v-toolbar-title> {{ $local.vn_admin.login }}</v-toolbar-title>
              </v-toolbar>
              <v-card-text>
                <v-alert v-show="alter" color="red" outlined text type="error"
                  >{{ msg }}
                </v-alert>
                <v-text-field
                  v-model="email"
                  :label="$local.vn_admin_general.EMAIL"
                  type="email"
                  :rules="$rule.EMAIL_LOGIN"
                  required
                ></v-text-field>
                <v-text-field
                  v-model="password"
                  :label="$local.vn_admin_general.PASSWORD"
                  type="password"
                  :rules="$rule.PASSWORD_LOGIN"
                  required
                ></v-text-field>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" type="submit" @click="validate">{{
                  $local.vn_admin.login
                }}</v-btn>
              </v-card-actions>
            </v-card>
          </v-form>
        </v-flex>
      </v-layout>
    </v-container>
  </v-app>
</template>

<script>
export default {
  layout: 'loginAdmin',
  middleware: 'isauthenticated',
  data: () => ({
    alter: false,
    email: '',
    password: '',
    valid: false,
    msg: '',
  }),
  methods: {
    validate() {
      this.$refs.form.validate()
    },
    async submit(e) {
      e.preventDefault()
      let flap = null
      if (!this.valid) return
      const form = { email: this.email, password: this.password }
      flap = await this.$store.dispatch(
        this.$constant.admin.ACTION_ADMIN_LOGIN,
        form
      )
      if (flap === 404) {
        this.msg = 'Vui lòng bạn kiểm tra lại email hoặc mật khâu'
        this.alter = true
      } else if (this.$auth.user.role.length < 1) {
        this.msg = 'Bạn không có quyền truy cập'
        this.alter = true
      } else if (flap === true) {
        this.$router.push({ name: 'admin' })
      }
    },
  },
}
</script>
<style lang="scss" scoped></style>
