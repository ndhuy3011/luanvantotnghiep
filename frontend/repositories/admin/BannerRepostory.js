const resource = '/admin/banner'
export default ($axios) => ({
  get() {
    return $axios.get(`${resource}`)
  },

  create(payload) {
    return $axios.post(`${resource}`, payload)
  },

  delete(id) {
    return $axios.delete(`${resource}/${id}`)
  },
})
