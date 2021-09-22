<template>
  <v-container>
    <v-row>
      <v-col cols="12" sm="9">
        <v-container>
          <v-row>
            <v-col>
              <v-card
                color="red"
                @click="$router.push({ name: 'admin-invoice-not' })"
              >
                <v-card-title>
                  <span class="text">{{ notSize }}</span></v-card-title
                >
                <v-card-subtitle
                  ><span class="text"
                    >Đơn hàng chưa xử lý</span
                  ></v-card-subtitle
                >
              </v-card>
            </v-col>
            <v-col
              ><v-card
                color="amber"
                @click="$router.push({ name: 'admin-invoice-accuracy' })"
                ><v-card-title
                  ><span class="text">{{ accuracySize }}</span></v-card-title
                >
                <v-card-subtitle
                  ><span class="text"
                    >Đơn hàng cần vận chuyển</span
                  ></v-card-subtitle
                >
              </v-card></v-col
            >
            <v-col
              ><v-card
                color="lime"
                @click="$router.push({ name: 'admin-invoice-transport' })"
                ><v-card-title
                  ><span class="text">{{ transportSize }}</span></v-card-title
                >
                <v-card-subtitle
                  ><span class="text"
                    >Đơn hàng đang vẫn chuyển</span
                  ></v-card-subtitle
                >
              </v-card></v-col
            >
            <v-col
              ><v-card color="green"
                ><v-card-title
                  ><span class="text">{{ sumDay.sum }}</span></v-card-title
                >
                <v-card-subtitle
                  ><span class="text">Tổng tiền hôm này</span></v-card-subtitle
                >
              </v-card></v-col
            >
          </v-row>
          <v-row>
            <v-col sm="6">
              <DoughnutChart :chartdata="sumCategory" />
            </v-col>
            <v-col sm="6">
              <Bar :chartdata="sumWeek" :options="options" />
            </v-col>
          </v-row>
        </v-container>
      </v-col>
      <v-col cols="12" sm="3">
        <v-sheet rounded="lg" min-height="268">
          <v-date-picker
            v-model="picker"
            header-color="orange"
            color="orange"
            locale="vi"
          ></v-date-picker>
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters } from 'vuex'
import Bar from '~/components/vue-chart/BarChart.js'
import DoughnutChart from '~/components/vue-chart/DoughnutChart.js'
export default {
  components: {
    Bar,
    DoughnutChart,
  },
  layout: 'admin',

  data: () => ({
    picker: new Date(Date.now()).toISOString().substr(0, 10),
    options: {
      scales: {
        yAxes: [
          {
            ticks: {
              beginAtZero: true,
            },
          },
        ],
      },
      responsive: true,
    },
  }),

  computed: {
    ...mapGetters({
      // map `this.doneCount` to `this.$store.getters.doneTodosCount`
      notSize: 'admin/invoice/getNotSize',
      accuracySize: 'admin/invoice/getAccuracySize',
      transportSize: 'admin/invoice/getTransportSize',
      completeSize: 'admin/invoice/getCompleteSize',
      sumCategory: 'admin/report/getDataCategoryDoughnutChart',
      sumDay: 'admin/report/getSumDay',
      sumWeek: 'admin/report/getSumWeek',
    }),
  },
  created() {},
  methods: {},
}
</script>

<style>
.text {
  color: white;
}
</style>
