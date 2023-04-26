<script>
import axios from 'axios'

export default {
  data() {
    return {
      inputText: ''
    }
  },
  methods: {
    handleClick() {
      // Hier kannst du die Eingabe aus inputText verwenden
      axios.post('http://localhost:8080/', {text: this.inputText})
        .then(response => {
            console.log(response.data)
        })
        .catch(error => {
            console.error(error)
        })
    }
  }
}
</script>

<template>
    <div>
      <input type="text" v-model="inputText">
      <button @click="handleClick">Submit</button>
    </div>
  </template>