<template>
  <div>
    <header>
      <span>Алексеев Даниил</span> P3233<br>
      вар. 3309
    </header>
    <main>
      <div class="content-wrapper">
        <registrate @regUser="signUp" v-model:error="error"></registrate>
      </div>
    </main>

    <footer>

    </footer>
  </div>
</template>

<script>
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
import Registrate from "@/components/startCompos/registrate";
export default {
  name: "Registration",
  components: {Registrate},
  data(){
    return{
      error: "",
    }
  },
  methods:{
    signUp: function (newLogin, newPass){
      console.log(newPass);
      sendReq("/signup", {
        login: newLogin,
        password: newPass
      })
          .then(response => response.json())
          .then(data => {
            console.log(data)
            console.log(data.token)
            if(data.loginStatus === "OK" && data.jwtToken !== ""){
              //this.$store.commit('updateToken', data.jwtToken)
              this.$store.commit('auth/updateToken', data.jwtToken)
              this.$router.push({name: "PointApp", params: {status: "ok", login: newLogin}});
            }
            else if (data.loginStatus === "USER_ALREADY_EXISTS"){
              this.error = "this login is taken";
            }
            else if (data.loginStatus === "WRONG_PASSWORD"){
              this.error = "incorrect password";
            }
            else if (data.loginStatus === "UNABLE_TO_INSERT"){
              this.error = "can't insert user in db";
            }
            else{
              this.error = "unknown error";
            }
          });
    }
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
</style>