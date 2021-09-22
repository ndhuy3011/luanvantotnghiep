<template>
  <div>
    <div>
      <b-carousel
        id="carousel"
        style="text-shadow: 0px 0px 2px #000"
        :interval="2000"
        fade
        indicators
        img-width="1024"
        img-height="450"
      >
        <div v-for="(item, index) in carousel" :key="index">
          <b-carousel-slide id="content-index" :img-src="item.file.url">
          </b-carousel-slide>
        </div>
      </b-carousel>
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    listbanner() {
      return this.$store.state.admin.banner.banner
    },
    carousel() {
      return this.listbanner.filter((item) => {
        return item.sort === 1
      })
    },
  },
}
</script>

<style lang="scss" scoped>
#carousel {
  margin-top: -6%;
}
@media screen and (max-width: 900px) {
  #carousel {
    margin-top: -5%;
  }
}
</style>
