const resource = '/cart'
export default ($axios) => ({
  showcart() {
    return $axios.get(`${resource}`)
  },
  addcart(payload) {
    return $axios.post(`${resource}`, payload)
  },
  delete(id) {
    return $axios.delete(`${resource}/${id}`)
  },
  updated(id, payload) {
    return $axios.patch(`${resource}/${id}`, payload)
  },
})
