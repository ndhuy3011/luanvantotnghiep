<template>
  <v-footer padless>
    <v-card class="flex" flat tile>
      <v-card-title>
        <v-row>
          <v-col cols="12" md="4" sm="5">
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title class="tit">{{
                  $local.vn.support_title
                }}</v-list-item-title>
                <v-list-item-subtitle
                  v-for="item in $local.vn.support"
                  :key="item"
                  class="select"
                  >{{ item }}</v-list-item-subtitle
                >
              </v-list-item-content>
            </v-list-item>
          </v-col>
          <v-col cols="12" md="5" sm="5">
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title class="tit">{{
                  $local.vn.service_tille
                }}</v-list-item-title>
                <v-list-item-subtitle
                  v-for="item in $local.vn.service"
                  :key="item"
                  class="select"
                  >{{ item }}</v-list-item-subtitle
                >
              </v-list-item-content>
            </v-list-item>
          </v-col>
          <v-col cols="12" md="3" sm="2">
            <v-btn v-for="icon in icons" :key="icon" class="mx-4 social" icon>
              <v-icon size="24px">
                {{ icon }}
              </v-icon>
            </v-btn>
          </v-col>
        </v-row>
      </v-card-title>

      <v-card-text class="py-2 black--text text-center">
        {{ new Date().getFullYear() }} —
        <strong>{{ $local.vn.shop }} — {{ $local.vn.nation }}</strong>
      </v-card-text>
    </v-card>
  </v-footer>
</template>

<script>
export default {
  data: () => ({
    icons: ['mdi-facebook', 'mdi-twitter', 'mdi-instagram'],
  }),
}
</script>

<style lang="scss" scoped>
.tit {
  font-size: 18px;
  font-weight: bold;
}
.select {
  cursor: pointer;
  font-size: 15px;
  margin-left: 10px;
}
.select:hover {
  color: red;
}
.social {
  cursor: pointer;
  color: black;
}
</style>
