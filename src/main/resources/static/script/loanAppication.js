var app9 = new Vue({
    el: "#app9",
    data: {
        amount: "",
        numberAccount: "",
        payment: "",

        maxAmount: [],
        destinyAccount: [],
        payments: [],
        loans: [],
        cargarLoans: "",
        cargarAccounts: "",
        interesTotal: 0,
        loan: [],
        accountsTrue: [],
    },

    created() {
        this.cargarAccount()
        this.cargarCuota()
    },

    methods: {
        cargarCuota() {
            axios.get("/api/loans")
                .then(response => {
                    this.loans = response.data
                    this.payments = this.loans.filter(loan => loan.name == this.cargarLoans)
                    this.payments = this.payments[0].payments
                    console.log(this.payments)

                    // this.payments = response.data[0].payments
                })
                .catch(error => {
                    "error"
                });
        },

        cargarAccount() {
            axios.get("/api/clients/current")
                .then(response => {
                    this.destinyAccount = response.data.account
                    this.accountsTrue = this.destinyAccount.filter(account => account.active == true)
                    console.log(this.destinyAccount)
                    console.log(this.accountsTrue)
                })
                .catch(error => {
                    "error"
                });
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

        /*     total() {
                let total = parseFloat(this.application.amount * (0.20)) + parseFloat(this.application.amount)
                let cuotas = parseFloat(total / this.application.payments);
            } */

    },
    computed: {
        getMaxAmount: function() {
            if (this.prestamoASolicitar != "") {
                loan = this.loans.filter(loan => loan.name == this.cargarLoans);
                return loan[0].maxAmount;
            }
        },
        getInterest: function() {
            if (this.prestamoASolicitar != "") {
                loan = this.loans.filter(loan => loan.name == this.cargarLoans);
                return loan[0].interes;
            }
        }
    }


})