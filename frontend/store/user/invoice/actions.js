import constants from './constants'

export default {
  async init({ commit }) {
    const allNot = await this.$repositories.invoiceUser.allNot()
    commit(constants.MUTATION_INVOICE_NOT, allNot)
    const accuracy = await this.$repositories.invoiceUser.allAccuracy()
    commit(constants.MUTATION_INVOICE_ACCURACY, accuracy)
    const allTransport = await this.$repositories.invoiceUser.allTransport()
    commit(constants.MUTATION_INVOICE_TRANSPORT, allTransport)
    const complete = await this.$repositories.invoiceUser.allComplete()
    commit(constants.MUTATION_INVOICE_COMPLETE, complete)
    const cancel = await this.$repositories.invoiceUser.allCancel()
    commit(constants.MUTATION_INVOICE_CANCEL, cancel)
  },
  async createIvoice({ commit }, invoice) {
    try {
      const res = await this.$repositories.invoiceUser.create(invoice)
      const { status, data } = res
      if (status === 201) {
        commit(constants.MUTATIONS_INVOICE_CREATE, data.billCode)
        if (invoice.payment === 'MoMo') {
          this.$router.push({ name: 'payment' })
        }
      }
    } catch (e) {
      return false
    }
  },
  async savePayment({ commit }, payload) {
    const data = {
      billCode: payload,
    }
    try {
      const res = await this.$repositories.invoiceUser.savePayment(data)
      const { status } = res
      if (status === 200) {
        this.$toast.global.paymet()
        this.$router.push({ name: 'index' })
      }
    } catch (e) {
      return false
    }
  },
  async getInvoice({ commit }) {
    try {
      const res = await this.$repositories.invoiceUser.all()
      const { status, data } = res
      if (status === 200) {
        commit(constants.MUTATIONS_INVOICE_GET, data)
      }
    } catch (e) {
      return false
    }
  },
  async notData({ commit }) {
    const payload = await this.$repositories.invoiceUser.allNot()
    commit(constants.MUTATION_INVOICE_NOT, payload)
    return payload.data
  },
  async accuracyData({ commit }) {
    const payload = await this.$repositories.invoiceUser.allAccuracy()
    commit(constants.MUTATION_INVOICE_ACCURACY, payload)
    return payload.data
  },

  async transportData({ commit }) {
    const payload = await this.$repositories.invoiceUser.allTransport()
    commit(constants.MUTATION_INVOICE_TRANSPORT, payload)
    return payload.data
  },
  async completeData({ commit }) {
    const payload = await this.$repositories.invoiceUser.allComplete()
    commit(constants.MUTATION_INVOICE_COMPLETE, payload)
    return payload.data
  },
  async cancelData({ commit }) {
    const payload = await this.$repositories.invoiceUser.allCancel()
    commit(constants.MUTATION_INVOICE_CANCEL, payload)
    return payload.data
  },
  async del({ commit }, payload) {
    this.$toast.global.loading()
    try {
      const res = await this.$repositories.invoiceUser.del(payload.id)
      const { status } = res
      if (status === 200) {
        this.$toast.global.success()
        this.$router.go()
        return true
      }
    } catch (e) {
      return false
    }
  },
}
