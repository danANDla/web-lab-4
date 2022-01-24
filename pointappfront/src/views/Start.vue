<template>
  <div>
    <header>
      <span>Алексеев Даниил</span> P3233<br>
      вар. 3309
    </header>
    <main>
      <div class="content-wrapper">
        <login @regUser="signUp"></login>
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
import Login from "@/components/startCompos/login";
export default {
  name: "Start",
  components: {Login},
  methods: {
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