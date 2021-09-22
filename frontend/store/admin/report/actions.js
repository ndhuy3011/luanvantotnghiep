export default {
  async init({ commit }) {
    const invoiceAmout = await this.$repositories.reportAdmin.getReportInvoice()
    commit(this.$constant.admin.MUTATION_ADMIN_REPORT_INIT, invoiceAmout.data)

    const invoiceCategory = await this.$repositories.reportAdmin.getReportCategory()
    commit(
      this.$constant.admin.MUTATION_ADMIN_REPORT_CATEGORY_INIT,
      invoiceCategory.data
    )
    const invoiceSum = await this.$repositories.reportAdmin.getReportSumWeek()
    commit(this.$constant.admin.MUTATION_ADMIN_REPORT_SUM_INIT, invoiceSum.data)
  },
}
