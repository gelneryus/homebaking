let app = new Vue({
    el: '#app',
    data: {
        clients: [],
        json: [],
        form: {
            firstName: "",
            lastName: "",
            email: "",
        }
    },
    created() {
        this.loadData()
    },

    methods: {
        loadData() {
            axios.get("/clients")
                .then(response => {
                    let data = response.data._embedded.clients;
                    this.json = response.data;
                    this.clients = data;

                })
        },
        AddClient() {
            if (this.form.firstName && this.form.lastName && this.form.email.includes("@")) {
                let client = {
                    firstName: this.form.firstName,
                    lastName: this.form.lastName,
                    email: this.form.email,
                };
                this.postClient(client)
            }
        },

        postClient(client) {
            axios.post("/clients", client)
                .then(response => {
                    this.loadData()
                })
        },

        DeleteClient(link) {

            this.link = link._links.client.href

            axios.delete(this.link)

            .then(deleted => { this.loadData() })

        },
        logout() {
            axios.post(`/api/logout`)

            .then(response => console.log('signed out!!'))
                .then
            return (window.location.href = "/index.html")

        },

    }

})