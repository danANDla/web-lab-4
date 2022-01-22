<template>
  <div class="table-container">
    <table id="points-table">
      <thead>
      <tr>
        <td> X </td>
        <td> Y </td>
        <td> R </td>
        <td> sent on </td>
        <td> is hit</td>
      </tr>
      </thead>
      <tbody id="scrollBody">
      <template v-for="point in points" v-bind:key="point.time">
        <tr v-bind:class="{'ishit-true': point.ishit, 'ishit-false': !point.ishit}">
          <td>{{point.x}}</td>
          <td>{{point.y}}</td>
          <td>{{point.r}}</td>
          <td>{{point.stime}}</td>
          <td>{{point.hit}}</td>
        </tr>
      </template>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "point-table",
  props:{
    points:{
      type: Array,
      required: true
    }
  },
  methods:{
    scrollToEnd: function() {
      var container = document.querySelector("#scrollBody");
      container.scrollTop = container.scrollHeight;
    }
  },
  updated() {
    this.scrollToEnd();
  }

}
</script>

<style scoped>

.table-container {
  height: 24em;
}

table {
  display: flex;
  flex-flow: column;
  width: 100%;
  height: 100%;
  background: #202020;
  border-collapse: collapse;
}

table > thead {
  /* head takes the height it requires,
  and it's not scaled when table is resized */
  flex: 0 0 auto;
  width: 100%;
  display: table;
  table-layout: fixed;
  text-align: center;
  background: #202020;
}

table > tbody{
  /* body takes all the remaining available space */
  flex: 1 1 auto;
  background: #292929;
  display: block;
  overflow-y: scroll;
}

table > tbody > tr{
  width: 100%;
  display: table;
  table-layout: fixed;
}

table > tbody > tr.ishit-true {
  background: #59ab42;
}
table > tbody > tr.ishit-false {
  background: #ab2a3d;
}

</style>
