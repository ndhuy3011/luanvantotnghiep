const resource = '/admin/report'
export default ($axios) => ({
  getReportInvoice() {
    return $axios.get(`${resource}/invoice`)
  },

  getReportInvoiceDate(payload) {
    return $axios.get(`${resource}/invoice/date`, payload)
  },

  getReportCategory() {
    return $axios.get(`${resource}/invoice/category`)
  },
  getReportSum(payload) {
    return $axios.get(`${resource}/invoice/sum?date=${payload}`)
  },
  getReportSumWeek() {
    return $axios.get(`${resource}/invoice/sum/week`)
  },
})
