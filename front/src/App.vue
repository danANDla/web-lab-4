<template>
  <div class="app-block">
    <router-view></router-view>
  </div>
</template>

<script>
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600&display=swap");
* {
  box-sizing: border-box;
}

html, body {
  background: #343434;
  margin: 0;
  font-family: "Montserrat", sans-serif;
  font-weight: 300;
  color: whitesmoke;
  font-size: 20px;
}

.app-block{
  display: flex;
  flex-direction: column;
}
</style>
