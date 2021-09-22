import constants from './constants'

export default {
  async getAll({ commit }) {
    const listproduct = await this.$repositories.productUser.all()
    const hot = await this.$repositories.productUser.hot()
    const payload = {
      listproduct: listproduct.data,
      hot: hot.data,
    }
    commit(constants.MUTATIONS_PRODUCT_GETALL, payload)
  },

  getProductCategory({ commit }, payload) {
    this.$router.push({ name: 'category' })
    commit(constants.MUTATIONS_PC_GET, payload)
  },
}
