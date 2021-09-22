export default {
  MUTATIONS_CATEGORYS_GETALL(state, payload) {
    state.categorys = payload.categorys
    state.select = payload.select
    payload.categorys.forEach((item) => {
      if (item.name === this.$local.vn.category_vuex[0]) {
        state.category_women = item
      } else if (item.name === this.$local.vn.category_vuex[1]) {
        state.category_men = item
      } else if (item.name === this.$local.vn.category_vuex[2]) {
        state.sale = item
      }
    })
  },
}
