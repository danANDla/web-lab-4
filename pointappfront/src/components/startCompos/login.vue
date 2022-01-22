<template>
  <div class="login">
    <div class="input-text-block">
      <div class="input-wrappers">
        <div class="login-text">
          <div class="wrappers">
            <div class="value-label">login</div>
            <input type="text" placeholder="Type login" v-model="login">
          </div>
        </div>

        <div class="pass-text">
          <div class="wrappers">
            <div class="value-label">password</div>
            <input type="text" placeholder="Type password" v-model="pass">
          </div>
        </div>
      </div>
    </div>
    <div class="login-btns-block">
        <usual-button @click.native="signIn">sign in</usual-button>
        <div class="signUP-block">
          Not registred yet?
          <usual-button @click.native="signUp">sign up</usual-button>
        </div>
    </div>
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
  });
}

import UsualButton from "@/components/UI/button";
export default {
  name: "login",
  components: {UsualButton},
  data(){
    return{
      login : "",
      pass : "",
      status: ""
    }
  },
  methods:{
    signIn : function(){
      console.log("sign in")
      this.status = ""
      sendReq('/api/auth/sign-in', {
        login: this.login,
        password: this.pass
      })
          .then(response => response.json())
          .then(data => {
            console.log(data)
            this.status = data.data
            if (data.status.toString() === "true") {
              this.$router.push({name: 'main', params: {login: this.login, pass: this.pass, registered: 'true'}})
            }
          });
    },

    signUp : function(){
      console.log("sign up")
      this.status = ""
      sendReq('/api/auth/sign-up', {
        login: this.login,
        password: this.pass
      })
          .then(response => response.json())
          .then(data => {
            console.log(data)
            this.status = data.data
            if (data.status.toString() === "true") {
              this.$router.push({name: 'PointApp', params: {login: this.login, pass: this.pass, registered: 'true'}})
            }
          });
    }
  }
}
</script>

<style scoped>
.login{
  display: grid;
  grid-template-columns: 2fr 1fr;
  width: 60%;
  background: #202020;
  padding: 10px;
}
.value-label{
  margin-left: 2px;
  font-weight: 500;
}

.login-text > .wrappers > input, .pass-text > .wrappers > input {
  margin-left: 10px;
}
.pass-text{
  margin-top: 12px;
}
.wrappers{
  display: flex;
}
.login-btns-block{
  text-align: right;
  display: flex;
  flex-direction: column;
}
.input-text-block{
  display: flex;
  align-items: center;
}
.wrappers{
  text-align: right;
}
.signUP-block{
  margin-top: 15px;
  display: flex;
  flex-direction: column;
}
</style>