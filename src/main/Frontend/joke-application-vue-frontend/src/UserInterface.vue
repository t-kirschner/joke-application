<script>
import axios from 'axios'

export default {
  data() {
    return {
      inputLanguage: '',
      apiResult: ''
    }
  },
  methods: {
    // action triggered when clicking "Submit" button. Sends selected option in dropdown menu.
    handleClick() {
      axios.post('http://localhost:8080/', this.inputLanguage)
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
      <div>
        <label for="joke-type" class="form-label">Joke Type:</label>
        <span id="joke-type">Programming</span>
      </div>
    </div>

    <div class="form-group">
      <label for="language-dropdown" class="form-label">Language:</label>
      <select id="language-dropdown" v-model="inputLanguage" class="form-control">
        <option value="1">English</option>
        <option value="2">German</option>
      </select>
    </div>

    <div class="form-group">
      <button @click="handleClick" class="btn">Submit</button>
    </div>

    <div class="form-group">
      <div>
        <label for="joke-result" class="result-label">Result:</label>
        <span id="joke-result" class="result-value">{{ apiResult }}</span>
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
font-style: italic;
}
</style>

  