<template>
  <div class="text-center">
    <div class="row no-gutters" style="background: #161616">
      <div class="col-sm-12 content-wrap">
        <div class="row">
          <div class="col-sm-8 offset-sm-2">
            <div class="dev-dash-wrap">
              <div class="no-gutters">
                <div class="collection_wrapper">
                  <v-overlay value="true">
                    <v-progress-circular
                      indeterminate
                      color="deep-purple accent-4"
                      size="64"
                    ></v-progress-circular>
                  </v-overlay>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  layout: 'loadding',
  async created() {
    if (this.$route.hash) {
      const hash = await this.$route.hash.split('#')[1]
      this.$store.dispatch(this.$constant.user.ACTIONS_USER_LOGINFACEBOOK, hash)
    } else {
      this.$router.push({ name: 'index' })
    }
  },
  async mounted() {
    await this.$nextTick(() => {
      this.$nuxt.$loading.start()
    })
  },
}
</script>

<style></style>
