var register = new Vue({
    el: '#register',
    data: {
        form: {
            firstName: "",
            lastName: "",
            email: "",
            password: ""
        }
    },

    methods: {
        AddClient() {
            if (this.form.firstName && this.form.lastName && this.form.email.includes("@")) {
                let client = {
                    firstName: this.form.firstName,
                    lastName: this.form.lastName,
                    email: this.form.email,
                    password: this.form.password
                }
                this.postClient(client)
            }
        },

        postClient(client) {
            axios.post('/api/clients', "firstName=" + client.firstName + "&lastName=" + client.lastName + "&email=" + client.email + "&password=" + client.password, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => console.log('registered'))
                .catch(error => {
                    "error a la creacion de cliente"
                });
        }

    }

})