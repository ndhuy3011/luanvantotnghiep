const resource = '/invoice'
export default ($axios) => ({
  create(payload) {
    return $axios.post(`${resource}`, payload)
  },
  savePayment(payload) {
    return $axios.post('invoice/payment', payload)
  },
  all() {
    return $axios.get(`${resource}`)
  },
  allNot() {
    return $axios.get(`${resource}/not`)
  },
  allAccuracy() {
    return $axios.get(`${resource}/accuracy`)
  },
  allTransport() {
    return $axios.get(`${resource}/transport`)
  },

  allComplete() {
    return $axios.get(`${resource}/complete`)
  },

  allCancel() {
    return $axios.get(`${resource}/cancel`)
  },
  del(id) {
    return $axios.put(`${resource}/${id}/del`)
  },
})
