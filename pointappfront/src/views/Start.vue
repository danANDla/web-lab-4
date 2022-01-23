<template>
  <div>
    <header>
      <span>Алексеев Даниил</span> P3233<br>
      вар. 33407
    </header>
    <main>
      <login @regUser="signUp"></login>
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
import Login from "@/components/startCompos/login";
export default {
  name: "Start",
  components: {Login},
  methods: {
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
            }
          });
    }
  }
}

</script>

<style scoped>

</style>