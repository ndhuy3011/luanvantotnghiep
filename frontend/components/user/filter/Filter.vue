<template>
  <div id="productFilter">
    <v-row>
      <v-col>
        <h5>Giá</h5>
        <hr />
        <v-row>
          <v-col cols="12" sm="12" md="12">
            <v-radio-group column>
              <v-radio label="100,000 - 300,000" value="1"></v-radio>
              <v-radio label="300,000 - 500,000" value="2"></v-radio>
              <v-radio label="> 500,000" value="3"></v-radio>
            </v-radio-group>
          </v-col>
        </v-row>
      </v-col>
    </v-row>

    <v-row>
      <v-col>
        <h5>Size</h5>
        <hr />
        <v-row>
          <v-col cols="12" sm="6" md="6">
            <v-radio-group v-model="size" column>
              <v-radio label="S" value="S"></v-radio>
              <v-radio label="M" value="M"></v-radio>
              <v-radio label="L" value="L"></v-radio>
              <v-radio label="XL" value="XL"></v-radio>
            </v-radio-group>
          </v-col>
        </v-row>
      </v-col>
    </v-row>

    <v-row>
      <v-col>
        <h5>Màu</h5>
        <hr />
        <v-row>
          <v-col cols="6" sm="12" md="12">
            <div
              v-for="(color, index) in $local.vn.colors"
              :key="index"
              class="colors-wrap"
            >
              <span
                class="colors"
                :style="{ background: color.value }"
                :class="colorActive.value === color.value ? 'selected' : ''"
                @click="ColorActive(color)"
              ></span>
            </div>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  data: () => ({
    size: '',
    price: '',
    colorActive: {},
    category: null,
  }),
  computed: {
    categorys() {
      return this.$store.state.user.categorys.select
    },
  },
  methods: {
    ColorActive(colorItem) {
      this.colorActive = colorItem
    },
  },
}
</script>

<style lang="scss" scoped>
#productFilter {
  margin-top: 10px;
  .btn-light {
    z-index: 13;
  }
}
.colors-wrap {
  width: 100%;
  height: auto;
  margin: 5px auto;
  padding: 10px;
  border-radius: 70px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  display: inline;
}
.colors {
  width: 20px;
  height: 20px;
  display: inline-block;
  transition: 0.3s all;
  border-radius: 50%;
  border: 1px solid black;
  margin-bottom: 5px;
}
.colors:hover {
  transform: scale(1.2);
  box-shadow: 0 0 0 8px rgba(173, 173, 170, 0.3);
  cursor: pointer;
}
.colors:active {
  transform: scale(1);
}
.selected {
  box-shadow: 0 0 0 4px #fff, 0 0 0 8px rgba(173, 173, 170, 0.3);
}
</style>
