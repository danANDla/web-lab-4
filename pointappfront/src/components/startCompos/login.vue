<template>
  <div class="login">
    <div class="signin">
      <div class="login-text">
        <div class="wrappers">
          <div class="value-label">login</div>
          <div>
            <input type="text" v-model="login">
          </div>
        </div>
      </div>
      <div class="pass-text">
        <div class="wrappers">
          <div class="value-label">password</div>
          <div>
            <input type="password" v-model="pass">
          </div>
        </div>
      </div>
      <div class="error-msg">
        {{this.error}}
      </div>
      <div class="btn-block">
        <usual-button @click.native="signIn">sign in</usual-button>
      </div>
    </div>

    <div class="signup">
      <div class="btn-block">
        <usual-button @click.native="signUp" btn-type="interesting">sign up</usual-button>
      </div>
    </div>
  </div>
</template>

<script>
import UsualButton from "@/components/UI/button";
export default {
  name: "login",
  components: {UsualButton},
  props:{
    error: String
  },
  data(){
    return{
      login : "",
      pass : "",
      status: "",
      token: ""
    }
  },
  methods:{
    signIn : function(){
      if(this.pass !== "" && this.login !== "" && this.login !== undefined && this.pass !== undefined){
        this.$emit("logIn", this.login, this.pass);
        document.querySelector('input[type=password]').style.border = "4px solid #ffffff";
        document.querySelector('input[type=text]').style.border = "4px solid #ffffff";
      }
      else {
        if (this.pass === "" || this.pass === undefined) {
          console.log("empty");
          document.querySelector('input[type=password]').style.border = "4px solid #c1364c";
        }
        if(this.login === "" || this.login === undefined){
          console.log("empty");
          document.querySelector('input[type=text]').style.border = "4px solid #c1364c";
        }
      }
    },
    signUp: function (){
      this.$router.push({name: "Registration"})
    }
  }
}
</script>

<style scoped>
.signin, .signup{
  width: 100%;
  background: #202020;
  padding: 23px;
  display: block;
}
.signin{
  margin-bottom: 10px;
}
.value-label{
  margin-bottom: 6px;
  font-weight: 500;
}
.wrappers > div{
  display: block;
}
.login-text{
  margin-bottom: 30px;
}
.btn-block{
  display: flex;
  flex-direction: column;
  width: 100%;
}
.signup{
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

input {
  font-size: 16px;
  font-family: inherit;
  padding: 0.25em 0.5em;
  background-color: #fff;
  border: 4px solid #ffffff;
  border-radius: 4px;
  transition: 180ms box-shadow ease-in-out;
}

.error-msg{
  text-align: center;
  color: #c1364c;
  margin-top: 10px;
  height: 20px;
  margin-bottom: 10px;
}
</style>