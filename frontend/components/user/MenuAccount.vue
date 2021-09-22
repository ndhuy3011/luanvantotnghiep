<template>
  <div class="acc text-center">
    <v-menu offset-y>
      <template #activator="{ on, attrs }">
        <v-btn v-bind="attrs" icon v-on="on">
          <b-icon id="person" icon="person-check"></b-icon>
        </v-btn>
        <article id="username">{{ user.name }}</article>
      </template>
      <v-list v-if="!user">
        <v-list-item
          v-for="(item, index) in $local.vn.memu_account"
          :key="index"
        >
          <v-list-item-title class="text-center">
            <router-link :to="{ name: item.url }" class="text">
              {{ item.title }}
            </router-link>
          </v-list-item-title>
        </v-list-item>
      </v-list>
      <v-list v-if="user">
        <v-list-item>
          <v-list-item-title class="text-center">
            <div class="mt-2">
              <router-link :to="{ name: 'profile' }" class="text">
                {{ $local.vn.profile_tille }}
              </router-link>
            </div>
            <div class="mt-5">
              <a class="text" @click="logout"> {{ $local.vn.logout_title }} </a>
            </div>
          </v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
  </div>
</template>

<script>
export default {
  computed: {
    user() {
      return this.$auth.user ? this.$auth.user : ''
    },
  },
  methods: {
    logout() {
      this.$auth.logout()
      this.$store.dispatch(this.$constant.user.ACTION_REMOVE_CART_USER)
    },
  },
}
</script>

<style lang="scss" scoped>
.acc {
  #person {
    font-size: 1.5rem;
  }
}
#username {
  display: inline-block;
  font-size: 14px;
}
a.text {
  color: rgb(0, 0, 0);
  text-decoration: none;
}
a:hover {
  color: #ce785c;
}
@media screen and (max-width: 500px) {
  #username {
    font-size: 10px;
  }
}
</style>
