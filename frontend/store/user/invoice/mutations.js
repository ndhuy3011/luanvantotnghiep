export default {
  MUTATIONS_INVOICE_CREATE(state, payload) {
    state.billCode = payload
  },
  MUTATIONS_INVOICE_GET(state, payload) {
    state.invoice = payload
  },
  MUTATION_INVOICE_NOT(state, payload) {
    state.not = payload.data
    payload.data.forEach((el) => {
      let total = 0
      el.invoiceDetails.forEach((elm) => {
        total = total + elm.price * elm.amount
      })
      state.totalnot.push(total)
    })
  },
  MUTATION_INVOICE_ACCURACY(state, payload) {
    state.accuracy = payload.data
    payload.data.forEach((el) => {
      let total = 0
      el.invoiceDetails.forEach((elm) => {
        total = total + elm.price * elm.amount
      })
      state.totalaccuracy.push(total)
    })
  },
  MUTATION_INVOICE_TRANSPORT(state, payload) {
    state.transport = payload.data
    payload.data.forEach((el) => {
      let total = 0
      el.invoiceDetails.forEach((elm) => {
        total = total + elm.price * elm.amount
      })
      state.totaltransport.push(total)
    })
  },
  MUTATION_INVOICE_COMPLETE(state, payload) {
    state.complete = payload.data
    payload.data.forEach((el) => {
      let total = 0
      el.invoiceDetails.forEach((elm) => {
        total = total + elm.price * elm.amount
      })
      state.totalcomplete.push(total)
    })
  },
  MUTATION_INVOICE_CANCEL(state, payload) {
    state.cancel = payload.data
    payload.data.forEach((el) => {
      let total = 0
      el.invoiceDetails.forEach((elm) => {
        total = total + elm.price * elm.amount
      })
      state.totalcancel.push(total)
    })
  },
}
