export default ($axios) => ({
  login(payload) {
    const resource = '/admin/login'
    return $axios.post(`${resource}`, payload)
  },
  user() {
    const resource = '/admin/user'
    return $axios.get(`${resource}`)
  },
})
