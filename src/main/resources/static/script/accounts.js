var app2 = new Vue({
    el: '#app2',
    data: {
        account: [],
        client: [],
        loans: [],
        accType: "",

    },
    created() {
        const urlParams = new URLSearchParams(window.location.search);
        var myParam = urlParams.get("id");

        axios.get("http://localhost:8080/api/clients/current")
            .then(response => {
                this.client = response.data;

                this.account = this.client.account;

                this.loans = this.client.loans

                console.log(this.account)

                console.log(this.client)
            })
            .catch(e => {
                console.log("error get")
            })
    },
    methods: {
        logout() {
            axios.post(`/api/logout`)

            .then(response => console.log('signed out!!'))
                .then
            return (window.location.href = "/index.html")

        },

        createAccount() {
            axios.post("/api/clients/current/accounts", "typeAccounts=" + this.accType, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(res => {
                    location.reload()
                })
        },
        crearPrestamo() {
            axios.post("/api/loans", {
                    "loanTypeId": this.cargarLoans,
                    "amount": this.amount,
                    "payments": this.payments[0],
                    "numberAccount": this.numberAccount
                })
                .then(response => {
                    console.log("Prestamo creado")
                        // return (window.location.href = "/accounts.html");
                })
                .catch(error => {
                    "error"
                });
        },
        deleteAccount(id) {
            axios.patch(`/api/clients/current/accounts/delete/` + id)
                .then(response => {
                    return window.location.reload()
                })
        },
    },
})