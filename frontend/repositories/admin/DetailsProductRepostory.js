const resource = '/admin/detailsproduct'
export default ($axios) => ({
  all() {
    return $axios.get(`${resource}`)
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

  delete(id) {
    return $axios.delete(`${resource}/${id}`)
  },
  updateImage(id, payload) {
    return $axios.put(`${resource}/${id}/image`, payload)
  },
})
