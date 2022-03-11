var register = new Vue({
    el: '#register',
    data: {
        form: {
            firstName: "",
            lastName: "",
            email: "",
            password: "",
            typeAccounts: ""
        }
    },

    methods: {
        AddClient() {
            if (this.form.firstName && this.form.lastName && this.form.email.includes("@")) {
                let client = {
                    firstName: this.form.firstName,
                    lastName: this.form.lastName,
                    email: this.form.email,
                    password: this.form.password,
                    typeAccounts: this.form.typeAccounts
                }
                this.postClient(client)
            }
        },

        postClient(client) {
            axios.post('/api/clients', "firstName=" + client.firstName + "&lastName=" + client.lastName + "&email=" + client.email + "&password=" + client.password + "&typeAccounts=" + client.typeAccounts, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => {
                    window.alert("SE REGISTRO CON EXITO!")
                    console.log('registered')

                    return window.location.href = "/index.html"

                })


            .catch(error => {
                "error a la creacion de cliente"
            });
        }

    }

})