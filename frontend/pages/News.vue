<template>
  <section class="overflow-hidden">
    <v-row no-gutters>
      <v-col class="hidden-sm-and-down" md="6">
        <v-img :src="require('@/assets/welcome.png')" height="100vh" />
      </v-col>

      <v-col
        class="align-content-space-between layout wrap"
        cols="12"
        md="6"
        :pa-5="$vuetify.breakpoint.smAndDown"
      >
        <Bubble1 style="transform: rotate(180deg) translateY(25%)" />

        <v-row align="center" justify="center">
          <v-col cols="10" md="6">
            <h2>Xin chào!</h2>
            <p
              class="title font-weight-light"
              style="line-height: 1.7 !important"
            >
              {{ $local.vn.shop }}
            </p>
            <!-- <base-btn class="mt-4"> Learn More! </base-btn> -->
          </v-col>
        </v-row>

        <Bubble2 style="transform: rotate(180deg) translate(-200px, -15%)" />
      </v-col>
    </v-row>
    <v-row class="white" no-gutters>
      <v-col class="pa-5" cols="12" md="6">
        <Bubble-1 style="transform: translate(5%, -5%)" />

        <h2>{{ $local.vn.title_news }}</h2>

        <v-alert outlined class="mt-5">
          <v-row v-for="(trend, i) in $local.vn.trends" :key="i">
            <v-col class="text-uppercase" cols="6" v-text="trend.name" />

            <v-col class="text-right" cols="6" v-text="`${trend.value}%`" />

            <v-progress-linear
              :value="trend.value"
              height="8"
              color="#BCAAA4"
            />
          </v-row>
        </v-alert>
      </v-col>

      <v-col class="hidden-sm-and-down" md="6">
        <v-img :src="require('@/assets/about.jpg')" height="100%" />
      </v-col>
    </v-row>
  </section>
</template>
<script>
import Bubble1 from '../components/Bubble1'
import Bubble2 from '../components/Bubble2'
export default {
  components: { Bubble1, Bubble2 },
  data: () => ({}),
}
</script>
