var app4 = new Vue({
    el: '#app4',
    data: {
        client: [],
        cards: [],
        checkType: [],
        checkColor: [],
        cardsTrue: []
    },
    created() {
        this.loadData()

        const urlParams = new URLSearchParams(window.location.search);
        var myParam = urlParams.get("id");


    },

    methods: {
        loadData() {
            axios.get("/api/clients/current")
                .then(response => {
                    this.client = response.data;

                    this.cards = this.client.card;

                    this.cardsTrue = this.cards.filter(card => card.esActiva == true)

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
                    window.alert("La tarjeta se a creado con exito")
                    console.log("CREADO")
                    window.location.href = "/cards.html"
                })
                .catch(error => {
                    "error"
                });
        },

        deleteCards(id) {
            axios.patch(`/api/clients/current/cards/delete/` + id)
                .then(response => {
                    return window.location.reload()
                })
        },
        /*      expiredCard(tarjeta) {
                 let dateNow = new Date()
                 let expirationDate = new Date(tarjeta.thruDate)
                 return expirationDate < dateNow
             }, */
    },








})