<template>
  <div>
    <header>
      <div class="toStart">PointsApp</div>
      <div class="username">{{login}}</div>
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
import login from "../components/startCompos/login";

async function sendReq(url, params){
  url = "http://127.0.0.1:8080/web4-1.0/api/pointApp" + url;
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
import router from "@/router";
export default {
  components: {PointForm, PointTable},
  data() {
    return {
      login: this.$route.params.login,
      pass: this.$route.params.pass,
      status: this.$route.params.status,
      points: [  ]
    }
  },
  methods:{
    sendData: function (newPoint){
      console.log("FUNC: Send point")
      sendReq("/add", {
        x: newPoint.x.toString(),
        y: newPoint.y.toString(),
        r: newPoint.r.toString(),
        login: "some user",
        pass: "some password"
      })
          .then(response => response.json())
          .then(data => {
            console.log(data)
            if(data.toString() === "false") {
              console.log("Bad response");
            }
            else{
              this.updatePoints();
              //this.points = data; //this.$emit('table', data)
              //this.$refs.canvas.dots(data)
            }
          });
    },
    clearData: function(){
      console.log("FUNC: Clear table")
      this.error = ""
      sendReq('/clear', {
        login: "some login",
        password: "some password",
      })
          .then(response => response.json())
          .then(data => {
            console.log(data)
            if(data.toString() === "false") {
              console.log("Bad response");
            }
            else{
              this.updatePoints();
            }
          });
    },
    updatePoints(){
      console.log("FUNC: Update table")
      this.error = ""
      sendReq('/pointTable', {
        login: "some login",
        password: "some password",
      })
          .then(response => response.json())
          .then(data => {
            console.log(data)
            this.points = data
          });
    }
  },
  created() {
    if(this.status !== "ok"){
      this.$router.push({name: "Start"});
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
header{
  display: flex;
  padding: 5px;
}

.toStart{
  flex-grow: 1;
  text-align: left;
}
.username{
  flex-grow: 1;
  text-align: right;
}
main{
  padding: 10px;
}
</style>
