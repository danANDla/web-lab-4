<template>
  <div class="app-block">

    <header>
      <span>Алексеев Даниил</span> P3233<br>
      вар. 33407
    </header>

    <main>
      <div class="content-wrapper">
        <div class="form-block">
          <point-form @pushPoint="sendData" @clearPoints="clearData"></point-form>
        </div>

        <div class="response-block">
          <point-table v-bind:points="points"></point-table>
        </div>
      </div>
    </main>

    <footer>

    </footer>
  </div>
</template>

<script>
import PointTable from "@/Components/point-table";
import PointForm from "@/Components/point-form";
export default {
  components: {PointForm, PointTable},
  data(){
    return{
      points: [  ]
    }
  },
  methods:{
    sendData: function (newPoint){
      this.points.push({x: newPoint.x, y: newPoint.y, r: newPoint.r, ishit: newPoint.ishit, time: newPoint.time});
    },
    clearData: function(){
      this.points = []
    }
  }
}
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600&display=swap");
* {
  box-sizing: border-box;
}

html, body {
  background: #343434;
  margin: 0;
  font-family: "Montserrat", sans-serif;
  font-weight: 300;
  color: whitesmoke;
  font-size: 20px;
}

header, main, footer{
  display: block;
  width: 100%;
  box-sizing: border-box;
}

.app-block{
  display: flex;
  flex-direction: column;
}

header{
  background: #8f8fff;
  padding: 2px;
  text-align: center;
  font-size: 30px;
}

header > span {
  font-weight: 500;
}

main{
  padding: 10px;
  height: 80vh;
}

.content-wrapper{
  display: grid;
  grid-template-columns: 2fr 3fr;
  grid-column-gap: 10px;
}

.form-block{
  grid-column-start: 1;
  grid-column-end: 2;
  grid-row-start: 1;
  grid-row-end: 2;
  padding: 5px;
}

.response-block{
  grid-column-start: 2;
  grid-column-end: 3;
  grid-row-start: 1;
  grid-row-end: 3;
}
</style>
