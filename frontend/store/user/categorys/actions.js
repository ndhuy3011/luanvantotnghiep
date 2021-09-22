import constants from './constants'

export default {
  async getAll({ commit }) {
    const categorys = await this.$repositories.categoryUser.all()
    const select = await this.$repositories.categoryUser.show('categorynull')
    const payload = {
      categorys: categorys.data,
      select: select.data,
    }
    commit(constants.MUTATIONS_CATEGORYS_GETALL, payload)
  },
}
