const resource = '/review'
export default ($axios) => ({
  post(payload) {
    return $axios.post(`${resource}`, payload)
  },
  show(payload) {
    return $axios.get(`${resource}/${payload}`)
  },
})
