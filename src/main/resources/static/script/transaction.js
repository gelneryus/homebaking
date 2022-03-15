var app8 = new Vue({
    el: '#app8',
    data: {
        accounts: [],
        numberOrigen: "",
        numberDestino: "",
        amount: 0,
        description: "",
        cuentaOrigen: true,
        cuentaDestino: false
    },

    created() {
        this.loadData()
    },

    methods: {
        loadData() {
            axios.get("/api/clients/current")
                .then(response => {
                    this.accounts = response.data.account;

                    console.log(this.accounts)

                })
                .catch(e => {
                    console.log("error get")

                })
        },
        newtrans() {
            console.log(this.amount, this.description, this.numberOrigen, this.numberDestino)
            axios.post("/api/transactions", "amount=" + this.amount + "&description=" + this.description + "&numberDestiny=" + this.numberDestino + "&numberOrigin=" + this.numberOrigen, {
                    headers: { 'content-type': 'application/x-www-form-urlencoded' }
                })
                .then(response => {
                    window.alert("TRANSACCION HECHA CON EXITO!!!")
                    console.log("CREADO")
                        //  location.reload

                })
                .catch(error => {
                    console.log(error)
                    window.alert("NO SE PUDO HACER LA TRANSACCION")
                });
        },
        cuentaOrigens() {
            this.cuentaOrigen = true
            this.cuentaDestino = false
        },
        cuentaDestinos() {
            this.cuentaDestino = true
            this.cuentaOrigen = false
        }
    },



})