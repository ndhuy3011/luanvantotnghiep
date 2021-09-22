<template>
  <v-app-bar app color="white" flat>
    <ul>
      <li
        v-for="(menu, index) in menu_header"
        :key="index"
        @click="activeMemu(index)"
      >
        <router-link
          :id="menu.active ? 'active' : ''"
          :to="{ name: menu.url }"
          class="link"
          >{{ menu.content }}</router-link
        >
      </li>
    </ul>

    <v-spacer></v-spacer>
    <v-responsive max-width="500">
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
    <BtnCart />
    <MenuAccount />
  </v-app-bar>
</template>

<script>
import MenuAccount from '../MenuAccount.vue'
import BtnCart from '../../BtnCart'
export default {
  components: { MenuAccount, BtnCart },
  data() {
    return {
      menu_header: [
        {
          content: 'Trang chủ',
          url: 'index',
          active: false,
        },
        {
          content: 'Sản phẩm',
          url: 'shop',
          active: false,
        },
        {
          content: 'Liên hệ',
          url: 'contact',
          active: false,
        },
        {
          content: 'Thông tin',
          url: 'news',
          active: false,
        },
      ],
      activeValue: '',
      search: '',
    }
  },

  methods: {
    activeMemu(i) {
      this.menu_header.forEach((item, index) => {
        if (i === index) {
          this.activeValue = index
          item.active = true
        } else {
          item.active = false
        }
      })
    },
    keysearch() {
      this.$router.push({ name: 'shop' })
      this.$store.dispatch(this.$constant.user.ACTIONS_SEARCH, this.search)
    },
  },
}
</script>

<style lang="scss" scoped>
ul li {
  list-style: none;
  margin-top: 20px;
  margin-left: 20px;
  display: inline-block;
  a {
    font-size: 21px;
    color: black;
    text-decoration: none;
    transition: all 0.3s;
  }
  #active {
    color: #ce785c;
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
    width: 100%;
    transition: width 0.3s;
  }
}
</style>
