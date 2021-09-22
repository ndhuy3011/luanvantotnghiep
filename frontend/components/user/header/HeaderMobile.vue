<template>
  <div id="navigation-mobile">
    <v-responsive max-width="500" class="search">
      <v-text-field
        v-model="search"
        background-color="grey lighten-5"
        dense
        flat
        hide-details
        rounded
        solo
        append-icon="mdi-magnify"
        :label="$local.vn.sreach"
        @input="keysearch()"
      >
      </v-text-field>
    </v-responsive>
    <ul>
      <li v-for="(menu, index) in $local.vn.menu_header" :key="index">
        <router-link :to="{ name: menu.url }" class="link">{{
          menu.content
        }}</router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      search: '',
    }
  },
  methods: {
    keysearch() {
      this.$router.push({ name: 'shop' })
      this.$store.dispatch(this.$constant.user.ACTIONS_SEARCH, this.search)
    },
  },
}
</script>

<style lang="scss" scoped>
#navigation-mobile {
  padding: 20px 0 0 0;
  ul li {
    list-style: none;
    margin-top: 20px;
    margin-left: 30px;
    a {
      font-size: 16px;
      color: black;
      text-decoration: none;
      transition: all 0.3s;
    }
    a:hover {
      color: #ce785c;
    }
    .link::after {
      content: '';
      width: 0%;
      height: 1px;
      display: block;
      background-color: black;
      transition: width 0.3s;
    }
    .link:hover::after {
      width: 50%;
      transition: width 0.3s;
    }
  }
  .search {
    position: relative;
    width: 250px;
    margin-bottom: 20px;
    padding-left: 20px;
  }
  #cart {
    font-size: larger;
    span {
      position: absolute;
      margin-top: -25px;
      margin-left: 25px;
      height: 15px;
      width: 15px;
      background: #ec410d;
      color: #ffffff;
      border-radius: 50%;
      font-size: 11px;
      font-weight: 650;
      text-align: center;
      line-height: 15px;
    }
  }
}
</style>
