export default function ({ $axios, $toast }) {
  $axios.onError((data) => {
    const { errors, message } = data.response.data
    if (parseInt(data.response.status) === 400) {
      $toast.global.error_badrequest()
      if (status === 400) {
        if (errors) {
          errors.forEach(
            (el) =>
              el === 'Invaild name' ||
              $toast.error('Tên không hợp lệ', {
                position: 'top-center',
                type: 'error',
                duration: 5000,
                theme: 'outline',
                icon: 'error',
              })
          )
        }
      }
    }
    // if (parseInt(data.response.status) === 500) {}
    if (parseInt(data.response.status) === 409) {
      $toast.global.conflitct()
      if (message === 'Namesake') {
        $toast.error('Bạn nhập trùng tên', {
          position: 'top-center',
          type: 'error',
          duration: 5000,
          theme: 'outline',
          icon: 'error',
        })
      }
    }
  })
  $axios.onRequest((config) => {
    // if (config.url === '/admin/category') {
    // }
    return config
  })
  $axios.onResponse((config) => {
    config.headers['Content-Type'] = 'application/json'
    config.headers['Access-Control-Allow-Origin'] = '*'
  })
}
