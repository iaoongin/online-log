<template>
  <div class="home">
    <div class="panels">
      <div>
        <span class="title">日志数量:</span>
        <a-input type="number" style="width: 100px;" v-model="num" />
        <a-button type="primary" @click="renderData">提交</a-button>
      </div>
      <div class="panel" v-for="(item, index) in list" :key="index">
        <h2 class="title">{{item.name}}({{item.key}})</h2>
        <div v-for="(it ,i) in item.lines" :key="i">
          <div class="lineNum">{{ i }}</div>
          <div class="line">{{it}}</div>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
.home {
  
  background-color: rgb(41, 44, 52);
  height: 100%;
}
.panels {
  width: 80%;
  margin: 0 auto;
}
.panel {
  color: rgb(43, 176, 102);
  overflow-x: auto;
  margin: 0 auto;
}
.title{
  color: white;
}
.lineNum,
.line {
  display: inline;
}
.lineNum {
  width: 2rem;
  color: grey;
  position: absolute;
}

.line {
  height: 1rem;
  margin: 3px 0 3px 2rem;
  white-space: nowrap;

}
</style>
<script>
import { tail } from "@/apis/tail";
export default {
  name: "home",
  components: {},
  data() {
    return {
      list: [],
      num: 10
    };
  },
  created() {
    this.renderData();
  },
  methods: {
    renderData() {
      tail({
        num: this.num
      }).then(resp => {
        this.list = resp.data;
        // console.log(this.data);
      });
    }
  }
};
</script>
