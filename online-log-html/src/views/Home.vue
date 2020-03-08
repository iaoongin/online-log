<template>
  <div class="home">
    <div class="panels">
      <div class="setting">
        <span class="title">选择日志</span>
        <a-select
          :value="selectValue"
          @change="selectHandleChange"
          style="width: 300px"
        >
          <a-select-option
            v-for="it in selectData"
            :key="it.key"
            :value="it.key"
            >{{ it.name + `(${it.key})` }}</a-select-option
          >
        </a-select>

        <span class="title">日志数量:</span>
        <a-input type="number" style="width: 100px;" v-model="num" />
        <a-button type="primary" @click="renderData">提交</a-button>
      </div>

      <div v-if="_.isEmpty(list)" class="empty">没有日志</div>
      <div v-else class="panel" v-for="(item, index) in list" :key="index">
        <h2 class="title">{{ item.name }}({{ item.key }})</h2>
        <div class="panel-body">
          <div v-for="(it, i) in item.lines" :key="i">
            <div class="lineNum">{{ i + 1 }}</div>
            <div class="line">{{ it }}</div>
          </div>
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
.panel-body {
  color: rgb(43, 176, 102);
  overflow-x: auto;
  margin: 0 auto;
  padding: 50px;
}
.title,
.white {
  color: white;
}
.empty {
  color: white;
  text-align: center;
  height: 300px;
  line-height: 300px;
  font-size: 2rem;
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
import { tail, names } from "@/apis/tail";

export default {
  name: "home",
  components: {},
  data() {
    return {
      list: [],
      num: 10,
      selectValue: "",
      selectData: []
    };
  },
  created() {
    this.renderSelect();
  },
  methods: {
    renderData() {
      tail({
        num: this.num,
        key: this.selectValue
      }).then(resp => {
        this.list = resp.data;
        // console.log(this.data);
      });
    },
    selectHandleChange(value) {
      this.selectValue = value;
    },
    renderSelect() {
      names().then(resp => {
        this.selectData = resp.data;
        // debugger
        if (resp.data.length > 0) {
          this.selectValue = resp.data[0].name;
          this.renderData();
        }
      });
    }
  }
};
</script>
