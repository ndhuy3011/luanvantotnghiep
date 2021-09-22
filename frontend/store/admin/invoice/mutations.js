export default {
  MUTATION_ADMIN_INVOICE_INIT(state, payload) {
    state.invoice = payload.data
  },
  MUTATION_ADMIN_INVOICE_NOT(state, payload) {
    state.not = payload.data
  },
  MUTATION_ADMIN_INVOICE_ACCURACY(state, payload) {
    state.accuracy = payload.data
  },
  MUTATION_ADMIN_INVOICE_TRANSPORT(state, payload) {
    state.transport = payload.data
  },
  MUTATION_ADMIN_INVOICE_COMPLETE(state, payload) {
    state.complete = payload.data
  },
  MUTATION_ADMIN_INVOICE_CANCEL(state, payload) {
    state.cancel = payload.data
  },

  MUTATION_ADMIN_INVOICE_RETURN(state, payload) {
    state.return = payload.data
  },
}
