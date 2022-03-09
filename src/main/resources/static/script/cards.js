var app4 = new Vue({
    el: '#app4',
    data: {
        client: [],
        cards: [],
        checkType: [],
        checkColor: [],
    },
    created() {
        this.loadData()
    },

    methods: {
        loadData() {
            axios.get("http://localhost:8080/api/clients/current")
                .then(response => {
                    this.client = response.data;

                    this.cards = this.client.card;

                    console.log(this.cards)

                    console.log(this.client)
                })
                .catch(e => {
                    console.log("error get")


                })
        },
        newCard() {
            console.log("asdas")
            axios.post("/api/clients/current/cards ", "cardType=" + this.checkType + "&cardColor=" + this.checkColor, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => {
                    console.log("CREADO")
                    window.location.href = "/cards.html"
                })
                .catch(error => {
                    "error"
                });
        },
        expiredCard(tarjeta) {
            let dateNow = new Date()
            let expirationDate = new Date(tarjeta.thruDate)
            return expirationDate < dateNow
        },
    },








})