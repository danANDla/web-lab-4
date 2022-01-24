<template>
  <div>
    <header>
      <span>Алексеев Даниил</span> P3233<br>
      вар. 3309
    </header>
    <main>
      <div class="content-wrapper">
        <registrate @regUser="signUp"></registrate>
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
            if(data.toString() === "false") {
              console.log("Bad response");
            }
            else{
              console.log("Good response");
              this.$router.push({name: "PointApp", params: {login: newLogin, pass: newPass, status: "ok"}});
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