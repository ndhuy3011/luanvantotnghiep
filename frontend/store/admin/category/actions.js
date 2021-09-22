export default {
  async init({ commit }) {
    const category = await this.$repositories.categoryAdmin.all()
    const select = await this.$repositories.categoryAdmin.show('categorynull')
    const payload = {
      category: category.data,
      select: select.data,
    }
    commit(this.$constant.admin.MUTATION_ADMIN_INIT, payload)
  },
  async addCategory({ commit }, item) {
    this.$toast.global.loading()
    const newCategory = {
      name: item.name,
      sort: item.sort,
      category: item.category,
    }

    try {
      const res = await this.$repositories.categoryAdmin.create(newCategory)
      const { status, data } = res
      if (status === 201) {
        commit(this.$constant.admin.MUTATION_ADMIN_CATEGORY_ADD_CONTENT, data)
        this.$toast.global.success()
        return true
      }
    } catch (e) {
      return false
    }
  },
  async updataCategory({ commit }, item) {
    this.$toast.global.loading()
    const category = {
      name: item.name,
      sort: item.sort,
      category: item.category,
    }
    try {
      const res = await this.$repositories.categoryAdmin.update(
        item.id,
        category
      )
      const { status, data } = res
      if (status === 200) {
        this.$toast.global.success()
        commit(
          this.$constant.admin.MUTATION_ADMIN_CATEGORY_UPDATE_CONTENT,
          data
        )
        return true
      }
    } catch (e) {
      return false
    }
  },
  async deleteCategory({ commit }, item) {
    this.$toast.global.loading()
    try {
      const res = await this.$repositories.categoryAdmin.delete(item.id)
      const { status } = res
      if (status === 200) {
        this.$toast.global.success()
        commit(
          this.$constant.admin.MUTATION_ADMIN_CATEGORY_DELETE_CONTENT,
          item
        )
        return true
      }
    } catch (e) {
      return false
    }
  },
}
