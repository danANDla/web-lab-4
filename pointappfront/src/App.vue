<template>
  <div class="app-block">
    <router-view/>
  </div>
</template>

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

header, main, footer{
  display: block;
  width: 100%;
  box-sizing: border-box;
}

header{
  background: #8f8fff;
  padding: 2px;
  text-align: center;
  font-size: 30px;
}

header > span {
  font-weight: 500;
}

main{
  padding: 10px;
  height: 80vh;
}
</style>
