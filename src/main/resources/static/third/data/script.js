
const URL = "/api/data/dish//";

var webVue

webVue = new Vue({
    el: '#app',
    data: {
        dishes : []
    },
    mounted: function () {
        this.findRules()
    },
    methods: {
        findRules: async function () {
            const response = await axios.get(URL)
            this.dishes = response.data
            console.log(this.dishes)
        },
        displayUpdateDish: function (dish) {
            alert(dish)
        }
      }
});