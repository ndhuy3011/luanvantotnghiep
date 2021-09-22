import Vue from 'vue'
import VueFbCustomerChat from 'vue-fb-customer-chat'

Vue.use(VueFbCustomerChat, {
  page_id: '102480125364233', //  change 'null' to your Facebook Page ID,
  theme_color: '#007CFF', // theme color in HEX
  locale: 'en_US', // default 'en_US'
})
