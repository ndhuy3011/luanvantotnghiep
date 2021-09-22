export default {
  async init({ commit }) {
    const all = await this.$repositories.invoiceAdmin.all()
    commit(this.$constant.admin.MUTATION_ADMIN_INVOICE_INIT, all)
    const allNot = await this.$repositories.invoiceAdmin.allNot()
    commit(this.$constant.admin.MUTATION_ADMIN_INVOICE_NOT, allNot)
    const accuracy = await this.$repositories.invoiceAdmin.allAccuracy()
    commit(this.$constant.admin.MUTATION_ADMIN_INVOICE_ACCURACY, accuracy)
    const allTransport = await this.$repositories.invoiceAdmin.allTransport()
    commit(this.$constant.admin.MUTATION_ADMIN_INVOICE_TRANSPORT, allTransport)
    const complete = await this.$repositories.invoiceAdmin.allComplete()
    commit(this.$constant.admin.MUTATION_ADMIN_INVOICE_COMPLETE, complete)
    const returns = await this.$repositories.invoiceAdmin.allReturn()
    commit(this.$constant.admin.MUTATION_ADMIN_INVOICE_COMPLETE, returns)
  },
  async notData({ commit }) {
    const payload = await this.$repositories.invoiceAdmin.allNot()
    commit(this.$constant.admin.MUTATION_ADMIN_INVOICE_NOT, payload)
    return payload.data
  },
  async accuracyData({ commit }) {
    const payload = await this.$repositories.invoiceAdmin.allAccuracy()
    commit(this.$constant.admin.MUTATION_ADMIN_INVOICE_ACCURACY, payload)
    return payload.data
  },

  async transportData({ commit }) {
    const payload = await this.$repositories.invoiceAdmin.allTransport()
    commit(this.$constant.admin.MUTATION_ADMIN_INVOICE_TRANSPORT, payload)
    return payload.data
  },
  async completeData({ commit }) {
    const payload = await this.$repositories.invoiceAdmin.allComplete()
    commit(this.$constant.admin.MUTATION_ADMIN_INVOICE_CANCEL, payload)
    return payload.data
  },
  async cancelData({ commit }) {
    const payload = await this.$repositories.invoiceAdmin.allCancel()
    commit(this.$constant.admin.MUTATION_ADMIN_INVOICE_INIT, payload)
    return payload.data
  },
  async returnData({ commit }) {
    const payload = await this.$repositories.invoiceAdmin.allReturn()
    commit(this.$constant.admin.MUTATION_ADMIN_INVOICE_RETURN, payload)
    return payload.data
  },
  async submit({ commit }, payload) {
    this.$toast.global.loading()
    try {
      const res = await this.$repositories.invoiceAdmin.status(payload.id)
      const { status } = res
      if (status === 200) {
        this.$toast.global.success()
        return true
      }
    } catch (e) {
      this.$toast.global.errorSubmitInvoice()
      return false
    }
  },
  async del({ commit }, payload) {
    this.$toast.global.loading()
    try {
      const res = await this.$repositories.invoiceAdmin.del(payload.id)
      const { status } = res
      if (status === 200) {
        this.$toast.global.success()
        return true
      }
    } catch (e) {
      return false
    }
  },
  async paid({ commit }, payload) {
    this.$toast.global.loading()
    try {
      const res = await this.$repositories.invoiceAdmin.paid(payload.id)
      const { status } = res
      if (status === 200) {
        this.$toast.global.success()
        return true
      }
    } catch (e) {
      this.$toast.global.errorSubmitInvoice()
      return false
    }
  },
  async ret({ commit }, payload) {
    this.$toast.global.loading()
    try {
      const res = await this.$repositories.invoiceAdmin.ret(payload.id)
      const { status } = res
      if (status === 200) {
        this.$toast.global.success()
        return true
      }
    } catch (e) {
      return false
    }
  },
}
