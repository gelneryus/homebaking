var app3 = new Vue({
    el: '#app3',
    data: {
        account: {},
    },
    created() {
        this.loadData()
    },
    methods: {
        loadData() {

            const urlParams = new URLSearchParams(window.location.search);
            const Id = urlParams.get('id');

            axios.get(`http://localhost:8080/api/accounts/${Id}`)
                .then(response => {
                    this.account = response.data;
                    console.log(response.data);
                })

        }

    }
})