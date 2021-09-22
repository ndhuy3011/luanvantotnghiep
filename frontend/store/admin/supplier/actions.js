export default {
  async init({ commit }) {
    const payload = await this.$repositories.supplierAdmin.all()
    commit(this.$constant.admin.MUTATION_ADMIN_SUPPLIER_INIT, payload)
  },
  async addSupplier({ commit }, item) {
    this.$toast.global.loading()
    const newSupplier = {
      name: item.name,
      number: item.number,
      address: item.address,
      website: item.website,
    }

    try {
      const res = await this.$repositories.supplierAdmin.create(newSupplier)
      const { status, data } = res
      if (status === 201) {
        commit(this.$constant.admin.MUTATION_ADMIN_SUPPLIER_ADD_CONTENT, data)
        this.$toast.global.success()
        return true
      }
    } catch (e) {
      return false
    }
  },
  async updataSupplier({ commit }, item) {
    this.$toast.global.loading()
    const supplier = {
      name: item.name,
      number: item.number,
      address: item.address,
      website: item.website,
    }
    try {
      const res = await this.$repositories.supplierAdmin.update(
        item.id,
        supplier
      )
      const { status, data } = res
      if (status === 200) {
        this.$toast.global.success()
        commit(
          this.$constant.admin.MUTATION_ADMIN_SUPPLIER_UPDATE_CONTENT,
          data
        )
        return true
      }
    } catch (e) {
      return false
    }
  },
  async deleteSupplier({ commit }, item) {
    this.$toast.global.loading()
    try {
      const res = await this.$repositories.supplierAdmin.delete(item.id)
      const { status } = res
      if (status === 200) {
        this.$toast.global.success()
        commit(
          this.$constant.admin.MUTATION_ADMIN_SUPPLIER_DELETE_CONTENT,
          item
        )
        return true
      }
    } catch (e) {
      return false
    }
  },
}
