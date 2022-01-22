<template>
  <div class="radio-block">
    <div class="check-btn">
      <input type="radio" :id="n4" value=-4 v-model="xLoc" v-model.number="xLoc"  v-on:change="changeRadioBtn">
      <label :for="n4">-4</label>
    </div>
    <div class="check-btn">
      <input type="radio" :id="n3" value=-3 v-model="xLoc" v-model.number="xLoc" v-on:change="changeRadioBtn">
      <label :for="n3">-3</label>
    </div>
    <div class="check-btn">
      <input type="radio" :id="n2" value=-2 v-model="xLoc" v-model.number="xLoc" v-on:change="changeRadioBtn">
      <label :for="n2">-2</label>
    </div>
    <div class="check-btn">
      <input type="radio" :id="n1" value=-1 v-model="xLoc" v-model.number="xLoc" v-on:change="changeRadioBtn">
      <label :for="n1">-1</label>
    </div>
    <div class="check-btn">
      <input type="radio" :id="zero" value=0 v-model="xLoc" v-model.number="xLoc" v-on:change="changeRadioBtn">
      <label :for="zero">0</label>
    </div>
    <div class="check-btn">
      <input type="radio" :id="p1" value=1 v-model="xLoc" v-model.number="xLoc"  v-on:change="changeRadioBtn">
      <label :for="p1">1</label>
    </div>
    <div class="check-btn">
      <input type="radio" :id="p2" value=2 v-model="xLoc" v-model.number="xLoc" v-on:change="changeRadioBtn">
      <label :for="p2">2</label>
    </div>
    <div class="check-btn">
      <input type="radio" :id="p3" value=3 v-model="xLoc" v-model.number="xLoc" v-on:change="changeRadioBtn">
      <label :for="p3">3</label>
    </div>
    <div class="check-btn">
      <input type="radio" :id="p4" value=4 v-model="xLoc" v-model.number="xLoc" v-on:change="changeRadioBtn">
      <label :for="p4">4</label>
    </div>
  </div>
</template>

<script>
export default {
  name: "radio-btn",
  data (){
    return{
      id: null,
      xLoc: 0
    }
  },
  methods:{
    changeRadioBtn: function (){
      if(this.xLoc === undefined) this.xLoc = 0;
      this.$emit('radioChange', this.xLoc)
    },
    resetChoice: function (){
      this.xLoc = 0;
    }
  },
  mounted () {
    this.id = this._uid
  },
  computed: {
    n4: function (){
      return this.id+"n4"
    },
    n3: function (){
      return this.id+"n3"
    },
    n2: function (){
      return this.id+"n2"
    },
    n1: function (){
      return this.id+"n1"
    },
    zero: function (){
      return this.id+"zero"
    },
    p1: function (){
      return this.id+"p1"
    },
    p2: function (){
      return this.id+"p2"
    },
    p3: function (){
      return this.id+"p3"
    },
    p4: function (){
      return this.id+"p4"
    },
  }
}
</script>

<style scoped>
.radio-block{
  width: 100%;
  display: grid;
  grid-template-columns: repeat(9, 1fr);
  grid-column-gap: 5px;
}
.check-btn > input, label{
  display: block;
  text-align:center;
}
.check-btn > input[type="radio"] {
  /* Add if not using autoprefixer */
  -webkit-appearance: none;
  appearance: none;
  background-color: #fff;
  margin: 0 auto;
  font: inherit;
  color: #8f8fff;
  width: 0.9em;
  height: 0.9em;
  border: 0.1em solid #8f8fff;
  border-radius: 50%;

  display: grid;
  place-content: center;
}

.check-btn > input[type="radio"]::before {
  content: "";
  width: 0.55em;
  height: 0.55em;
  border-radius: 50%;
  transform: scale(0);
  transition: 120ms transform ease-in-out;
  box-shadow: inset 1em 1em #8f8fff;
}

.check-btn > input[type="radio"]:hover {
  filter: brightness(120%);
}

.check-btn > input[type="radio"]:checked::before {
  transform: scale(1);
}
</style>
