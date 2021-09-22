const resource = '/category'
export default ($axios) => ({
  all() {
    return $axios.get(`${resource}`)
  },
  show(id) {
    return $axios.get(`${resource}/${id}`)
  },
})
