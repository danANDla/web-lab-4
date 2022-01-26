<template>
  <div>
    <header>
      <div class="toStart">
        <div>
         PointsApp
        </div>
      </div>
      <div class="username">
        <div class="dropdown">
          <div class="dropbtn">{{login}}</div>
          <div class="hidden-button">
            <usual-button @click.native="logOut" btn-type="dangdang">log out</usual-button>
          </div>
        </div>
      </div>
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

async function sendReq(url, params, jwt){
  url = "http://127.0.0.1:8080/web4-1.0/api/pointApp" + url;
  return await fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
      'Authorization': 'Bearer ' + jwt
    },
    body: JSON.stringify(params)
  })
}
import PointTable from "@/components/appCompos/point-table";
import PointForm from "@/components/appCompos/point-form";
import UsualButton from "@/components/UI/button";
export default {
  components: {UsualButton, PointForm, PointTable},
  data() {
    return {
      login: this.$route.params.login,
      status: this.$route.params.status,
      token: this.$store.state.auth.token,
      points: [  ]
    }
  },
  methods:{
    sendData: function (newPoint){
      console.log("FUNC: Send point")
      sendReq("/add", {
        x: newPoint.x.toString(),
        y: newPoint.y.toString(),
        r: newPoint.r.toString()
      }, this.token)
          .then(response => {
            if(response.status == 401){
              this.$router.push({name:"Start"})
            }
            return response
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
      sendReq('/clear', null, this.token)
          .then(response => {
            if(response.status == 401){
              this.$router.push({name:"Start"})
            }
            return response
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
      sendReq('/pointTable', null, this.token)
          .then(response => {
            if(response.status == 401){
              this.$router.push({name:"Start"})
            }
            return response
          })
          .then(response => response.json())
          .then(data => {
            console.log(data)
            this.points = data
          });
    },
    logOut(){
      this.login = "";
      this.token = "";
      this.status = "";
      this.$router.push({name: "Start"});
    }
  },
  created() {
    this.updatePoints();
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
.username > div, .toStart > div{
  padding: 5px;
  display: inline-block;
}
main{
  padding: 10px;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.hidden-button{
  display: none;
  z-index: 1;
  position: absolute;
  right: 0;
  left: auto;
  width: 100%;
  padding: 8px;
}

.dropdown:hover .hidden-button{
  margin-top: 5px;
  display: flex;
  background-color: #343434;
  flex-direction: column;
}

.dropdown:hover{
  background-color: #343434;
}
</style>
