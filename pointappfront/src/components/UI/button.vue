<template>
  <button v-bind:class="{isDangerous: dangerous, isSafe: safe}">
    <slot></slot>
  </button>
</template>

<script>
export default {
  name: "usual-button",
  props:{
    btnType: String
  },
  computed: {
    dangerous: function (){
      return this.btnType === 'dangerous'
    },
    safe: function(){
      return !this.dangerous
    }
  }
}
</script>

<style scoped>
button {
  display: inline-block;
  padding: 0.35em 1.2em;
  border: 0.1em solid #FFFFFF;
  margin: 0.3em 0 0.3em 0.3em;
  border-radius: 0.12em;
  box-sizing: border-box;
  text-decoration: none;
  font-size: 20px;
  color: #FFFFFF;
  background-color: #292929;
  text-align: center;
  transition: all 0.2s;
}
button.isSafe:hover {
  color: #000000;
  background-color: #ffffff;
}

button.isDangerous:hover{
  color: #ffffff;
  background-color: #ab2a3d;
  border: 0.1em solid #ab2a3d;
}
</style>
