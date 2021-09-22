<template>
  <div>
    <v-row>
      <v-spacer></v-spacer>
      <v-col cols="6" md="2" sm="2">
        <v-select
          v-model="select"
          :items="$local.vn.selects"
          chips
          flat
          solo
        ></v-select>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  data: () => {
    return {
      select: null,
    }
  },
}
</script>

<style lang="scss" scoped></style>
