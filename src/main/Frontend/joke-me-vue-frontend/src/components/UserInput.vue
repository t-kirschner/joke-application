<script>
import axios from 'axios'

export default {
  data() {
    return {
      inputField1: '',
      inputField2: ''
    }
  },
  methods: {
    handleClick() {
        const inputData = {
            inputField1: this.inputField1,
            inputField2: this.inputField2
        }
      axios.post('http://localhost:8080/', inputData)
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
      <label for="dropdown1">Joke type: </label>
      <select id="dropdown1" v-model="inputField1">
        <option value="any">Any</option>
        <option value="programming">Programming</option>
        <option value="christmas">Christmas</option>
      </select>
    </div>

    <div>
      <label for="dropdown2">Language: </label>
      <select id="dropdown2" v-model="inputField2">
        <option value="english">English</option>
        <option value="german">German</option>
      </select>
    </div>

    <div>
        <button @click="handleClick">Submit</button>
    </div>
  </template>
  