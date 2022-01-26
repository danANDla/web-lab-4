<template>
  <div>
    <header>
      <span>Алексеев Даниил</span> P3233<br>
      вар. 3309
    </header>
    <main>
      <div class="content-wrapper">
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
              //this.$store.commit('updateToken', data.jwtToken)
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
            else{
              this.error = "some_error";
            }
            // if(data.toString() === "false") {
            //   console.log("Bad response");
            // }
            // else{
            //   console.log("Good response");
            //   this.$router.push({name: "PointApp", params: {login: Login, pass: Pass, status: "ok"}});
            // }
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