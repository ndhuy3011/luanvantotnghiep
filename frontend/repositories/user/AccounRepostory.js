export default ($axios) => ({
  login(payload) {
    return $axios.get(`/login`, payload)
  },
  register(payload) {
    return $axios.post('/register', payload)
  },
  update(payload) {
    return $axios.put('/user', payload)
  },
  accessToken(payload) {
    return $axios.post('/access_token', payload)
  },
  facebook(payload) {
    return $axios.post('/facebook', payload)
  },
  verification(payload) {
    return $axios.get('/verification?token=' + payload)
  },
})
