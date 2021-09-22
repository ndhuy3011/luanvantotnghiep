export default {
  async addImage({ dispatch }, item) {
    this.$toast.global.loading()
    let res = null
    const payload = {
      image: item.image,
    }
    try {
      if (item.type + '' === 'product') {
        res = await this.$repositories.productAdmin.updateImage(
          item.id,
          payload
        )
      }
      if (item.type + '' === 'color') {
        res = await this.$repositories.detailsproductAdmin.updateImage(
          item.id,
          payload
        )
      }
      if (res === null) {
        return
      }
      const { status } = res
      if (status === 200) {
        this.$toast.global.success()
      }
    } catch (e) {
      this.$toast.global.error()
    }
  },
}
