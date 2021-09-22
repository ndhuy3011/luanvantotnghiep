const resource = '/admin/invoice'
export default ($axios) => ({
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

  allReturn() {
    return $axios.get(`${resource}/return`)
  },
  show(id) {
    return $axios.get(`${resource}/${id}`)
  },

  create(payload) {
    return $axios.post(`${resource}`, payload)
  },

  update(id, payload) {
    return $axios.put(`${resource}/${id}`, payload)
  },
  status(id) {
    return $axios.put(`${resource}/${id}/status`)
  },

  del(id) {
    return $axios.put(`${resource}/${id}/del`)
  },

  paid(id) {
    return $axios.put(`${resource}/${id}/paid`)
  },
  ret(id) {
    return $axios.put(`${resource}/${id}/return`)
  },
})
