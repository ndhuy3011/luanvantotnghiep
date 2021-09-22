const resource = '/product'
export default ($axios) => ({
  all() {
    return $axios.get(`${resource}`)
  },
  show(id) {
    return $axios.get(`${resource}/${id}`)
  },
  hot() {
    return $axios.get(`${resource}/hot`)
  },
})
