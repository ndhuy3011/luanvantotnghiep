export default {
  getAll: (state) => {
    return state.invoice
  },
  getNotSize: (state) => {
    return state.not.length
  },

  getAccuracySize: (state) => {
    return state.accuracy.length
  },
  getTransportSize: (state) => {
    return state.transport.length
  },
  getCompleteSize: (state) => {
    return state.complete.length
  },
}
