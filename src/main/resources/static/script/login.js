var app6 = new Vue({
    el: '#app6',
    data: {
        email: "",
        password: "",
    },

    methods: {
        login() {
            axios.
            post('/api/login', "email=" + this.email + "&password=" + this.password, {
                headers: {
                    'content-type': 'application/x-www-form-urlencoded'
                }
            })

            .then(response => {
                window.alert("USTED INGRESO A SU HOME BANKING")
                console.log('signed in!!!');
                return window.location.href = "/accounts.html"

            }).catch(error => {
                "error de email o password"


            })

        }
    },

    logout() {
        axios.post(`/api/logout`)

        .then(response => console.log('signed out!!'))
            .then

        return (window.location.href = "/index.html")

    }




})