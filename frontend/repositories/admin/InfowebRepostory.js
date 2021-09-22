const resource = '/admin/infoweb'
export default ($axios) => ({
  get() {
    return $axios.get(`${resource}`)
  },

  create(payload) {
    return $axios.post(`${resource}`, payload)
  },
})
