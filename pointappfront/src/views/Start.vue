<template>
  <div>
    <header>
      <span>Алексеев Даниил</span> P3233<br>
      вар. 3309
    </header>
    <main>
      <div class="content-wrapper">
        <div class="time-block">
          <div id="clock"></div>
          <div id="date"></div>
        </div>
        <login @logIn="signIn" v-model:error="error"></login>
      </div>
    </main>

    <footer>

    </footer>
  </div>
</template>

<script>
import login from "@/components/startCompos/login";

async function sendReq(url, params){
  url = "http://127.0.0.1:8080/web4-1.0/api/login" + url;
  return await fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    body: JSON.stringify(params)
  })
}
import Login from "@/components/startCompos/login";
export default {
  name: "Start",
  components: {Login},
  data(){
    return{
      token: "",
      error: "",
    }
  },
  methods: {
    signIn: function (Login, Pass){
      sendReq("/signin", {
        login: Login,
        password: Pass
      })
          .then(response => response.json())
          .then(data => {
            console.log(data)
            console.log(data.token)
            if(data.loginStatus === "OK" && data.jwtToken !== ""){
              this.$store.commit('auth/updateToken', data.jwtToken)
              this.$store.commit('auth/updateLogin', Login)
              this.$router.push({name: "PointApp", params: {status: "ok"}});
            }
            else if (data.loginStatus === "NO_USER_FOUND"){
              this.error = "login wasn't found";
            }
            else if (data.loginStatus === "WRONG_PASSWORD"){
              this.error = "incorrect password";
            }
            else if (data.loginStatus === "UNABLE_TO_UPDATE"){
              this.error = "can't update token";
            }
            else{
              this.error = "some_error";
            }
          });
    },
    update: function() {
        let date = new Date(),
            months = ["January","February","March","April","May","June","July","August","September","October","November","December"],
            days = (date.getDate() < 10) ? '0' + date.getDate() : date.getDate(),
            hours = (date.getHours() < 10) ? '0' + date.getHours() : date.getHours(),
            minutes = (date.getMinutes() < 10) ? '0' + date.getMinutes() : date.getMinutes(),
            seconds = (date.getSeconds() < 10) ? '0' + date.getSeconds() : date.getSeconds();
        document.getElementById('clock').innerHTML = hours + ':' + minutes + ':' + seconds;
        document.getElementById('date').innerHTML = days + ' ' + months[date.getMonth()] + ' ' + date.getFullYear();
    }
  },
  mounted() {
    this.update();
    setInterval(this.update, 11000);
  }
}

</script>

<style scoped>
main{
  padding: 10px;
}
.content-wrapper{
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

.time-block{
  width: 100%;
  background: #202020;
  padding: 23px;
  display: block;
  margin-bottom: 40px;
}
</style>