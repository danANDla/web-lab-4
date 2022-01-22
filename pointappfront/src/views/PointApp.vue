<template>
  <div>
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
async function sendReq(url, params){
  url = "http://127.0.0.1:8080/web4-1.0" + url;
  return await fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    body: JSON.stringify(params)
  })
}
import PointTable from "@/components/appCompos/point-table";
import PointForm from "@/components/appCompos/point-form";
export default {
  components: {PointForm, PointTable},
  data() {
    return {
      login: this.$route.params.login,
      pass: this.$route.params.pass,
      registered: this.$route.params.registered,
      points: [  ]
    }
  },
  methods:{
    sendData: function (newPoint){
      sendReq("/api/pointApp/parseParams", {
        x: newPoint.x.toString(),
        y: newPoint.y.toString(),
        r: newPoint.r.toString()
      })
          .then(response => response.json())
          .then(data => {
            console.log(data)
            this.points = data; //this.$emit('table', data)
            //this.$refs.canvas.dots(data)
          });
    },
    clearData: function(){
      this.error = ""
      sendReq('/api/pointApp/clear', {
        login: this.login,
        password: this.pass,
      })
          .then(response => response.json())
          .then(data => {
            console.log(data)
            this.updatePoints() //this.$emit('table', {data: [], status: data.status})
            //this.$refs.canvas.clear_dots();
          });
    },
    updatePoints(){
      console.log("update points")
      this.error = ""
      sendReq('/api/pointApp/pointTable', {
        login: this.login,
        password: this.pass,
      })
          .then(response => response.json())
          .then(data => {
            console.log(data)
            this.points = data; //this.$emit('table', data)
            //this.$refs.canvas.dots(data)
          });
    }
  }
}
</script>

<style scoped>
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
