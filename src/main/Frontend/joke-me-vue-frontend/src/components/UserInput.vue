<script>
import axios from 'axios'

export default {
  data() {
    return {
      inputField1: '',
      inputField2: '',
      apiResult: ''
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
            this.apiResult = response.data;
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
    <div class="form-group">
      <label for="dropdown1" class="form-label">Joke type:</label>
      <select id="dropdown1" v-model="inputField1" class="form-control">
        <option value="any">Any</option>
        <option value="programming">Programming</option>
        <option value="christmas">Christmas</option>
      </select>
    </div>

    <div class="form-group">
      <label for="dropdown2" class="form-label">Language:</label>
      <select id="dropdown2" v-model="inputField2" class="form-control">
        <option value="english">English</option>
        <option value="german">German</option>
      </select>
    </div>

    <div class="form-group">
      <button @click="handleClick" class="btn btn-primary">Submit</button>
    </div>

    <div class="form-group">
      <div>
        <label for="result" class="result-label">Result:</label>
        <span id="result" class="result-value">{{ apiResult }}</span>
      </div>
    </div>
  </div>
</template>


<style>
.form-group {
  margin-bottom: 1rem;
  width: 500px;
  height: 70px;
}

.form-label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: bold;
}

.form-control {
  display: block;
  width: 100%;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  line-height: 1.5;
  color: #495057;
  background-color: #fff;
  background-clip: padding-box;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

.btn {
  display: inline-block;
  font-weight: 400;
  color: #fff;
  text-align: center;
  vertical-align: middle;
  -webkit-user-select: none;
     -moz-user-select: none;
      -ms-user-select: none;
          user-select: none;
  background-color: #007bff;
  border: 1px solid #007bff;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  line-height: 1.5;
  border-radius: 0.25rem;
  transition: color 0.15s ease-in;
}

.result-label {
font-weight: bold;
}

.result-value {
display: block;
margin-top: 0.5rem;
}
</style>

  